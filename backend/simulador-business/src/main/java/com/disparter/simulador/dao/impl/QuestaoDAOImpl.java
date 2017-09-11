package com.disparter.simulador.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.disparter.hibernate.QueryBuilder;
import com.disparter.hibernate.QueryBuilder.ConditionType;
import com.disparter.simulador.dao.GenericDAO;
import com.disparter.simulador.dao.QuestaoDAO;
import com.disparter.simulador.model.Banca;
import com.disparter.simulador.model.Questao;
import com.disparter.util.QueryDTO;

@Stateless
public class QuestaoDAOImpl extends GenericDAO<Questao> implements QuestaoDAO {

	@Override
	public Questao obter(Long id) {
		try {
			return getEntityManager().find(Questao.class, id);
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Questao> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryDTO<Questao> consultar(QueryDTO<Questao> queryDTO) {
		tratar(queryDTO);
		QueryBuilder qb = QueryBuilder.create("FROM Questao q")
				 		   .addCondition(queryDTO.getFilter().getAssunto(), "q.assunto", ConditionType.AND)
				 		   .addCondition(queryDTO.getFilter().getBanca().getId(), "q.banca.id", ConditionType.AND);
	                
	    Long total = qb.buildCountQuery(getEntityManager()).getSingleResult();
	    queryDTO.setTotalResults(total.intValue());
	        
	    qb.withResultList("q");
	        		
	    TypedQuery<Questao> query = qb.build(getEntityManager(), Questao.class);
	    configurarPaginacao(query, queryDTO);
	    queryDTO.setList(query.getResultList());
	    return queryDTO;
	}

	private void tratar(QueryDTO<Questao> queryDTO) {
		if(queryDTO.getFilter() == null) {
			queryDTO.setFilter(new Questao());
		}
		if(queryDTO.getFilter().getBanca() == null) {
			queryDTO.getFilter().setBanca(new Banca());
		}
	}


}
