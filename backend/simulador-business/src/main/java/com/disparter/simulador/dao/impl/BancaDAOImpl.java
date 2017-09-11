package com.disparter.simulador.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.disparter.hibernate.QueryBuilder;
import com.disparter.simulador.dao.BancaDAO;
import com.disparter.simulador.dao.GenericDAO;
import com.disparter.simulador.model.Banca;

@Stateless
public class BancaDAOImpl extends GenericDAO<Banca> implements BancaDAO {

	@Override
	public Banca obter(Long id) {
		try {
			return getEntityManager().find(Banca.class, id);
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Banca> listar() {
		return QueryBuilder.create("FROM Banca b")
				.build(getEntityManager(), Banca.class)
				.getResultList();
	}


}
