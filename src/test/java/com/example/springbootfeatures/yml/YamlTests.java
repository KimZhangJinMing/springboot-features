package com.example.springbootfeatures.yml;

import com.example.springbootfeatures.domains.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootTest
public class YamlTests {

    @Autowired
    private Guest guest;
    @Autowired
    private Environment environment;
    @Value("${guest.name}")
    private String name;

    // 加载classpath下的yml文件，加载成Properties
    @Test
    public void sample1() {
        Resource wechatResource = new ClassPathResource("guest.yml");
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(wechatResource);
        Properties properties = yamlPropertiesFactoryBean.getObject();
        String method = properties.getProperty("name");
        System.out.println(method);

        // properties转换成map
        Map<String,String> map = new HashMap<>((Map) properties);
        System.out.println(map);
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
    }

    // 加载classpath下的yml文件，加载成Map
    @Test
    public void sample2() {
        Resource wechatResource = new ClassPathResource("guest.yml");
        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
        yamlMapFactoryBean.setResources(wechatResource);
        Map<String, Object> map = yamlMapFactoryBean.getObject();
        System.out.println(map);
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
    }

    @Test
    public void sample3() {
        Resource wechatResource = new ClassPathResource("guest.yml");
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        try {
            List<PropertySource<?>> propertySources = yamlPropertySourceLoader.load("guest",wechatResource);
            System.out.println(propertySources.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sample4() {
        System.out.println(guest);
        System.out.println(environment.getProperty("guest.name"));
        System.out.println(environment.getProperty("key"));
        System.out.println(name);
    }
}
