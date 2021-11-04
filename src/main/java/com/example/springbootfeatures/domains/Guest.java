package com.example.springbootfeatures.domains;

import com.example.springbootfeatures.config.CustomizedPropertySourceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.PropertySource;

import java.math.BigDecimal;
import java.util.List;

@Data
// @Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@PropertySource(value = "classpath:/guest.yml",name = "guest",encoding = "utf-8",factory = CustomizedPropertySourceFactory.class)
@ConfigurationProperties(prefix = "guest")
// @ConstructorBinding
// @Component
public class Guest {
    private String name;
    private int age;
    private List<String> skills;
    private BigDecimal itemPrice;
    private Security security;

    @Data
    @NoArgsConstructor
    private static class Security {

        private String username;
        private String password;

        // @DefaultValue不起作用
        public Security(String username,@DefaultValue("1234") String password) {
            this.username = username;
            this.password = password;
        }

    }
}
