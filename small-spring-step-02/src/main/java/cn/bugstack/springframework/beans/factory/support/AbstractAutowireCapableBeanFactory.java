package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * 和{@link AbstractBeanFactory}类似，都是一个模板类
 * 实现{@link AbstractBeanFactory}的{@link #createBean(String, BeanDefinition)}方法,作用：根据bean定义创建bean，将bean保存到单例bean map中
 * 达到了定制化开发
 * {@link AbstractBeanFactory#getBeanDefinition(String)}方法还未重写
 * 所以此类依然是一个模板类，只是规定了一个方法，其他类可继承该类，并实现此类未重写的方法
 *
 * 作用是当bean不存在时，将bean定义表中的bean拿出放入单例bean容器中
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

}
