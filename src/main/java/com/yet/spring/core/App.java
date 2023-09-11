package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.loggers.CacheFileEventLogger;
import com.yet.spring.core.loggers.EventLogger;
import com.yet.spring.core.loggers.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;
    private CacheFileEventLogger defaultLogger;

    public void setDefaultLogger(CacheFileEventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(Event event, String msg, EventType type) throws IOException {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = ctx.getBean(App.class);

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "User 1 " + app.client.getGreeting(), EventType.INFO);

        event = ctx.getBean(Event.class);
        app.logEvent(event, "User 2 " + app.client.getGreeting(), EventType.ERROR);

        event = ctx.getBean(Event.class);
        app.logEvent(event, "User 3 " + app.client.getGreeting(), null);

        ctx.close();
    }
}
