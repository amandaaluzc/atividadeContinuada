package br.edu.cs.poo.ac.utils;

public class ValidadorCPFCNPJ {
	public static ResultadoValidacaoCPFCNPJ validarCPFCNPJ(String cpfCnpj) {
		
	}
	
	
	public static boolean isCPF(String valor) {
		String newCpf = valor.trim().replaceAll("\\D", "");
		if (newCpf.length() == 11) {
			return true;
		}
		return false;
	}
	
	public static boolean isCNPJ(String valor) {
		String newCnpj = valor.trim().replaceAll("\\D", "");
		if (newCnpj.length() == 14) {
			return true;
		}
		return false;
	}
	public static ErroValidacaoCPFCNPJ validarCPF(String cpf) {
		
	}
	public static ErroValidacaoCPFCNPJ validarCNPJ(String cnpj) {}
	
	private static boolean isDigitoVerificadorValidoCPF(String cpf) {}
	private static boolean isDigitoVerificadorValidoCNPJ(String cnpj) {}
}
