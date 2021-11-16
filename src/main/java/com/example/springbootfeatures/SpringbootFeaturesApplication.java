package com.example.springbootfeatures;

import com.example.springbootfeatures.config.CustomizedPropertySourceFactory;
import com.example.springbootfeatures.domains.Guest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(Guest.class)
@PropertySource(value = {"classpath:other.yml","classpath:other.properties"},factory = CustomizedPropertySourceFactory.class)
public class SpringbootFeaturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFeaturesApplication.class, args);
    }


}
