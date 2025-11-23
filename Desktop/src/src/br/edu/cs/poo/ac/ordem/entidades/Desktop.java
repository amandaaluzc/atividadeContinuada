	package br.edu.cs.poo.ac.ordem.entidades;

public class Desktop extends Equipamento {
	private static final long serialVersionUID = 1L;
	private boolean ehServidor;
	public String getIdTipo() {
		return "DE";  
	}
	public boolean isEhServidor() {
		return ehServidor;
	}
	public void setEhServidor(boolean ehServidor) {
		this.ehServidor = ehServidor;
	}
	public Desktop(String serial, String descricao, boolean ehNovo, double valorEstimado, boolean ehServidor) {
		super(serial, descricao, ehNovo, valorEstimado);
		this.ehServidor = ehServidor;
	}
}
