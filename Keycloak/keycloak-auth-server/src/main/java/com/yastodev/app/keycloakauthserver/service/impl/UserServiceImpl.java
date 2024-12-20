package com.yastodev.app.keycloakauthserver.service.impl;

import com.yastodev.app.keycloakauthserver.model.UserDto;
import com.yastodev.app.keycloakauthserver.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    private String realm;

    @Override
    public void saveUser(UserDto user) {
        UserRepresentation userRepresentation = getUserRepresentation(user);

        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);

        log.info("Status code{}", response.getStatus());

        if(!Objects.equals(201,response.getStatus())) {
            throw new RuntimeException("Status code" + response.getStatus());
        }

        log.info("User created");

        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(user.getUsername(), true);
        UserRepresentation userRepresentation1 = userRepresentations.getFirst();
        System.out.println(userRepresentation1.getEmail());
        senVerificationEmail(userRepresentation1.getId());

    }

    @Override
    public void senVerificationEmail(String userId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void forgotPassword(String username) {
        // TODO Auto-generated method stub

    }


    private static UserRepresentation getUserRepresentation(UserDto user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));
        return userRepresentation;
    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }
}
