package com.disparter.simulador.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.disparter.simulador.business.PessoaBusiness;
import com.disparter.simulador.dao.PessoaDAO;

@Stateless
public class PessoaBusinessImpl implements PessoaBusiness {

    @EJB
    private PessoaDAO pessoaDAO;
    
  
}
