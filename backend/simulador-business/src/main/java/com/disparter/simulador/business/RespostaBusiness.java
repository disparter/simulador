package com.disparter.simulador.business;

import javax.ejb.Local;

import com.disparter.simulador.vo.RespostaVO;

@Local
public interface RespostaBusiness {

	RespostaVO salvar(RespostaVO bancaVO);

	void excluir(Long id);
    
}
