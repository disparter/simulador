package com.disparter.simulador.service;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.disparter.simulador.business.BancaBusiness;
import com.disparter.simulador.vo.BancaVO;

@Path("/banca")
@Produces(MediaType.APPLICATION_JSON)
public class BancaService extends SuperService {

    @EJB
    private BancaBusiness bancaBusiness;

    @GET
    public Response list(){
        return Response.ok(bancaBusiness.listar()).build();
    }
    
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id){
        return Response.ok(bancaBusiness.obter(id)).build();
    }
    
    @PUT
    public Response put(BancaVO banca){
        return Response.ok(bancaBusiness.salvar(banca)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
    	bancaBusiness.excluir(id);
        return Response.ok().build();
    }
    
    
}
