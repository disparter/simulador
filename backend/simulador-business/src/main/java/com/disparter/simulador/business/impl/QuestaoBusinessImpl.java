package com.disparter.simulador.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.disparter.business.BusinessBase;
import com.disparter.simulador.business.QuestaoBusiness;
import com.disparter.simulador.dao.QuestaoDAO;
import com.disparter.simulador.dao.QuestaoItemDAO;
import com.disparter.simulador.dto.QuestaoDTO;
import com.disparter.simulador.model.Questao;
import com.disparter.simulador.model.QuestaoItem;
import com.disparter.simulador.vo.QuestaoItemVO;
import com.disparter.simulador.vo.QuestaoVO;
import com.disparter.util.QueryDTO;

@Stateless
public class QuestaoBusinessImpl extends BusinessBase implements QuestaoBusiness {

    @EJB
    private QuestaoDAO questaoDAO;
    
    @EJB
    private QuestaoItemDAO questaoItemDAO;

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
		
		salvarItens(questao.getId(), questaoVO.getItens());
		
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
	
	private void salvar(Long id, QuestaoItem qi) {
		if(qi.getId() != null) {
			QuestaoItem questaoItem = questaoItemDAO.obter(qi.getId());
			merge(questaoItem, qi);
		}else {
			Questao questao = new Questao();
			questao.setId(id);
			qi.setQuestao(questao);
			questaoItemDAO.salvar(qi);
		}
	}
	
	private void salvarItens(Long id, List<QuestaoItemVO> itensVO) {
		List<QuestaoItem> itens = converter(itensVO, QuestaoItem.class);
		itens.stream().forEach(qi -> salvar(id, qi));
	}
}
