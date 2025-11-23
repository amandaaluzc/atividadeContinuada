package br.edu.cs.poo.ac.ordem.entidades;

import java.time.LocalDate;

import br.edu.cs.poo.ac.utils.Registro;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter


public class FechamentoOrdemServico implements Registro {
	private String numeroOrdemServico;
	private LocalDate dataFechamento;
	private boolean pago;
	private String relatorioFinal;
	
	public String getId() {
		return this.numeroOrdemServico;	
	}
}
