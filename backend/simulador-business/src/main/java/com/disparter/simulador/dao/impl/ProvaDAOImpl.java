package com.disparter.simulador.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.disparter.hibernate.QueryBuilder;
import com.disparter.hibernate.QueryBuilder.ConditionType;
import com.disparter.simulador.dao.ProvaDAO;
import com.disparter.simulador.dao.GenericDAO;
import com.disparter.simulador.model.Questao;
import com.disparter.util.QueryDTO;

@Stateless
public class ProvaDAOImpl extends GenericDAO<Questao> implements ProvaDAO {

    @Override
    public Questao obter(Long empresaId) {
        try {
            return QueryBuilder
                    .create("FROM Beneficiario")
                    .addCondition(empresaId, "id", ConditionType.AND)
                    .build(getEntityManager(), Questao.class)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public QueryDTO<Questao> consultar(QueryDTO<Questao> queryDTO) {
        QueryBuilder qb = QueryBuilder.
                create("FROM Beneficiario b ")
                .append("JOIN b.empresa e");
                
        Long total = qb.buildCountQuery(getEntityManager()).getSingleResult();
        queryDTO.setTotalResults(total.intValue());
        
        qb.withResultList("new Beneficiario(b.id, e.razaoSocial)");
        TypedQuery<Questao> query = qb.build(getEntityManager(), Questao.class);
        configurarPaginacao(query, queryDTO);
        queryDTO.setList(query.getResultList());
        return queryDTO;
    }
    
    @Override
    public List<Questao> listar() {
        return QueryBuilder
                .create("FROM Beneficiario b")
                .append("JOIN b.empresa e")
                .withResultList("new Beneficiario(b.id, e.razaoSocial)")
                .build(getEntityManager(), Questao.class)
                .getResultList();
    }

}
