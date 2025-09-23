package br.edu.cs.poo.ac.ordem.entidades;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter

public class OrdemServico implements Serializable{
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
	
	
	public String getNumero () {
		String dataTotal = dataHoraAbertura.toString().replaceAll("\\D", "");
		String dataConvertida = dataTotal.substring(0,12);
		
		String idTipo = "";
		if (notebook == null) {
			idTipo = desktop.getIdTipo(); 
		}
		else {
			idTipo = notebook.getIdTipo();
		}
		
		
		//String stringConc = horaString + dataString;		
		String cnpjCpf = cliente.getCpfCnpj().replaceAll("\\D" , "");
		String stringReturn = "";		
		
		
		if (cnpjCpf.length() == 11){
			stringReturn +=  idTipo + dataConvertida + "000" + cnpjCpf;
		}
		else if (cnpjCpf.length() == 14) {
			stringReturn +=  idTipo + dataConvertida + cnpjCpf;
		}	
		
		return stringReturn;
	}
	
}
