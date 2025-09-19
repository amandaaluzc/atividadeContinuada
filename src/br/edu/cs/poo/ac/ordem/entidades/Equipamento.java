package br.edu.cs.poo.ac.ordem.entidades;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter

public class Equipamento {
	private String descricao;
	private Dificuldade dificuldade; //Precisa Verificar
	private boolean ehNovo;
	private double valorEstimado;
	
	
	
}
