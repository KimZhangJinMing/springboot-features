package com.example.springbootfeatures.events;

import org.springframework.context.ApplicationEvent;

// 事件发布者，发布的数据通过构造方法传参
public class LoadReadyEvent extends ApplicationEvent {

    private String data;

    public LoadReadyEvent(Object source,String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
