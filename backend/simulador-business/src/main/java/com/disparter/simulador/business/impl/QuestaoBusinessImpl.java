package com.disparter.simulador.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.disparter.business.BusinessBase;
import com.disparter.simulador.business.QuestaoBusiness;
import com.disparter.simulador.dao.QuestaoDAO;
import com.disparter.simulador.dto.QuestaoDTO;
import com.disparter.simulador.model.Questao;
import com.disparter.simulador.vo.QuestaoVO;
import com.disparter.util.QueryDTO;

@Stateless
public class QuestaoBusinessImpl extends BusinessBase implements QuestaoBusiness {

    @EJB
    private QuestaoDAO questaoDAO;

	@Override
	public QuestaoVO obter(Long id) {
		Questao questao = questaoDAO.obter(id);
		return converter(questao, QuestaoVO.class);
	}
	
	@Override
	public QuestaoVO salvar(QuestaoVO questaoVO) {
		Questao questao = converter(questaoVO, Questao.class);
		if(questaoVO.getId() != null) {
			Questao b = questaoDAO.obter(questaoVO.getId());
			questao = merge(b, questao);
		}else{
			questao = questaoDAO.salvar(questao);
		}
		
		return converter(questao, QuestaoVO.class);
	}
    
	@Override
	public void excluir(Long id) {
		Questao questao = questaoDAO.obter(id);
		questaoDAO.excluir(questao);
	}

	@Override
	public QueryDTO<QuestaoVO> consultar(QuestaoDTO dto) {
		QueryDTO<Questao> q = questaoDAO.consultar(converter(dto, Questao.class));
		return converter(q, QuestaoVO.class);
	}
	
}
