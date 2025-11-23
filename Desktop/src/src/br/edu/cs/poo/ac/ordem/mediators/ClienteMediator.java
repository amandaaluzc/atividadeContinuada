package br.edu.cs.poo.ac.ordem.mediators;

import br.edu.cs.poo.ac.ordem.daos.ClienteDAO;
import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.ordem.entidades.Contato;
import br.edu.cs.poo.ac.utils.ErroValidacaoCPFCNPJ;
import br.edu.cs.poo.ac.utils.ListaString;
import br.edu.cs.poo.ac.utils.ResultadoValidacaoCPFCNPJ;
import br.edu.cs.poo.ac.utils.ValidadorCPFCNPJ;
import static br.edu.cs.poo.ac.utils.StringUtils.*;

import java.time.LocalDate;

// Deve ser um Singleton
public class ClienteMediator {
	private static final String CPF_CNPJ_INEXISTENTE = "CPF/CNPJ inexistente";
	private static final String CPF_CNPJ_NAO_INFORMADO = "CPF/CNPJ não informado";
	private static ClienteMediator instancia;
	private ClienteDAO dao = new ClienteDAO();
	public static ClienteMediator getInstancia() {
		if (instancia == null) {
			instancia = new ClienteMediator();
		}
		return instancia;
	}
	private ClienteMediator() {}
	
	public ResultadoMediator incluir(Cliente cliente) {
		ResultadoMediator res = validar(cliente);
		if (res.isValidado()) {
			ListaString mensagens = new ListaString();
			boolean incluido = dao.incluir(cliente); 
			if (!incluido) {
				mensagens.adicionar("CPF/CNPJ já existente");
			}
			return new ResultadoMediator(true, incluido, mensagens);
		} else {
			return res;
		}
	}
	public ResultadoMediator alterar(Cliente cliente) {
		ResultadoMediator res = validar(cliente);
		if (res.isValidado()) {
			ListaString mensagens = new ListaString();
			boolean alterado = dao.alterar(cliente); 
			if (!alterado) {
				mensagens.adicionar(CPF_CNPJ_INEXISTENTE);
			}
			return new ResultadoMediator(true, alterado, mensagens);
		} else {
			return res;
		}
	}	
	public ResultadoMediator excluir(String cpfCnpj) {
		ListaString mensagens = new ListaString();
		boolean validado = false;
		boolean excluido = false; 
		if (estaVazia(cpfCnpj)) {
			mensagens.adicionar(CPF_CNPJ_NAO_INFORMADO);
		} else {
			validado = true;
			excluido = dao.excluir(cpfCnpj); 
			if (!excluido) {
				mensagens.adicionar(CPF_CNPJ_INEXISTENTE);
			}
		} 
		return new ResultadoMediator(validado, excluido, mensagens);
	}	
	public Cliente buscar(String cpfCnpj) {
		if (estaVazia(cpfCnpj)) {
			return null;
		} else {
			return dao.buscar(cpfCnpj);
		}
	}	
	public ResultadoMediator validar(Cliente cliente) {
		ListaString mensagens = new ListaString();
		if (cliente == null) {
			mensagens.adicionar("Cliente não informado");
		} else {
			String cpfCnpj = cliente.getCpfCnpj();
			if (estaVazia(cpfCnpj)) {
				mensagens.adicionar(CPF_CNPJ_NAO_INFORMADO);
			} else {			
				ResultadoValidacaoCPFCNPJ res = ValidadorCPFCNPJ.validarCPFCNPJ(cpfCnpj);
				ErroValidacaoCPFCNPJ erro = res.getErroValidacao();
				if (erro != null) {
					mensagens.adicionar(erro.getMensagem());
				}							
			} 
			if (estaVazia(cliente.getNome())) {
				mensagens.adicionar("Nome não informado");
			} else if (tamanhoExcedido(cliente.getNome().trim(), 50)) {
				mensagens.adicionar("Nome tem mais de 50 caracteres");
			}
			Contato contato = cliente.getContato();
			if (contato == null) {
				mensagens.adicionar("Contato não informado");
			}
			LocalDate dataCadastro = cliente.getDataCadastro(); 
			if (dataCadastro == null) {
				mensagens.adicionar("Data do cadastro não informada");
			}
			if (dataCadastro != null && dataCadastro.isAfter(LocalDate.now())) {
				mensagens.adicionar("Data do cadastro não pode ser posterior à data atual");
			}
			if (contato != null) {
				String celular = contato.getCelular();
				String email = contato.getEmail();				
				if (estaVazia(celular) && estaVazia(email)) {
					mensagens.adicionar("Celular e e-mail não foram informados");	
				}
				if (!estaVazia(celular) && !telefoneValido(celular)) {
					mensagens.adicionar("Celular está em um formato inválido");
				}
				if (!estaVazia(email) && !emailValido(email)) {
					mensagens.adicionar("E-mail está em um formato inválido");
				}
				if (estaVazia(celular) && contato.isEhZap()) {
					mensagens.adicionar("Celular não informado e indicador de zap ativo");
				}
			}
		}		
		return new ResultadoMediator(mensagens.tamanho() == 0, false, mensagens); 
	}
}
