package com.liao.common.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * spring工具类 方便在非spring管理环境中获取bean
 * </p>
 *
 * @author LiAo
 * @since 2020/12/16
 */
@Component
public final class SpringUtils implements BeanFactoryPostProcessor {

    // Spring应用上下文环境
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * <p>Spring提供了对BeanFactory进行操作的处理器BeanFactoryProcessor</p>
     * <p>简单来说就是获取容器BeanFactory，这样就可以在真正初始化bean之前对bean做一些处理操作。</p>
     * <p>允许我们在工厂里所有的bean被加载进来后但是还没初始化前</p>
     * <p>对所有bean的属性进行修改也可以add属性值</p>
     *
     * @param beanFactory 提供bean definition的解析,注册功能
     * @throws BeansException 异常
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    /**
     * 获取对象
     *
     * @param name 对象名称
     * @param <T>  泛型
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException 异常
     */
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取对象
     *
     * @param clzz  反射类型
     * @param <T>泛型
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clzz) throws BeansException {
        T result = (T) beanFactory.getBean(clzz);

        return result;
    }

}
