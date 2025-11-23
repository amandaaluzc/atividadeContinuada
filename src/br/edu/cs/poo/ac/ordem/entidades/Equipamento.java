package br.edu.cs.poo.ac.ordem.entidades;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import br.edu.cs.poo.ac.utils.Registro;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public abstract class Equipamento implements Registro {
	private String serial;
	private String descricao; 
	private boolean ehNovo;
	private double valorEstimado;
	
	public abstract String getIdTipo();
}
