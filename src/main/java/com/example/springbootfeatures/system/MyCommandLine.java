package com.example.springbootfeatures.system;

import com.example.springbootfeatures.exception.StartupException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCommandLine implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("load data from database.");
//        if(true) {
//            throw new StartupException("load WechatPay Configuration failed.");
//        }
        log.info("load data from database completed.");
    }
}
