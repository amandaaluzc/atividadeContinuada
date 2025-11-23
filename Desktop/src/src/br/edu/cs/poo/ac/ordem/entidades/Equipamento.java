package br.edu.cs.poo.ac.ordem.entidades;

import java.io.Serializable;

public abstract class Equipamento implements Serializable {
	private static final long serialVersionUID = 1L;
	private String serial;
	private String descricao;
	private boolean ehNovo;
	private double valorEstimado;
	public Equipamento(String serial, String descricao, boolean ehNovo, double valorEstimado) {
		super();
		this.serial = serial;
		this.descricao = descricao;
		this.ehNovo = ehNovo;
		this.valorEstimado = valorEstimado;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isEhNovo() {
		return ehNovo;
	}
	public void setEhNovo(boolean ehNovo) {
		this.ehNovo = ehNovo;
	}
	public double getValorEstimado() {
		return valorEstimado;
	}
	public void setValorEstimado(double valorEstimado) {
		this.valorEstimado = valorEstimado;
	}
}
