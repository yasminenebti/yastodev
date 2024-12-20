package com.yastodev.app.keycloakauthserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
}
