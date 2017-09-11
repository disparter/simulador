package com.disparter.simulador.vo;

import java.io.Serializable;
import java.util.List;

public class QuestaoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String enunciado;
	private String prova;
	private String assunto;
	private BancaVO banca;

	private List<QuestaoItemVO> itens;

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

	public String getProva() {
		return prova;
	}

	public void setProva(String prova) {
		this.prova = prova;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public BancaVO getBanca() {
		return banca;
	}

	public void setBanca(BancaVO banca) {
		this.banca = banca;
	}

	public List<QuestaoItemVO> getItens() {
		return itens;
	}

	public void setItens(List<QuestaoItemVO> itens) {
		this.itens = itens;
	}

}
