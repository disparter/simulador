package com.disparter.simulador.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.disparter.business.BusinessBase;
import com.disparter.simulador.business.BancaBusiness;
import com.disparter.simulador.dao.BancaDAO;
import com.disparter.simulador.model.Banca;
import com.disparter.simulador.vo.BancaVO;

@Stateless
public class BancaBusinessImpl extends BusinessBase implements BancaBusiness {

    @EJB
    private BancaDAO bancaDAO;

	@Override
	public BancaVO obter(Long id) {
		Banca banca = bancaDAO.obter(id);
		return converter(banca, BancaVO.class);
	}
	
	@Override
	public List<BancaVO> listar() {
		List<Banca> bancas = bancaDAO.listar();
		return converter(bancas, BancaVO.class);
	}
	
	@Override
	public BancaVO salvar(BancaVO bancaVO) {
		Banca banca = converter(bancaVO, Banca.class);
		if(bancaVO.getId() != null) {
			Banca b = bancaDAO.obter(bancaVO.getId());
			banca = merge(b, banca);
		}else{
			banca = bancaDAO.salvar(banca);
		}
		
		return converter(banca, BancaVO.class);
	}
    
	@Override
	public void excluir(Long id) {
		Banca banca = bancaDAO.obter(id);
		bancaDAO.excluir(banca);
	}
	
}
