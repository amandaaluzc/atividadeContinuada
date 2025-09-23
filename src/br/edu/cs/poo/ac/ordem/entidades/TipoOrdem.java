package br.edu.cs.poo.ac.ordem.entidades;

import java.io.Serializable;

public enum TipoOrdem implements Serializable {
	MANUTENCAO(1, "Manutenção"),
	CONFIGURACAO(2, "Configuração"),
	UPGRADE(3, "Upgrade");
	
	private final int codigo;
	private final String nome;
	
	private TipoOrdem(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getNome(){
		return nome;
	}
	
	public static TipoOrdem getTipoOrdem(int codigo) {
			for (TipoOrdem tipo : TipoOrdem.values()) {
				if (codigo == tipo.getCodigo()) {
					return tipo;
				}
			}
			return null;
	}
}
