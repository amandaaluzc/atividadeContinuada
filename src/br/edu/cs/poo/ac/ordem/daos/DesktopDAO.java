package br.edu.cs.poo.ac.ordem.daos;
import java.io.Serializable;

import br.edu.cs.poo.ac.ordem.entidades.Desktop;
//O identificador único, por objeto, de Desktop é a concatenação do retorno 
//do método getTipo com o atributo serial.   


public class DesktopDAO extends DAOGenerico {
	public DesktopDAO () {
		super(Desktop.class);
	}
}