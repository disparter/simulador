package com.disparter.simulador.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.disparter.simulador.business.ProvaBusiness;
import com.disparter.simulador.dao.ProvaDAO;

@Stateless
public class ProvaBusinessImpl implements ProvaBusiness {

    @EJB
    private ProvaDAO beneficiarioDAO;
    
  
}
