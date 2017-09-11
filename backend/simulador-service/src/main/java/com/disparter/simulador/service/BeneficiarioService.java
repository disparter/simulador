package com.disparter.simulador.service;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.disparter.simulador.business.ProvaBusiness;

@Path("/prova")
@Produces(MediaType.APPLICATION_JSON)
public class BeneficiarioService extends SuperService {

    @EJB
    private ProvaBusiness provaBusiness;

    
}
