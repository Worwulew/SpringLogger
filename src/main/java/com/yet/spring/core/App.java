package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger consoleEventLogger;

    public App(Client client, EventLogger consoleEventLogger) {
        this.client = client;
        this.consoleEventLogger = consoleEventLogger;
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        consoleEventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = ctx.getBean(App.class);

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "User 1 joined");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "User 2 joined");
    }
}
