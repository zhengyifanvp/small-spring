package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * 核心类，通过使用模板模式统一收口定义逻辑
 * {@link AbstractBeanFactory#getBean(String)}是统一方法，这个方法中包含以下两个方法
 * {@link #getBeanDefinition(String)} 和 {@link #createBean(String, BeanDefinition)}
 * 继承或实现{@link AbstractBeanFactory} 需要实现这两个方法，达到定制化开发。
 *
 * 此类继承了{@link DefaultSingletonBeanRegistry} 拥有了获取和注册单例bean的能力
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        //判断bean是否在单例bean容器中存在
        Object bean = getSingleton(name);
        //存在直接返回
        if (bean != null) {
            return bean;
        }
        //不存在，从 bean定义表 中拿
        BeanDefinition beanDefinition = getBeanDefinition(name);
        //拿到后放到单例bean容器中
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
