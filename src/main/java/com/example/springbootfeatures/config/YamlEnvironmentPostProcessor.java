package com.example.springbootfeatures.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@Slf4j
public class YamlEnvironmentPostProcessor implements EnvironmentPostProcessor {

    // application.yml中的属性值
    private final String YAML_RESOURCES = "customized.yaml.resources";
    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
    private final String DELIMITER = ";";

    // 加载classpath下所有的yml｜yaml文件,并将其加入到Environment中,以便使用@Value注解注入
    // 执行到这里时,Environment中已经包含了application[properties|yml]中的配置了
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String resourceLocationsStr = environment.getProperty(YAML_RESOURCES);
        if(resourceLocationsStr == null) {
            return;
        }
        String[] resourceLocations = resourceLocationsStr.split(DELIMITER);
        System.out.println("load customized yaml file: " + resourceLocationsStr);
        for (String location : resourceLocations) {
            Resource resource = new ClassPathResource(location);
            try {
                List<PropertySource<?>> propertySources = loader.load(resource.getFilename(), resource);
                environment.getPropertySources().addLast(propertySources.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
