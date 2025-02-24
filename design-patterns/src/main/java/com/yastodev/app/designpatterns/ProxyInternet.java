package com.yastodev.app.designpatterns;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProxyInternet implements Internet{
    private static final List<String> bannedSites;
    private final Internet internet = new PublicInternet();

    static {
        bannedSites = new ArrayList<>();
        bannedSites.add("banned.com");
    }
    @Override
    public void connectTo(String host) {
        if (bannedSites.contains(host)){
            System.out.println("Access Forbidden !");
            return;
        }
        internet.connectTo(host);
    }
}
