package com.example.springbootfeatures;

import com.example.springbootfeatures.events.LoadReadyEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

@SpringBootTest
class SpringbootFeaturesApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void publicEvent() {
        ApplicationEvent event = new LoadReadyEvent(this, "hello");
        applicationContext.publishEvent(event);
    }

}
