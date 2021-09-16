package com.liao.common.core;

import com.liao.common.constant.Constants;
import com.liao.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.HashMap;

/**
 * <p>
 * 统一结果集
 * </p>
 *
 * @author LiAo
 * @since 2020/12/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "统一结果集", description = "统一结果集")
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    // 状态码
    private static final String CODE_TAG = "code";
    // 消息
    private static final String MSG_TAG = "msg";
    // 数据对象
    private static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        // 成功
        SUCCESS(200),
        // 警告
        WARN(301),
        // 错误
        ERROR(500),
        // 未登录
        LOGOUT(401);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    /**
     * 初始化一个空的
     */
    public R() {

    }

    /**
     * 初始化一个新的
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public R(Type type, String msg) {
        super.put(CODE_TAG, type);
        super.put(MSG_TAG, msg);
    }

    /**
     * 创建一个新的
     *
     * @param type 类型
     * @param msg  消息
     * @param data 数据
     */
    public R(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }


    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public R(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static R success() {
        return R.success("成功");
    }

    /**
     * 返回成功数据
     *
     * @param data 数据
     * @return 成功消息
     */
    public static R success(Object data) {
        return R.success("成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static R success(String msg) {
        return R.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static R success(String msg, Object data) {
        return new R(Type.SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  内容
     * @param code 状态码
     * @return 成功消息
     */
    public static R success(Integer code, String msg) {
        return new R(code, msg, null);
    }

    /**
     * 返回未登录消息
     */
    public static R logout() {
        return new R(Type.LOGOUT, "未登录", null);
    }

    /**
     * 返回警告消息
     *
     * @param msg 内容
     * @return 警告消息
     */
    public static R warn(String msg) {
        return R.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static R warn(String msg, Object data) {
        return new R(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static R error() {
        return R.error("失败", null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 错误消息
     * @return 错误消息
     */
    public static R error(String msg) {
        return R.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static R error(String msg, Object data) {
        return new R(Type.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static R error(int code, String msg) {
        return new R(code, msg, null);
    }

    /**
     * 判断 Object 类型
     *
     * @param object 对象
     * @return 结果
     */
    public static R r(Object object) {
        return StringUtils.isNotNull(object) ? R.success(object) : R.error();
    }

    /**
     * 判断 Integer 类型
     *
     * @param integer 参数
     * @return 结果集
     */
    public static R r(Integer integer) {
        return StringUtils.isNotZero(integer) ? R.success() : R.error();
    }

    /**
     * 判断Collection 类型
     *
     * @param coll 参数
     * @return 结果集
     */
    public static R r(Collection<?> coll) {
        return StringUtils.isNotEmpty(coll) ? R.success(coll) : R.error();
    }

    /**
     * boolean 类型
     *
     * @param bol 参数
     * @return 结果集
     */
    public static R r(boolean bol) {
        return bol ? R.success() : R.error();
    }

}
