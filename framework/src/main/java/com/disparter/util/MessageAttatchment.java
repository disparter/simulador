package com.disparter.util;

import java.io.Serializable;
import java.nio.file.Path;

public class MessageAttatchment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Path path;
    private String filename;
    
    public MessageAttatchment(Path path) {
        super();
        this.path = path;
        this.filename = path.toFile().getName();
    }

    public MessageAttatchment(Path path, String filename) {
        super();
        this.path = path;
        this.filename = filename;
    }
    
    public Path getPath() {
        return path;
    }
    public void setPath(Path path) {
        this.path = path;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    
    

}
