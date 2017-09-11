package com.disparter.simulador.service;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.disparter.simulador.business.QuestaoBusiness;
import com.disparter.simulador.dto.QuestaoDTO;
import com.disparter.simulador.vo.QuestaoVO;

@Path("/questao")
@Produces(MediaType.APPLICATION_JSON)
public class QuestaoService extends SuperService {

    @EJB
    private QuestaoBusiness questaoBusiness;

    @POST
    public Response query(QuestaoDTO dto){
        return Response.ok(questaoBusiness.consultar(dto)).build();
    }
    
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id){
        return Response.ok(questaoBusiness.obter(id)).build();
    }
    
    @PUT
    public Response put(QuestaoVO questao){
        return Response.ok(questaoBusiness.salvar(questao)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
    	questaoBusiness.excluir(id);
        return Response.ok().build();
    }
    
    
}
