package com.disparter.simulador.vo;

import java.io.Serializable;

public class RespostaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private PessoaVO pessoa;
	private QuestaoItemVO questaoItem;

	public PessoaVO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaVO pessoa) {
		this.pessoa = pessoa;
	}

	public QuestaoItemVO getQuestaoItem() {
		return questaoItem;
	}

	public void setQuestaoItem(QuestaoItemVO questaoItem) {
		this.questaoItem = questaoItem;
	}

}
