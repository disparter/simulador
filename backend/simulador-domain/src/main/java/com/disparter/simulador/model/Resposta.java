package com.disparter.simulador.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.disparter.model.BaseEntity;

@Entity
@Table(schema = "simulador", name = "resposta")
public class Resposta extends BaseEntity<RespostaId> {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RespostaId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "int_pessoa_id", insertable = false, updatable = false)
	@ForeignKey(name = "pessoa_resposta_fk")
	private Pessoa pessoa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "int_questao_id", insertable = false, updatable = false)
	@ForeignKey(name = "questao_resposta_fk")
	private Questao questao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "int_questao_item_id")
	@ForeignKey(name = "questao_item_resposta_fk")
	private QuestaoItem questaoItem;

	@Override
	public RespostaId getId() {
		return id;
	}

	public void setId(RespostaId id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public QuestaoItem getQuestaoItem() {
		return questaoItem;
	}

	public void setQuestaoItem(QuestaoItem questaoItem) {
		this.questaoItem = questaoItem;
	}

}