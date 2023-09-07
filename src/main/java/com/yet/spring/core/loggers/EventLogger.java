package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

import java.io.IOException;

public interface EventLogger {
    public void logEvent(Event event) throws IOException;
}
