package com.example.springbootfeatures.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

/**
 * 使@PropertySource支持yaml格式
 */
public class CustomizedPropertySourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String filename = resource.getResource().getFilename();
        name = name != null ? name : filename;
        // 使@PropertySource支持yaml格式,会加入到Environment中,可以使用@Value注解注入
        if(filename != null && (filename.endsWith("yml") || filename.endsWith("yaml"))) {
            // 方法一：使用YamlPropertySourceLoader
            List<PropertySource<?>> propertySources = new YamlPropertySourceLoader().load(name, resource.getResource());
            return propertySources.get(0);
        }
        // 默认的Properties支持
        return super.createPropertySource(name, resource);
    }
}
