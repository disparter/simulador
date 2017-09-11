package com.disparter.mail;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.disparter.exception.BusinessException;
import com.disparter.util.MessageAttatchment;

public class MessageBuilder {

    private Message message;
    private Session sessao;
    private List<String> destinatarios;
    private List<String> destinatariosCopiados;
    private List<String> destinatariosOcultos;
    private String assunto;
    private String html;
    private String from;
    private String alias;
    private List<MessageAttatchment> anexos;

    private MessageBuilder(Session sessao, String from, String alias) {
        this.sessao = sessao;
        this.destinatarios = new ArrayList<>();
        this.destinatariosCopiados = new ArrayList<>();
        this.destinatariosOcultos = new ArrayList<>();
        this.anexos = new ArrayList<>();
        this.from = from;
        this.alias = alias;
    }

    public static MessageBuilder create(Session sessao, String from, String alias) {
        return new MessageBuilder(sessao, from, alias);
    }

    public MessageBuilder comAssunto(String assunto) {
        this.assunto = assunto;
        return this;
    }

    public MessageBuilder comDestinatario(String destinatario) {
        destinatarios.add(destinatario);
        return this;
    }

    public MessageBuilder comDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
        return this;
    }

    public MessageBuilder comCopia(String destinatario) {
        destinatariosCopiados.add(destinatario);
        return this;
    }

    public MessageBuilder comCopias(List<String> destinatarios) {
        this.destinatariosCopiados = destinatarios;
        return this;
    }

    public MessageBuilder comCopiaOculta(String destinatario) {
        destinatariosOcultos.add(destinatario);
        return this;
    }

    public MessageBuilder comCopiasOcultas(List<String> destinatarios) {
        this.destinatariosOcultos = destinatarios;
        return this;
    }

    public MessageBuilder comHtml(String html) {
        this.html = html;
        return this;
    }

    public MessageBuilder comAnexo(Path anexo) {
        anexos.add(new MessageAttatchment(anexo));
        return this;
    }

    public MessageBuilder comAnexo(String anexo) {
        return comAnexo(Paths.get(anexo));
    }

    public MessageBuilder comAnexos(List<Path> anexos) {
        anexos.forEach(a -> this.anexos.add(new MessageAttatchment(a)));
        return this;
    }
    
    public MessageBuilder comAnexo(MessageAttatchment anexo) {
        anexos.add(anexo);
        return this;
    }

    public MessageBuilder comAnexosMD(List<MessageAttatchment> anexos) {
        this.anexos = anexos;
        return this;
    }

    public Message build() throws MessagingException {

        message = new MimeMessage(sessao);

        if (anexos.isEmpty()) {
            message.setContent(html, "text/html; charset=UTF-8");
        } else {
            montarAnexos();
        }

        message.setRecipients(RecipientType.TO, transformarDestinatarios(destinatarios));

        if (!destinatariosCopiados.stream().filter(d -> !d.isEmpty()).collect(Collectors.toList()).isEmpty()) {
            message.setRecipients(RecipientType.CC, transformarDestinatarios(destinatariosCopiados));
        }
        if (!destinatariosOcultos.stream().filter(d -> !d.isEmpty()).collect(Collectors.toList()).isEmpty()) {
            message.setRecipients(RecipientType.BCC, transformarDestinatarios(destinatariosOcultos));
        }

        message.setSubject(assunto);
        try {
            message.setFrom(new InternetAddress(from, alias));
        } catch (UnsupportedEncodingException e) {
            BusinessException.throwError(e.getMessage());
        }

        return message;
    }

    private Address transformarDestinatario(String destinatario) throws AddressException {
        return new InternetAddress(destinatario);
    }

    private Address[] transformarDestinatarios(List<String> destinatarios) throws AddressException {
        Address[] address = new Address[destinatarios.size()];
        for (int i = 0; i < destinatarios.size(); i++) {
            address[i] = transformarDestinatario(destinatarios.get(i));
        }
        return address;
    }

    private void montarAnexos() throws MessagingException {

        Multipart mp = new MimeMultipart();
        
        MimeBodyPart mbpHtml = new MimeBodyPart();
        mbpHtml.setContent(html,"text/html; charset=UTF-8");
        mp.addBodyPart(mbpHtml);

        for (MessageAttatchment anexo : anexos) {
            MimeBodyPart mbp = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(anexo.getPath().toFile());
            mbp.setDataHandler(new DataHandler(fds));
            mbp.setFileName(anexo.getFilename());
            mp.addBodyPart(mbp);
        }

        message.setContent(mp);
    }

}
