package com.example.springbootfeatures.system;

import com.example.springbootfeatures.exception.StartupException;
import com.example.springbootfeatures.global.GlobalConstant;
import jdk.nashorn.internal.objects.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCommandLine implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("load data from database.");
//        if(true) {
//            throw new StartupException("load WechatPay Configuration failed.");
//        }
        long start = System.currentTimeMillis();
        while (start + 10000 < System.currentTimeMillis()) {

        }
        GlobalConstant.completed = true;
        log.info("load data from database completed.");
    }
}
