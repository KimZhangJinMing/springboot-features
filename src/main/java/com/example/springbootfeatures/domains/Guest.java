package com.example.springbootfeatures.domains;

import com.example.springbootfeatures.config.CustomizedPropertySourceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
//@PropertySource(value = "classpath:/guest.properties",name = "guest",encoding = "utf-8",factory = CustomizedPropertySourceFactory.class)
@ConfigurationProperties(prefix = "guest")
@Component
public class Guest {
    private String name;
    private int age;
    private List<String> skills;
}
