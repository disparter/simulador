package com.disparter.auth.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;

@RequestScoped
@Model
@Default
public class Usuario {
    
    private String teste;
    
    public Usuario(){
        this.teste = "alomundo";
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
}
