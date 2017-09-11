package com.disparter.util;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class ExporterDTO<T> extends QueryDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String filename;
    private HashMap<String, Object> parameters;
    private ByteArrayOutputStream outputStream;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, Object>  parameters) {
        this.parameters = parameters;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

}
