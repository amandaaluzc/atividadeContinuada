package br.edu.cs.poo.ac.ordem.entidades;

public class Contato {
	private String email;
	private String celular;
	private boolean ehZap;
	
	public Contato (String email , String celular , boolean ehZap) {
		this.email = email;
		this.celular = celular;
		this.ehZap = ehZap;
	}
	
	public final String getEmail () {
		return email;
	}
	
	public final String getCelular () {
		return celular ;
	}
	
	public final boolean getEhZap () {
		return ehZap;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEhZap(boolean ehZap) {
		this.ehZap = ehZap;
	}
}
