package br.edu.cs.poo.ac.ordem.entidades;

public enum Dificuldade {
	NORMAL(1, "Normal"),
	DIFICIL(2, "Difícil");
	
	private final int codigo;
	private final String nome;
	
	private Dificuldade(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	
	public static Dificuldade getDificuldade(int codigo) {
		for (Dificuldade tipo : Dificuldade.values()) {
			if (codigo == tipo.getCodigo()) {
				return tipo;
			}
		}
		return null;
	}
}
