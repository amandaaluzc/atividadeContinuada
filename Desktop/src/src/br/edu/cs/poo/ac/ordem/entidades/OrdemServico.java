package br.edu.cs.poo.ac.ordem.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static br.edu.cs.poo.ac.utils.ValidadorCPFCNPJ.isCPF;

public class OrdemServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private PrecoBase precoBase;	
	private Notebook notebook;	
	private Desktop desktop;
	private LocalDateTime dataHoraAbertura;
	private int prazoEmDias;
	private double valor;

	public LocalDate getDataEstimadaEntrega() {
		return dataHoraAbertura.plusDays(prazoEmDias).toLocalDate();
	} 
	public String getNumero() {
		String tipoEq = "";		
		if (desktop != null) {
			tipoEq = desktop.getIdTipo();
		} else if (notebook != null) {
			tipoEq = notebook.getIdTipo();
		}		
		String parteCpfCnpj = "";
		if (isCPF(cliente.getCpfCnpj())) {
			parteCpfCnpj = "000";
		}
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dhaString = dataHoraAbertura.format(formatter);
		return tipoEq + dhaString + parteCpfCnpj + cliente.getCpfCnpj();    
				
	}
	public OrdemServico(Cliente cliente, PrecoBase precoBase, Notebook notebook, Desktop desktop,
			LocalDateTime dataHoraAbertura, int prazoEmDias, double valor) {
		super();
		this.cliente = cliente;
		this.precoBase = precoBase;
		this.notebook = notebook;
		this.desktop = desktop;
		this.dataHoraAbertura = dataHoraAbertura;
		this.prazoEmDias = prazoEmDias;
		this.valor = valor;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public PrecoBase getPrecoBase() {
		return precoBase;
	}
	public void setPrecoBase(PrecoBase precoBase) {
		this.precoBase = precoBase;
	}
	public Notebook getNotebook() {
		return notebook;
	}
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	public Desktop getDesktop() {
		return desktop;
	}
	public void setDesktop(Desktop desktop) {
		this.desktop = desktop;
	}
	public LocalDateTime getDataHoraAbertura() {
		return dataHoraAbertura;
	}
	public void setDataHoraAbertura(LocalDateTime dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}
	public int getPrazoEmDias() {
		return prazoEmDias;
	}
	public void setPrazoEmDias(int prazoEmDias) {
		this.prazoEmDias = prazoEmDias;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
