package com.disparter.simulador.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.disparter.hibernate.QueryBuilder;
import com.disparter.hibernate.QueryBuilder.ConditionType;
import com.disparter.simulador.dao.GenericDAO;
import com.disparter.simulador.dao.RespostaDAO;
import com.disparter.simulador.model.Resposta;

@Stateless
public class RespostaDAOImpl extends GenericDAO<Resposta> implements RespostaDAO {

	@Override
	public Resposta obter(Long pessoaId, Long questaoItemId) {
		try {
			return QueryBuilder.create("FROM Resposta r")
					   .addCondition(pessoaId, "r.pessoa.id", ConditionType.AND)
					   .addCondition(questaoItemId, "r.questaoItem.id", ConditionType.AND)
					   .build(getEntityManager(), Resposta.class)
					   .getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}


}
