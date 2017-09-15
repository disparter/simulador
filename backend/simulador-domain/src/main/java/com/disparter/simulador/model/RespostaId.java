package com.disparter.simulador.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RespostaId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "int_pessoa_id")
	private Long pessoaId;

	@Column(name = "int_questaoItem_item_id")
	private Long questaoItemId;

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Long getQuestaoItemId() {
		return questaoItemId;
	}

	public void setQuestaoItemId(Long questaoItemId) {
		this.questaoItemId = questaoItemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pessoaId == null) ? 0 : pessoaId.hashCode());
		result = prime * result + ((questaoItemId == null) ? 0 : questaoItemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespostaId other = (RespostaId) obj;
		if (pessoaId == null) {
			if (other.pessoaId != null)
				return false;
		} else if (!pessoaId.equals(other.pessoaId))
			return false;
		if (questaoItemId == null) {
			if (other.questaoItemId != null)
				return false;
		} else if (!questaoItemId.equals(other.questaoItemId))
			return false;
		return true;
	}

}