package com.disparter.exception;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class Message {

    private MessageType type;
    private String msg;

    public Message() {
    }

    public Message(MessageType type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    @XmlAttribute
    public MessageType getType() {
        return this.type;
    }

    @XmlAttribute
    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
