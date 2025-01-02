package com.yastodev.app.events;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

public class CustomEventListenerFactory implements EventListenerProviderFactory {
    private static final String EVENT_LISTENER_ID = "custom-event-listener";

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new CustomEventListener();
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return EVENT_LISTENER_ID;
    }

    @Override
    public int order() {
        return EventListenerProviderFactory.super.order();
    }

    @Override
    public List<ProviderConfigProperty> getConfigMetadata() {
        return EventListenerProviderFactory.super.getConfigMetadata();
    }
}
