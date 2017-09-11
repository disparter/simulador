package com.disparter.exception;

public class WarningMessage extends Message {
    public WarningMessage(String msg) {
        super(MessageType.WARNING, msg);
    }
}
