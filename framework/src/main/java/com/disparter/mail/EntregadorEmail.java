package com.disparter.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class EntregadorEmail {

    public void enviar(Message message) throws MessagingException {
        Transport.send(message);
    }
}
    
    
 
