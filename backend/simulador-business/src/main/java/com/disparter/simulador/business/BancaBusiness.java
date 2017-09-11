package com.disparter.simulador.business;

import java.util.List;

import javax.ejb.Local;

import com.disparter.simulador.vo.BancaVO;

@Local
public interface BancaBusiness {

	BancaVO obter(Long id);

	BancaVO salvar(BancaVO bancaVO);

	List<BancaVO> listar();

	void excluir(Long id);
    
}
