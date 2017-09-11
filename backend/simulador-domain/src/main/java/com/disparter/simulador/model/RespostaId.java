package com.disparter.simulador.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RespostaId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "int_pessoa_id")
	private Long pessoaId;

	@Column(name = "int_questao_id")
	private Long questaoId;

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Long getQuestaoId() {
		return questaoId;
	}

	public void setQuestaoId(Long questaoId) {
		this.questaoId = questaoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pessoaId == null) ? 0 : pessoaId.hashCode());
		result = prime * result + ((questaoId == null) ? 0 : questaoId.hashCode());
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
		if (questaoId == null) {
			if (other.questaoId != null)
				return false;
		} else if (!questaoId.equals(other.questaoId))
			return false;
		return true;
	}

}