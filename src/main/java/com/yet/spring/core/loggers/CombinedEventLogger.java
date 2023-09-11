package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

import java.io.IOException;
import java.util.Collection;

public class CombinedEventLogger implements EventLogger{
    private Collection<EventLogger> eventLoggers;

    public CombinedEventLogger(Collection<EventLogger> eventLoggers) {
        this.eventLoggers = eventLoggers;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        for (EventLogger e: eventLoggers) {
            e.logEvent(event);
        }
    }
}
