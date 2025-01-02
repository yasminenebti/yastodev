package com.yastodev.app.keycloakauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeycloakAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeycloakAuthServerApplication.class, args);
        System.out.println("Hello from Keycloak Auth Server!");
    }

}
