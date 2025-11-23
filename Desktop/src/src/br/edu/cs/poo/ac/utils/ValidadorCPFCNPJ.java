package br.edu.cs.poo.ac.utils;

public class ValidadorCPFCNPJ {		
	public static ResultadoValidacaoCPFCNPJ validarCPFCNPJ(String cpfCnpj) {
		if (isCNPJ(cpfCnpj)) {
			return new ResultadoValidacaoCPFCNPJ(false, true, validarCNPJ(cpfCnpj));
		} else if (isCPF(cpfCnpj)) {
			return new ResultadoValidacaoCPFCNPJ(true, false, validarCPF(cpfCnpj));	
		} else {
			return new ResultadoValidacaoCPFCNPJ(false, false, 
					ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ);
		}
	}
	
    public static boolean isCPF(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        return valor.matches("\\d{11}");
    }

    public static boolean isCNPJ(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        return valor.matches("\\d{14}");
    }

    public static ErroValidacaoCPFCNPJ validarCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
        }
        if (cpf.length() != 11) {
            return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_TAMANHO_INVALIDO;
        }
        if (!cpf.matches("\\d{11}")) {
            return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_CARACTERES_INVALIDOS;
        }
        if (!isDigitoVerificadorValidoCPF(cpf)) {
        	return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
        }
        return null; // CPF válido
    }

    public static ErroValidacaoCPFCNPJ validarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
        }
        if (cnpj.length() != 14) {
            return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_TAMANHO_INVALIDO;
        }
        if (!cnpj.matches("\\d{14}")) {
            return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_CARACTERES_INVALIDOS;
        }
        if (!isDigitoVerificadorValidoCNPJ(cnpj)) {
        	return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
        }
        return null; // CNPJ válido
    }
    private static boolean isDigitoVerificadorValidoCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) return false;

        // Extrai os 9 primeiros dígitos
        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        // Calcula o primeiro dígito verificador
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += numeros[i] * (10 - i);
        }
        int digito1 = (soma1 * 10) % 11;
        if (digito1 == 10) digito1 = 0;

        // Verifica o primeiro dígito
        if (digito1 != numeros[9]) return false;

        // Calcula o segundo dígito verificador
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += numeros[i] * (11 - i);
        }
        int digito2 = (soma2 * 10) % 11;
        if (digito2 == 10) digito2 = 0;

        // Verifica o segundo dígito
        return digito2 == numeros[10];
    }
    public static boolean isDigitoVerificadorValidoCNPJ(String cnpj) {
            if (cnpj == null || !cnpj.matches("\\d{14}")) return false;

            int[] pesosPrimeiro = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesosSegundo  = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int[] numeros = new int[14];
            for (int i = 0; i < 14; i++) {
                numeros[i] = Character.getNumericValue(cnpj.charAt(i));
            }

            // Primeiro dígito verificador
            int soma1 = 0;
            for (int i = 0; i < 12; i++) {
                soma1 += numeros[i] * pesosPrimeiro[i];
            }
            int digito1 = soma1 % 11;
            digito1 = (digito1 < 2) ? 0 : 11 - digito1;

            if (digito1 != numeros[12]) return false;

            // Segundo dígito verificador
            int soma2 = 0;
            for (int i = 0; i < 13; i++) {
                soma2 += numeros[i] * pesosSegundo[i];
            }
            int digito2 = soma2 % 11;
            digito2 = (digito2 < 2) ? 0 : 11 - digito2;

            return digito2 == numeros[13];
        }
}
