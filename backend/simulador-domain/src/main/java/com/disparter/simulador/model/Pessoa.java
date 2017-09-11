package com.disparter.simulador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.disparter.model.BaseEntity;

@Entity
@Table(schema = "simulador", name = "pessoa")
@SequenceGenerator(name = "pk_sequence_pessoa", schema = "simulador", sequenceName = "pessoa_id_sequence", allocationSize = 1)
public class Pessoa extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "int_pessoa_id")
	private Long id;

	@Column(name = "str_nome")
	private String nome;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}