package com.example.springbootfeatures.listeners;

import com.example.springbootfeatures.events.LoadReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReadyListener {

    @EventListener
    public void listener1(LoadReadyEvent event) {
        System.out.println("listener1 listen: " + event.getData());
    }

    @EventListener
    public void listener2(LoadReadyEvent event) {
        System.out.println("listener2 listen: " + event.getData());
    }
}
