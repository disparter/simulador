package com.disparter.simulador.dao;

import java.util.List;

import javax.ejb.Local;

import com.disparter.simulador.model.QuestaoItem;

@Local
public interface QuestaoItemDAO {

	List<QuestaoItem> listar(Long questaoId);

	void excluir(QuestaoItem qi);

	QuestaoItem salvar(QuestaoItem qi);

	QuestaoItem obter(Long id);

}
