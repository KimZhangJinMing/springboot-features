package com.example.springbootfeatures.config;

import com.example.springbootfeatures.exception.StartupException;
import com.example.springbootfeatures.service.GuestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

// 自定义LoadException处理器,当springboot项目启动时发生指定的异常时会以友好的方式将错误信息显示在控制台
// 抛出的异常和这里捕获的异常必须一致，不能捕获异常的子类。如果抛出的异常是StartupException的子类,将不会被捕获
// 这也就意味着一个异常要写一个对应的FailureAnalysis
// 如果需要获取bean或者环境变量,需要实现BeanFactoryAware/Environment
public class StartupExceptionFailureAnalyzer extends AbstractFailureAnalyzer<StartupException> implements BeanFactoryAware {

    // 没有使用@Component等注解,不能使用@Autowired
//    @Autowired
//    private GuestService guestService;

    // 使用BeanFactoryAware接口获取bean
    private BeanFactory beanFactory;

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, StartupException cause) {
//        GuestService guestService = ((GuestService) beanFactory.getBean("guestService"));
//        System.out.println(guestService.findGuest("kim"));
        // 第一个参数是打印在控制台的异常信息
        // 第二个参数是打印在控制台的Action,即发生异常时正确的处理方式
        return new FailureAnalysis(cause.getMessage(),"Please check you configuration on database",cause);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
