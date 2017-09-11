package com.disparter.simulador.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.disparter.model.AuditableEntity;
import com.disparter.model.BaseEntity;
import com.disparter.util.QueryDTO;

public class GenericDAO<T extends BaseEntity<?>> {
    
    private static  final String DEFAULT_SYSTEM_USER_NAME = "system";
    
    @PersistenceContext(unitName="simuladorPU")
    private EntityManager entityManager;
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public T salvarOuAtualizar(T entity){
        if(entity.getId() != null){
            return atualizar(entity);
        } else {
            return salvar(entity);
        }
    }
    
    public T salvar(T entity){
    	
    	prepareInsert(entity);
    	prepareUpdate(entity);
        getEntityManager().persist(entity);
        return entity;
    }
    
    public T atualizar(T entity){
        prepareInsert(entity);
        prepareUpdate(entity);
        getEntityManager().merge(entity);
        return entity;
    }
    
    public void excluir(T entity){
        if(entity instanceof AuditableEntity<?>){
            AuditableEntity<?> item = (AuditableEntity<?>) entity;
            item.setAuditLastUpdate(new Date());
            item.setAuditLastUpdateUser(getUserName());
            item.setAuditDeleted(Boolean.TRUE);
            item.setAuditLastUpdateUser(getUserName());
            getEntityManager().merge(item);
        } else {
            getEntityManager().remove(entity);
        }
    }
    
    protected void configurarPaginacao(Query query, QueryDTO<?> dto){
        Integer first = ((dto.getCurrentPage() - 1) * dto.getPageSize());
        query.setFirstResult(first);
        query.setMaxResults(dto.getPageSize());
    }
    
    private void prepareUpdate(T entity){
        if(entity instanceof AuditableEntity<?>){          
            AuditableEntity<?> item = (AuditableEntity<?>) entity;
            configUpdate(item, new Date(), getUserName());
        }
    }
    
    private void prepareInsert(T entity){
        if(entity instanceof AuditableEntity<?>){
            AuditableEntity<?> item = (AuditableEntity<?>) entity;
            if(item.getAuditCreateUser() == null){
                Date actualDate = new Date();
                item.setAuditCreateUser(getUserName());
                item.setAuditeCreatedOn(actualDate);
            }
            
        }
    }
    
    private void configUpdate(AuditableEntity<?> auditable, Date date, String userName){
        auditable.setAuditLastUpdate(new Date());
        auditable.setAuditLastUpdateUser(userName);
        if(auditable.getAuditDeleted() == null){
            auditable.setAuditDeleted(Boolean.FALSE);
        }
    }
    
    public String getUserName(){
        return DEFAULT_SYSTEM_USER_NAME;
    }
    
    
}
