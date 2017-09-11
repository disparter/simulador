package com.disparter.exception;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BusinessExceptionResponse {

    private List<Message> erros;

    public BusinessExceptionResponse() {

    }

    private BusinessExceptionResponse(Message erro) {
        erros = Arrays.asList(erro);
    }

    private BusinessExceptionResponse(BusinessException erro) {
        erros = erro.getMessages();
    }

    @XmlElement
    public List<Message> getErros() {
        return erros;
    }

    void add(Message erro) {
        getErros().add(erro);
    }

    public static BusinessExceptionResponse build(BusinessException erro) {
        return new BusinessExceptionResponse(erro);
    }

    public static BusinessExceptionResponse build(Message erro) {
        return new BusinessExceptionResponse(erro);
    }
    
}
