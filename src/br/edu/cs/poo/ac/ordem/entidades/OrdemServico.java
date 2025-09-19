package br.edu.cs.poo.ac.ordem.entidades;
import java.time.LocalDate;
import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter

public class OrdemServico {
	private Cliente cliente;
	private PrecoBase precoBase;
	private Notebook notebook;
	private Desktop desktop;
	private LocalDateTime dataHoraAbertura;
	private int prazoEmDias;
	private double valor;
	
	public LocalDate getDataEstimadaEntrega () {
		LocalDate dataAbertura = dataHoraAbertura.toLocalDate();
		return dataAbertura.plusDays(prazoEmDias);
	}
	
	/*
	public String getNumero () {
		String horaString = dataHoraAbertura.toString();
		LocalDate dataAbertura = dataHoraAbertura.toLocalDate();
		String dataString = dataAbertura.toString();	
		String stringConc = horaString + dataString;
		
		String cnpjCpf = cliente.getCpfCnpj();
		
		
		
		if (cnpjCpf.length() == 11){
			String stringReturn =  stringConc + cnpjCpf + "000";
			return stringReturn;
		}
	}
	*/
}
