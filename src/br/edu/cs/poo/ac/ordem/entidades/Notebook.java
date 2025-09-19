package br.edu.cs.poo.ac.ordem.entidades;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter

public class Notebook extends Equipamento{
	private boolean carregaDadosSensiveis;
	
}	
