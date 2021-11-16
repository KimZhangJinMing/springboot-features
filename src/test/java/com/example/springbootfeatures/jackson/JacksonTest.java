package com.example.springbootfeatures.jackson;

import com.example.springbootfeatures.domains.Guest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Kim
 * @Date 2021/11/16 9:40
 */
@SpringBootTest
public class JacksonTest {

    @Autowired
    private ObjectMapper objectMapper;



    /**
     * 反序列化成一个Bean
     * @throws IOException
     */
    @Test
    public void sample1() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest.json");
        Guest guest = objectMapper.readValue(resource.getInputStream(), Guest.class);
        System.out.println(guest);
    }

    /**
     * 反序列化为map
     * 转换成实体类itemPrice会转换成BigDecimal类型
     * 转换成Map,itemPrice会转换成Integer类型
     * @throws IOException
     */
    @Test
    public void sample2() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest.json");
        // 可以完成转换,但是没有泛型,默认转换为Map<Object,Object>
        // 这里可以写Map,也可以是HashMap
        Map map = objectMapper.readValue(resource.getInputStream(), HashMap.class);
        System.out.println(map);
    }

    /**
     * 反序列化为map
     * @throws IOException
     */
    @Test
    public void sample3() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest.json");
        // 利用TypeReference转换成Map，可以写泛型
        Map<String, Object> map = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<Map<String, Object>>() {});
        System.out.println(map);
    }

    /**
     * 反序列化为map
     * @throws IOException
     */
    @Test
    public void sample4() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest_1.json");
        // 利用TypeReference转换成Map<String,String>，json字符串中不能有对象
        Map<String, String> map = objectMapper.readValue(resource.getInputStream(),
                new TypeReference<Map<String, String>>() {});
        System.out.println(map);
    }

    /**
     * 反序列化为map
     * @throws IOException
     */
    @Test
    public void sample5() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest_1.json");
        // 利用TypeReference转换成Map<String,String>，json字符串中不能有对象
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class,
                String.class);
        Map<String, String> map = objectMapper.readValue(resource.getInputStream(), javaType);
        System.out.println(map);
    }



    /**
     * 反序列化为List
     * @throws IOException
     */
    @Test
    public void sample6() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest_2.json");
        // 虽然能转换成List,但是没有泛型，无法获取到实体类的属性
        List list = objectMapper.readValue(resource.getInputStream(), List.class);
        System.out.println(list);
        // 无法通过list.get(0).get(xxx)
        System.out.println(list.get(0));
    }

    /**
     * 反序列化为List
     * @throws IOException
     */
    @Test
    public void sample7() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest_2.json");
        List<Guest> guests = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Guest>>() {
        });
        System.out.println(guests);
        // 无法通过list.get(0).get(xxx)
        System.out.println(guests.get(0).getName());
    }

    /**
     * 反序列化为List
     * @throws IOException
     */
    @Test
    public void sample8() throws IOException {
        Resource resource = new ClassPathResource("/jackson/guest_2.json");
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Guest.class);
        List<Guest> guests = objectMapper.readValue(resource.getInputStream(), javaType);
        System.out.println(guests);
        // 无法通过list.get(0).get(xxx)
        System.out.println(guests.get(0).getName());
    }
}
