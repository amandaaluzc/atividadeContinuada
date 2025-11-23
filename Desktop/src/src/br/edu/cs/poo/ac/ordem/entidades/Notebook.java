package br.edu.cs.poo.ac.ordem.entidades;

public class Notebook extends Equipamento {	
	private static final long serialVersionUID = 1L;
	private boolean carregaDadosSensiveis;
	public String getIdTipo() {
		return "NO";
	}
	public Notebook(String serial, String descricao, boolean ehNovo, double valorEstimado,
			boolean carregaDadosSensiveis) {
		super(serial, descricao, ehNovo, valorEstimado);
		this.carregaDadosSensiveis = carregaDadosSensiveis;
	}
	public boolean isCarregaDadosSensiveis() {
		return carregaDadosSensiveis;
	}
	public void setCarregaDadosSensiveis(boolean carregaDadosSensiveis) {
		this.carregaDadosSensiveis = carregaDadosSensiveis;
	}
}
