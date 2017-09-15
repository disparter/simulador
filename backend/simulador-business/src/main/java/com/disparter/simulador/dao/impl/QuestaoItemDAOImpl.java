package com.disparter.simulador.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.disparter.hibernate.QueryBuilder;
import com.disparter.hibernate.QueryBuilder.ConditionType;
import com.disparter.simulador.dao.GenericDAO;
import com.disparter.simulador.dao.QuestaoItemDAO;
import com.disparter.simulador.model.QuestaoItem;

@Stateless
public class QuestaoItemDAOImpl extends GenericDAO<QuestaoItem> implements QuestaoItemDAO {

	@Override
	public List<QuestaoItem> listar(Long questaoId) {
		return QueryBuilder.create("FROM QuestaoItem qi")
						   .addCondition(questaoId, "qi.questao.id", ConditionType.AND)
						   .build(getEntityManager(), QuestaoItem.class)
						   .getResultList();
	}

	@Override
	public QuestaoItem obter(Long id) {
		try {
			return QueryBuilder.create("FROM QuestaoItem qi")
					   .addCondition(id, "qi.id", ConditionType.AND)
					   .build(getEntityManager(), QuestaoItem.class)
					   .getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}


}
