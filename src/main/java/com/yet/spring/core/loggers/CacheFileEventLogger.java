package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache = new ArrayList<>();

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    @Override
    public void init() throws IOException {
        super.init();
    }

    private void destroy() throws IOException {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache() throws IOException {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }
}
