package com.disparter.simulador.dao;

import javax.ejb.Local;

import com.disparter.simulador.model.Resposta;

@Local
public interface RespostaDAO {

	Resposta obter(Long pessoaId, Long questaoItemId);

	Resposta salvar(Resposta resposta);

	void excluir(Resposta resposta);

}
