package com.disparter.simulador.vo;

import java.io.Serializable;

public class QuestaoItemVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String enunciado;
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

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

}
