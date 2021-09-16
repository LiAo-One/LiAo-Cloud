package com.liao.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.liao.datascope.annotation.Log;
import com.liao.common.enums.BusinessStatus;
import com.liao.datascope.system.entity.SysOpenLog;
import com.liao.common.utils.ServletUtils;
import com.liao.common.utils.StringUtils;
import com.liao.framework.manager.AsyncManager;
import com.liao.framework.manager.factory.AsyncFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>
 * 日志注解处理
 * </p>
 *
 * @author LiAo
 * @since 2020/12/16
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    private String IP = "";

    // 换行符
    private static final String LINE_SEPARATOR = System.lineSeparator();

    // 织入点
    @Pointcut("@annotation(com.liao.datascope.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 在请求前织入 获取ip
     *
     * @param joinPoint 切面方法信息
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // 使用断言检验数据
        assert attributes != null;

        // 获取请求数据
        HttpServletRequest request = attributes.getRequest();

        IP = request.getRemoteAddr();

        // 获取注解描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("========================================== Start ==========================================");

        // 打印请求url
        log.info("URL                : {}", request.getRequestURI());

        // 打印描述信息
        log.info("ModuleName         : {}", methodDescription);

        // 打印 Http method
        log.info("HTTP Method        : {}", request.getMethod());

        // 打印Controller 全路径
        log.info("Controller Path    : {}", joinPoint.getSignature().getDeclaringTypeName());

        // 打印Controller 执行方法
        log.info("Execution Method   : {}", joinPoint.getSignature().getName());

        //打印请求IP
        log.info("IP                 : {}", request.getRemoteAddr());

        // 打印请求入参
        log.info("Request Args       : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    // 环绕
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long strtTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        // 打印出参
        log.info("Response Args      : {}", JSON.toJSONString(result));

        // 执行耗时
        log.info("Time-Consuming     : {} ms", (System.currentTimeMillis() - strtTime));

        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);

        return result;
    }


    /**
     * 处理完请求后执行
     *
     * @param joinPoint  切面方法信息
     * @param jsonResult 返回值
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }


    /**
     * 拦截异常操作
     *
     * @param joinPoint 切面方法信息
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    /**
     * 日志信息持久化
     *
     * @param joinPoint  切面方法信息
     * @param e          异常
     * @param jsonResult 返回参数
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解信息
            Log controllerLog = getAnnotationLog(joinPoint);

            if (controllerLog == null) {
                return;
            }

            // 数据库日志
            SysOpenLog sysOpenLog = new SysOpenLog();
            // 操作状态
            sysOpenLog.setOpenStatus(BusinessStatus.SUCCESS.ordinal());
            // 请求IP
            sysOpenLog.setOperIp(IP);
            // 返回参数
            sysOpenLog.setOpenResult(JSON.toJSONString(jsonResult));
            // 请求路径
            sysOpenLog.setOpenUrl(ServletUtils.getRequest().getRequestURI());
            // 请求方式
            sysOpenLog.setOpenRequestMethod(ServletUtils.getRequest().getMethod());
            // 异常不为空
            if (e != null) {
                // 操作结果
                sysOpenLog.setOpenStatus(BusinessStatus.FAIL.ordinal());

                // 截取异常
                sysOpenLog.setOpenErrorMsg(StringUtils.substring(e.getMessage(), 0, 1500));
            }

            // 类名称
            String className = joinPoint.getTarget().getClass().getName();
            // 方法名
            String methodName = joinPoint.getSignature().getName();
            // 拼接
            sysOpenLog.setOpenMethod(className + "." + methodName + "()");
            // 参数
            getControllerMethodDescription(controllerLog, sysOpenLog);

            // 使用异步保存日志信息
            AsyncManager.me().execute(AsyncFactory.recordOper(sysOpenLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param openLog 操作日志
     * @throws Exception 异常
     */
    public void getControllerMethodDescription(Log log, SysOpenLog openLog) throws Exception {

        // 设置动作（0其它 1新增 2修改 3删除）
        openLog.setOpenBusinessType(log.businessType().ordinal());
        // 设置标题
        openLog.setOperTitle(log.title());
        // 是否需要保存请求参数
        if (log.isSaveRequestData()) {
            setRequestValue(openLog);
        }
    }

    /**
     * 请求参数放入日志
     *
     * @param openLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(SysOpenLog openLog) throws Exception {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSON.toJSONString(map);
        openLog.setOperParam(StringUtils.substring(params, 0, 1500));
    }


    /**
     * 获得注解信息
     *
     * @param joinPoint 切面方法信息
     * @return 存在的注解信息
     * @throws Exception 异常
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        // 获取目标方法信息
        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature = (MethodSignature) signature;

        // 获取目标方法
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }

        return null;
    }

    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception {
        String targeName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] argumets = joinPoint.getArgs();
        Class targetClass = Class.forName(targeName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] classes = method.getParameterTypes();
                if (classes.length == argumets.length) {
                    description.append(method.getAnnotation(Log.class).title());
                    break;
                }
            }
        }

        return description.toString();
    }
}
