package com.disparter.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement
    private final List<Message> mensagens = new ArrayList<>();

    public BusinessException(Message message) {
        super(message.getMsg());
        this.mensagens.add(message);
    }

    public BusinessException() {
    }

    public void add(Message message) {
        this.mensagens.add(message);
    }

    public List<Message> getMessages() {
        return this.mensagens;
    }

    public static void throwError(String Message) {
        throw new BusinessException(new ErrorMessage(Message));
    }

    public static void throwWarning(String Message) {
        throw new BusinessException(new WarningMessage(Message));
    }

    public static <T> void throwErrorIfNull(T obj, String Message) {
        if (obj == null)
            throwError(Message);
    }
    
    public static <T> void throwErrorIfNotNull(T obj, String Message) {
        if (obj != null)
            throwError(Message);
    }

    public static <T> void throwWarningIfNull(T obj, String Message) {
        if (obj == null)
            throwWarning(Message);
    }
    
    public static <T> void throwErrorIfFalse(Boolean condition, String Message) {
        if (!condition)
            throwError(Message);
    }
    
    public static <T> void throwWarningIfFalse(Boolean condition, String Message) {
        if (!condition)
            throwWarning(Message);
    }
    
    public static <T> void throwWarningIfNotTrue(Boolean condition, String Message) {
        if (!Boolean.TRUE.equals(condition))
            throwWarning(Message);
    }
    
    public static <T> void throwErrorIfTrue(Boolean condition, String Message) {
        if (condition)
            throwError(Message);
    }
    
    public static <T> void throwWarningIfTrue(Boolean condition, String Message) {
        if (condition)
            throwWarning(Message);
    }
    
    public static <T> void throwWarningIfEmpty(Collection<?> collection, String Message) {
        if (collection == null || collection.isEmpty())
            throwWarning(Message);
    }
    
}
