package com.yastodev.app.events;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.ResourceType;
import org.keycloak.models.KeycloakSession;

import java.util.logging.Logger;

public class CustomEventListener implements EventListenerProvider {
    private final Logger logger = Logger.getLogger(CustomEventListener.class.getName());
    private final KeycloakSession session;

    public CustomEventListener(KeycloakSession session) {
        this.session = session;
    }
    @Override
    public void onEvent(Event event) {
        System.out.println("Event: " + event.getType());
        if (event.getType() == EventType.REGISTER) {
            logger.info("User registered: " + event.getUserId());
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        System.out.println("Admin Event: " + adminEvent.getOperationType());
        if (adminEvent.getOperationType() == OperationType.CREATE && adminEvent.getResourceType() == ResourceType.USER) {
            logger.info("User registered: " + adminEvent.getRepresentation());
            logger.info("User Id: " + adminEvent.getAuthDetails().getUserId());
            logger.info("Realm Id: " + adminEvent.getAuthDetails().getRealmId());
            logger.info("Realm Name: " + adminEvent.getAuthDetails().getRealmName());

        }
    }

    @Override
    public void close() {

    }
}
