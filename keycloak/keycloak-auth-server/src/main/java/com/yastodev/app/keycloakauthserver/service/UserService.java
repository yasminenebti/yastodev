package com.yastodev.app.keycloakauthserver.service;

import com.yastodev.app.keycloakauthserver.model.UserDto;

public interface UserService {
    void saveUser(UserDto user);
    void senVerificationEmail(String userId);
    void deleteUser(String userId);
    void forgotPassword(String username);

}
