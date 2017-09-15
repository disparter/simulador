package com.disparter.simulador.service;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.disparter.simulador.business.RespostaBusiness;
import com.disparter.simulador.vo.RespostaVO;

@Path("/resposta")
@Produces(MediaType.APPLICATION_JSON)
public class RespostaService extends SuperService {

    @EJB
    private RespostaBusiness respostaBusiness;
    
    @PUT
    public Response put(RespostaVO resposta){
        return Response.ok(respostaBusiness.salvar(resposta)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
    	respostaBusiness.excluir(id);
        return Response.ok().build();
    }
    
    
}
