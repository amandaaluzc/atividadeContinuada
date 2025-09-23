package br.edu.cs.poo.ac.ordem.entidades;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter


public class FechamentoOrdemServico {
	private String numeroOrdemServico;
	private LocalDate dataFechamento;
	private boolean pago;
	private String relatorioFinal;
	
	
}
