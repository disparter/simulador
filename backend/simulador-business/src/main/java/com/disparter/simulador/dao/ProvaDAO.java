package com.disparter.simulador.dao;

import java.util.List;

import javax.ejb.Local;

import com.disparter.simulador.model.Questao;
import com.disparter.util.QueryDTO;

@Local
public interface ProvaDAO {

    Questao obter(Long empresaId);

    Questao salvar(Questao beneficiario);

    QueryDTO<Questao> consultar(QueryDTO<Questao> dto);

    Questao atualizar(Questao beneficiario);

    List<Questao> listar();

}
