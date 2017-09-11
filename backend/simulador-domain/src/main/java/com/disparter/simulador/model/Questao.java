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
@Table(schema = "simulador", name = "questao")
@SequenceGenerator(name = "pk_sequence_questao", schema = "simulador", sequenceName = "questao_id_sequence", allocationSize = 1)
public class Questao extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "int_questao_id")
	private Long id;

	@Column(name = "str_enunciado", length = 10000)
	private String enunciado;
	
	@Column(name = "str_prova")
	private String prova;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "int_banca_id")
	@ForeignKey(name = "questao_banca_fk")
	private Banca banca;

	@Override
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

	public Banca getBanca() {
		return banca;
	}

	public void setBanca(Banca banca) {
		this.banca = banca;
	}

	public String getProva() {
		return prova;
	}

	public void setProva(String prova) {
		this.prova = prova;
	}

}