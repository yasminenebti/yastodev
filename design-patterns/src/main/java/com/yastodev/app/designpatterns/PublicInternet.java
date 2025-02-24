package com.yastodev.app.designpatterns;

import org.springframework.stereotype.Service;

@Service
public class PublicInternet implements Internet{
    private static final String BANNED = "banned.com";
    @Override
    public void connectTo(String host) {
        if (BANNED.equals(host)) {
            System.out.println("Access Denied");
        }
        System.out.println("Opened connection to " + host);
    }
}
