package br.edu.cs.poo.ac.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static boolean estaVazia(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean tamanhoExcedido(String str, int tamanho) {
		if (tamanho > 0) {
			return (str == null || str.trim().length() > tamanho);
		}
		else {
			return false;
		}
	}
	
	public static boolean emailValido(String email) {
		
		if (email == null || email.trim().equals("")) {
			return false;
		}
		
		else {
			String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}	
	}
	
	public static boolean telefoneValido(String tel) {
	    if (tel == null || tel.trim().equals("")) {
	        return false;
	    }

	    else {
	        String digitos = tel.replaceAll("[^0-9]","");

	        if (digitos.length() == 11 || digitos.length() == 10 ) {
	            String regex = "^\\(\\d{2}\\)\\d{8,9}$";

	            Pattern pattern = Pattern.compile(regex);
	            Matcher matcher = pattern.matcher(tel);

	            return matcher.matches();
	        }
	        else {
	            return false;
	        }

	    }
	}
	
	public static boolean tamanhoMenor(String str, int tamanho) {
		if (tamanho > 0) {
			return (str == null || str.trim().length() < tamanho);
		}
		else {
			return false;
		}
	}
}