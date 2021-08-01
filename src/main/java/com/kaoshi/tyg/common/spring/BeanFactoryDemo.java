package com.kaoshi.tyg.common.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BeanFactoryDemo implements BeanPostProcessor {

    @Resource
    private ApplicationContext context;

    public void test(){
        BeanFactory parentBeanFactory = context.getParentBeanFactory();
    }

}
