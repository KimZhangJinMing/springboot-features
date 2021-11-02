package com.example.springbootfeatures.scheduler;

import com.example.springbootfeatures.global.GlobalConstant;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class WechatTask {

    // @Scheduler标注的方法不能有参数
    @Scheduled(fixedRate = 1000 * 60)
    @EventListener(classes = ApplicationReadyEvent.class)
    public void run() {
        if(GlobalConstant.completed) {
            System.out.println("wechatTask run...");
            System.out.println(GlobalConstant.completed);
        }
    }
}
