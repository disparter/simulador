package com.disparter.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import com.disparter.exception.BusinessException;
import com.disparter.util.Conversor;
import com.disparter.util.Merger;
import com.disparter.util.PublicId;
import com.disparter.util.QueryDTO;

public class BusinessBase {

    protected static final String SEPARADOR_PUBLICID = "-";

    private Validator validator;
    
    @Inject
    private Conversor conversor;
    
    @Inject
    private Merger merger;

    @PostConstruct
    public void initValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    protected Validator getValidator() {
        return this.validator;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected <T> void validate(T objeto) {
        BusinessException.throwErrorIfNull(objeto, "NULL OBJECT EXCEPTION");
        Set violations = getValidator().validate(objeto, new Class[0]);
        if (!(violations.isEmpty()))
            throw new ConstraintViolationException(new HashSet(violations));
    }

    protected <S> S merge(S target, S source) {
        return this.merger.merge(target, source);
    }
    
    protected <S, T> T converter(S entidade, Class<T> tipoVO, String[] excludeFields) {
        return this.conversor.converter(entidade, tipoVO, excludeFields);
    }

    protected <S, T> T converter(S from, Class<T> tipoVO) {
        return this.conversor.converter(from, tipoVO);
    }

    public <S, T> List<T> converter(List<S> from, Class<T> tipoVO){
        List<T> itens = new ArrayList<>();
        from.stream().forEach(it -> itens.add(converter(it,tipoVO)));
        return itens;
    }
    
    public <S, T> QueryDTO<T> converter(QueryDTO<S> from, Class<T> tipoVO){
        T filtro = converter(from.getFilter(), tipoVO);
        List<T> list = converter(from.getList(), tipoVO);
        QueryDTO<T> queryDTO = new QueryDTO<>();
        queryDTO.setCurrentPage(from.getCurrentPage());
        queryDTO.setFilter(filtro);
        queryDTO.setList(list);
        queryDTO.setOrder(from.getOrder());
        queryDTO.setOrderDirection(from.getOrderDirection());
        queryDTO.setPageSize(from.getPageSize());
        queryDTO.setTotalResults(from.getTotalResults());
        return queryDTO;
    }
    
    public void setConversor(Conversor conversor) {
        this.conversor = conversor;
    }
    
    protected String codificarPublicId(Long referencia1, Long referencia2) {
        return codificarPublicId(new PublicId(referencia1, referencia2));
    } 
    
    protected String codificarPublicId(PublicId publicId) {
        return Long.toHexString(publicId.getReferencia1Id()) + SEPARADOR_PUBLICID + Long.toHexString(publicId.getReferencia2Id());
    } 
    
    protected PublicId decodificarPublicId(String publicId){
        PublicId id = new PublicId();
        String[] partes = publicId.split(SEPARADOR_PUBLICID);
        id.setReferencia1Id(Long.parseLong(partes[0], 16));
        id.setReferencia2Id(Long.parseLong(partes[1], 16));
        return id;
    }
    
}
