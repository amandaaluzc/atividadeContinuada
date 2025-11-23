package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.ordem.entidades.FechamentoOrdemServico;

//O identificador único, por objeto, de FechamentoOrdemServico 
//é o número da ordem de serviço.   
public class FechamentoOrdemServicoDAO extends DAOGenerico {
	public FechamentoOrdemServicoDAO() {
		super(FechamentoOrdemServico.class);
	}
}
