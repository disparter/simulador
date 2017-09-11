package com.disparter.simulador.dao;

import java.util.List;

import javax.ejb.Local;

import com.disparter.simulador.model.Banca;

@Local
public interface BancaDAO {

	Banca obter(Long id);

	Banca salvar(Banca banca);

	void excluir(Banca banca);

	List<Banca> listar();

}
