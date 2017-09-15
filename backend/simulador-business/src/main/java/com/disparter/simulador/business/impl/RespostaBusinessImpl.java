package com.disparter.simulador.business.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.disparter.business.BusinessBase;
import com.disparter.simulador.business.RespostaBusiness;
import com.disparter.simulador.business.util.UsuarioUtil;
import com.disparter.simulador.dao.RespostaDAO;
import com.disparter.simulador.model.Resposta;
import com.disparter.simulador.vo.RespostaVO;

@Stateless
public class RespostaBusinessImpl extends BusinessBase implements RespostaBusiness {

    @EJB
    private RespostaDAO respostaDAO;

	@Override
	public RespostaVO salvar(RespostaVO respostaVO) {
		Resposta resposta = respostaDAO.obter(UsuarioUtil.obterIdUsuarioLogado(), respostaVO.getQuestaoItem().getId());
		
		if(resposta == null) {
			resposta = respostaDAO.salvar(resposta);
		}

		return respostaVO;
		
	}
    
	@Override
	public void excluir(Long questaoItemId) {
		Resposta resposta = respostaDAO.obter(UsuarioUtil.obterIdUsuarioLogado(), questaoItemId);
		respostaDAO.excluir(resposta);
	}
	
}
