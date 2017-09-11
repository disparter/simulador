package com.disparter.exception;

public class ErrorMessage extends Message {
    public ErrorMessage(String msg) {
        super(MessageType.ERROR, msg);
    }
}
