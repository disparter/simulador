package com.disparter.simulador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.disparter.model.BaseEntity;

@Entity
@Table(schema = "simulador", name = "questao_item")
@SequenceGenerator(name = "pk_sequence_questao_item", schema = "simulador", sequenceName = "questao_item_id_sequence", allocationSize = 1)
public class QuestaoItem extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "int_questao_item_id")
	private Long id;

	@Column(name = "str_enunciado", length = 10000)
	private String enunciado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "int_questao_id")
	@ForeignKey(name = "questao_item_fk")
	private Questao questao;
	
	@Column(name = "st_correta")
	private Boolean correta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

}