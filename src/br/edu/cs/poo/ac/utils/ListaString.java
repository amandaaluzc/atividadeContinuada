package br.edu.cs.poo.ac.utils;

public class ListaString {
    private ElementoListaString primeiro;
    private int tamanho;

    public ListaString() {
        this.primeiro = null;
        this.tamanho = 0;
    }
    public int tamanho() {
    	return tamanho;
    }
    
    
    public boolean isEmpty() {
        return this.tamanho == 0;
    }
    
    
   
    public void adicionar(String str) {
        ElementoListaString novo = new ElementoListaString(str, null);
        if (primeiro == null) {
            primeiro = novo;
        } else {
            ElementoListaString atual = primeiro;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novo);
        }
        tamanho++;
    }

    public String buscar(int indice) {
        if (indice < 0 || indice >= tamanho) {
            return null;
        }
        ElementoListaString atual = primeiro;
        for (int i = 0; i < indice; i++) {
            atual = atual.getProximo();
        }
        return atual.getConteudo();
    }

    public String[] listar() {
        String[] resultado = new String[tamanho];
        ElementoListaString atual = primeiro;
        int i = 0;
        while (atual != null) {
            resultado[i++] = atual.getConteudo();
            atual = atual.getProximo();
        }
        return resultado;
    }



	@Override
	public String toString() {
	    if (isEmpty()) {
	        return "";
	    }
	
	    StringBuilder builder = new StringBuilder();
	    ElementoListaString atual = primeiro;
	    while (atual != null) {
	        builder.append("- ").append(atual.getConteudo()).append("\n");
	        atual = atual.getProximo();
	    }
	    
	  
	    if (builder.length() > 0) {
	        builder.setLength(builder.length() - 1);
	    }
	
	    return builder.toString();
	}
}
