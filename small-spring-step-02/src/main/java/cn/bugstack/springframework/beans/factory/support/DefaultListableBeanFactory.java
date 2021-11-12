package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link AbstractAutowireCapableBeanFactory}实现了{@link AbstractBeanFactory#createBean(String, BeanDefinition)}方法
 * {@link DefaultListableBeanFactory}实现了{@link AbstractBeanFactory#getBeanDefinition(String)} 方法
 * {@link DefaultListableBeanFactory}继承于{@link AbstractAutowireCapableBeanFactory}，
 * 说明了{@link DefaultListableBeanFactory}实现了{@link AbstractBeanFactory}所有的抽象类
 *
 * {@link DefaultListableBeanFactory}又实现了{@link BeanDefinitionRegistry#registerBeanDefinition(String, BeanDefinition)}方法
 * 拥有了注册bean定义的能力
 * 从父类中又拥有了get和注册单例bean的能力
 *
 * {@link #registerBeanDefinition(String, BeanDefinition)} 将bean放入bean定义表中,相当于暂存
 * {@link #getBeanDefinition(String)} 从bean定义表中拿到bean
 *
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

}
