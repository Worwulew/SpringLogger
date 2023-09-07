package com.yet.spring.core.loggers;

import com.yet.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger{
    private String filename;
    private File file;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public void logEvent(Event event) throws IOException {
        file = new File(filename);
        FileUtils.writeStringToFile(file, event.toString(), true);
    }

    public void init() throws IOException {
        this.file = new File(filename);
        file.canWrite();
    }
}
