package br.edu.cs.poo.ac.utils;

public class ValidadorCPFCNPJ {
	
	public static ResultadoValidacaoCPFCNPJ validarCPFCNPJ(String cpfCnpj) {
		boolean cpf = ValidadorCPFCNPJ.isCPF(cpfCnpj);
		boolean cnpj = ValidadorCPFCNPJ.isCNPJ(cpfCnpj);
		
		ErroValidacaoCPFCNPJ tipoErro = null;
		
		if (cpf) {
			tipoErro = validarCPF(cpfCnpj);
		} else if (cnpj) {
			tipoErro = validarCNPJ(cpfCnpj);
		} else {
			tipoErro = ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ;
		}
		return new ResultadoValidacaoCPFCNPJ(cpf, cnpj, tipoErro);
	}
	
	public static boolean isCPF(String valor) {
		if (valor == null) return false;
		String newCpf = valor.trim().replaceAll("\\D", "");
		if (newCpf.length() == 11) {
			return true;
		}
		return false;
	}
	
	public static boolean isCNPJ(String valor) {
		if (valor == null) return false;
		String newCnpj = valor.trim().replaceAll("\\D", "");
		if (newCnpj.length() == 14) {
			return true;
		}
		return false;
	}
	
	private static boolean caracteresInvalidos(String cpfCnpj) {
		boolean temLetras = false;
		for (int i = 0; i < cpfCnpj.length(); i++) {
				char ch = cpfCnpj.charAt(i);
				if (Character.isLetter(ch)) {
					temLetras = true;
				}
			}

		return temLetras;	
	}
	
	public static ErroValidacaoCPFCNPJ validarCPF(String cpf) {
	    if (cpf == null || cpf.trim().equals("")) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
	    }

	    String cpfNumeros = cpf.replaceAll("\\D", "");

	    if (cpfNumeros.length() != 11) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_TAMANHO_INVALIDO;
	    }
	    if (caracteresInvalidos(cpf)) { 
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_CARACTERES_INVALIDOS;
	    }
	    if (!ValidadorCPFCNPJ.isDigitoVerificadorValidoCPF(cpfNumeros)) { 
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
	    } 
	    
	    return null; 
	}
	public static ErroValidacaoCPFCNPJ validarCNPJ(String cnpj) {
	    if (cnpj == null || cnpj.trim().equals("")) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
	    }

	    String cnpjNumeros = cnpj.replaceAll("\\D", "");

	    if (cnpjNumeros.length() != 14) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_TAMANHO_INVALIDO;
	    }
	    if (caracteresInvalidos(cnpj)) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_CARACTERES_INVALIDOS;
	    }
	    if (!ValidadorCPFCNPJ.isDigitoVerificadorValidoCNPJ(cnpjNumeros)) { 
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
	    }

	    return null; 
	}
	
	private static boolean isDigitoVerificadorValidoCPF(String cpf) {
		String numeros = cpf.replaceAll("\\D", "");
		int soma = 0;
	    for (int i = 0, peso = 10; i < 9; i++, peso--) {
	        soma += (numeros.charAt(i) - '0') * peso;
	    }
	    int r = soma % 11;
	    int d1 = (r < 2) ? 0 : 11 - r;

	    soma = 0;
	    for (int i = 0, peso = 11; i < 9; i++, peso--) {
	        soma += (numeros.charAt(i) - '0') * peso;
	    }
	    soma += d1 * 2;
	    r = soma % 11;
	    int d2 = (r < 2) ? 0 : 11 - r;

	    return d1 == (numeros.charAt(9) - '0') && d2 == (numeros.charAt(10) - '0');
	}

	private static boolean isDigitoVerificadorValidoCNPJ(String cnpj) {
		String numeros = cnpj.replaceAll("\\D", "");
		int[] w1 = {5,4,3,2,9,8,7,6,5,4,3,2};
	    int[] w2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

	    int soma = 0;
	    for (int i = 0; i < 12; i++) {
	        soma += (numeros.charAt(i) - '0') * w1[i];
	    }
	    int r = soma % 11;
	    int d1 = (r < 2) ? 0 : 11 - r;

	    soma = 0;
	    for (int i = 0; i < 12; i++) {
	        soma += (numeros.charAt(i) - '0') * w2[i];
	    }
	    soma += d1 * w2[12]; // peso 2
	    r = soma % 11;
	    int d2 = (r < 2) ? 0 : 11 - r;

	    return d1 == (numeros.charAt(12) - '0') && d2 == (numeros.charAt(13) - '0');
	}
}
