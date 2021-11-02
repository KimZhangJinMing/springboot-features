package com.example.springbootfeatures.config;

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


public class YamlEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final String YAML_RESOURCES = "customized.yaml.resources";

    // 加载classpath下所有的yml｜yaml文件,并将其加入到Environment中,以便使用@Value注解注入
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String resourcesLocation = environment.getProperty(YAML_RESOURCES);
        System.out.println(resourcesLocation);

        Resource resource = new ClassPathResource(resourcesLocation);
        try {
            List<PropertySource<?>> propertySources = new YamlPropertySourceLoader().load(resource.getFilename(), resource);
            environment.getPropertySources().addLast(propertySources.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
