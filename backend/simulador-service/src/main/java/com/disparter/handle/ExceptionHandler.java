package com.disparter.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.disparter.exception.BusinessException;
import com.disparter.exception.BusinessExceptionResponse;

@Provider
public class ExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;
    
    @Override
    public Response toResponse(RuntimeException throwedException) {
        Throwable cause = ExceptionUtils.getRootCause(throwedException);
        RuntimeException exception = (RuntimeException) cause;
        
        if(exception == null){
            exception = throwedException;
        }
        
        ResponseBuilder rb;
        exception.printStackTrace();
        
        if(exception instanceof BusinessException){
            rb = tratarException((BusinessException)exception);
        }else {
            rb = tratarException(exception);
        }
        
        return buildResponse(rb);
    }

    private ResponseBuilder tratarException(Exception exception) {
        return Response.status(Status.INTERNAL_SERVER_ERROR);
    }

    private ResponseBuilder tratarException(BusinessException exception) {
        return Response.status(Status.BAD_REQUEST)
                .entity(BusinessExceptionResponse.build(exception))
                .type(MediaType.APPLICATION_JSON);
    }
    
    private Response buildResponse(ResponseBuilder responseBuilder){
        for (String haderName : response.getHeaderNames()) {
            responseBuilder.header(haderName, response.getHeader(haderName));
        }
        return responseBuilder.build();
    }

}
