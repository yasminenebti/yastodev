package com.yastodev.app.events;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class CustomEventListener implements EventListenerProvider {
    @Override
    public void onEvent(Event event) {
        System.out.println("Event: " + event.getType());
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        System.out.println("Admin Event: " + adminEvent.getOperationType());
    }

    @Override
    public void close() {

    }
}
