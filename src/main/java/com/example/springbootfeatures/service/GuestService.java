package com.example.springbootfeatures.service;

import com.example.springbootfeatures.domains.Guest;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    public Guest findGuest(String name) {
        return new Guest("kim",24);
    }
}
