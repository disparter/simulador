package com.disparter.simulador.business;

import javax.ejb.Local;

import com.disparter.simulador.dto.QuestaoDTO;
import com.disparter.simulador.vo.QuestaoVO;
import com.disparter.util.QueryDTO;

@Local
public interface QuestaoBusiness {

	QuestaoVO obter(Long id);

	QuestaoVO salvar(QuestaoVO bancaVO);

	void excluir(Long id);

	QueryDTO<QuestaoVO> consultar(QuestaoDTO dto);
    
}
