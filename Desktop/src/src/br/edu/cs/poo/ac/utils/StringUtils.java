package br.edu.cs.poo.ac.utils;

import java.util.regex.Pattern;

public class StringUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE);
    // Regex: (NN)NNNNNNNN ou (NN)NNNNNNNNN
    private static final Pattern PADRAO_TELEFONE = Pattern.compile("^\\(\\d{2}\\)\\d{8,9}$");
	
	public static boolean estaVazia(String str) {
		return str == null || str.trim().isEmpty();
	}
	public static boolean tamanhoExcedido(String str, int tamanho) {
		if (tamanho < 0) {
			return false;
		} else {
			if (str == null) {
				return tamanho > 0;
			} else {
				return str.length() > tamanho;
			}
		}
	}
	public static boolean tamanhoMenor(String str, int tamanho) {
		if (tamanho < 0) {
			return false;
		} else {
			if (str == null) {
				return tamanho > 0;
			} else {
				return str.length() < tamanho;
			}
		}
	}
    public static boolean emailValido(String email) { 
    	if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static boolean telefoneValido(String tel) {
        if (tel == null || tel.trim().isEmpty()) return false;
        return PADRAO_TELEFONE.matcher(tel).matches();
    }
}
