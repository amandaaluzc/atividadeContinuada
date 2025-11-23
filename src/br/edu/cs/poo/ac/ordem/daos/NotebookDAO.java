package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.Notebook;
import br.edu.cs.poo.ac.utils.Registro;

public class NotebookDAO extends DAOGenerico{
    @Override
    public Class<?> getClasseEntidade() {
        return Notebook.class;
    }

    public Notebook buscar(String id) {
        return (Notebook) super.buscar(id);
    }

    public boolean incluir(Notebook note) {
        return super.incluir(note);
    }

    public boolean alterar(Notebook note) {
        return super.alterar(note);
    }

    public boolean excluir(String id) {
        return super.excluir(id);
    }

    public Notebook[] buscarTodos() {
        Registro[] rs = super.buscarTodos();
        Notebook[] out = new Notebook[rs.length];
        for (int i = 0; i < rs.length; i++) out[i] = (Notebook) rs[i];
        return out;
    }
}