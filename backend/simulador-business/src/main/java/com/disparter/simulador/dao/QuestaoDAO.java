package com.disparter.simulador.dao;

import java.util.List;

import javax.ejb.Local;

import com.disparter.simulador.model.Questao;
import com.disparter.util.QueryDTO;

@Local
public interface QuestaoDAO {

	Questao obter(Long id);

	Questao salvar(Questao questao);

	void excluir(Questao questao);

	List<Questao> listar();

	QueryDTO<Questao> consultar(QueryDTO<Questao> filtro);

}
