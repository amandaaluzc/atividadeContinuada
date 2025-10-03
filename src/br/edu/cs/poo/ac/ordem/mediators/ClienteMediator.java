package br.edu.cs.poo.ac.ordem.mediators;

import java.time.LocalDate;

import br.edu.cs.poo.ac.ordem.daos.ClienteDAO;
import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.ordem.entidades.Contato;
import br.edu.cs.poo.ac.utils.ListaString;
import br.edu.cs.poo.ac.utils.ResultadoValidacaoCPFCNPJ;
import br.edu.cs.poo.ac.utils.StringUtils;
import br.edu.cs.poo.ac.utils.ValidadorCPFCNPJ;

public class ClienteMediator {
	private static final ClienteMediator instancia = new ClienteMediator();
	private final ClienteDAO clienteDAO = new ClienteDAO();
	
	public static ClienteMediator getInstancia() {
		return instancia;
	}
	
	public ResultadoMediator incluir(Cliente cliente) {
		ResultadoMediator resultado = validar(cliente);
		
		if (resultado.isValidado() == false) {
			return new ResultadoMediator(false, false, resultado.getMensagensErro());
		}
		
		if (buscar(cliente.getCpfCnpj()) == null) {
			clienteDAO.incluir(cliente);
			return new ResultadoMediator(true, true, resultado.getMensagensErro());
		} else {
			ListaString erro = new ListaString();
			erro.adicionar("CPF/CNPJ já existente");
			return new ResultadoMediator(false, false, erro);
		}
	}
	
		
	public ResultadoMediator alterar(Cliente cliente) {
		ResultadoMediator resultado = validar(cliente);
		
		if (resultado.isValidado() == false) {
			return new ResultadoMediator(false, false, resultado.getMensagensErro());
		}
		
		if (buscar(cliente.getCpfCnpj()) != null) {
			clienteDAO.alterar(cliente);
			return new ResultadoMediator(true, true, resultado.getMensagensErro());
		} else {
			ListaString erro = new ListaString();
			erro.adicionar("CPF/CNPJ inexistente");
			return new ResultadoMediator(false, false, erro);
		}
	}
	
	public ResultadoMediator excluir(String cpfCnpj) {
		ListaString erro = new ListaString();
		if(StringUtils.estaVazia(cpfCnpj)) { 
			erro.adicionar("CPF/CNPJ não informado");
			return new ResultadoMediator(false,false,erro);
		}
		
		if(buscar(cpfCnpj) != null) {
			clienteDAO.excluir(cpfCnpj);
			return new ResultadoMediator(true,true,erro);
		}
		else {
			erro.adicionar("CPF/CNPJ inexistente");
			return new ResultadoMediator(true,false,erro);
		}
	}
	

	public Cliente buscar(String cpfCnpj) {
	    if (cpfCnpj == null || cpfCnpj.trim().equals("")) {
	        return null;
	    }
	    return clienteDAO.buscar(cpfCnpj);
	}
	
	
	public ResultadoMediator validar(Cliente cliente) {
		
		ListaString erros = new ListaString();

		if (cliente == null) {
	        erros.adicionar("Cliente não informado");
	        return new ResultadoMediator(false, false, erros);
	    }
		
		boolean validado = true;
		boolean operacaoRealizada = false;

		if(cliente.getCpfCnpj() == null || StringUtils.estaVazia(cliente.getCpfCnpj())) {
			erros.adicionar("CPF/CNPJ não informado");
			validado = false;
		}
		else {
			ResultadoValidacaoCPFCNPJ validacaoCPFCNPJ = ValidadorCPFCNPJ.validarCPFCNPJ(cliente.getCpfCnpj());
		
			if(validacaoCPFCNPJ.isCPF() == false && validacaoCPFCNPJ.isCNPJ() == false) {
				erros.adicionar("Não é CPF nem CNJP");
				validado = false;
			}
			else if(validacaoCPFCNPJ.getErroValidacao() != null){
				erros.adicionar(validacaoCPFCNPJ.getErroValidacao().getMensagem());
				validado = false;
			}		
		}
		
		String nomeCliente = cliente.getNome();
		if (nomeCliente == null || StringUtils.estaVazia(nomeCliente)) {
			erros.adicionar("Nome não informado");
			validado = false;
		}
		else {
	        if (nomeCliente.length() > 50) {
	            erros.adicionar("Nome tem mais de 50 caracteres");
	            validado = false;
	        }
	    }

		Contato contatoCliente = cliente.getContato();
		if (contatoCliente == null) {
			erros.adicionar("Contato não informado");
			validado = false;
		}

		LocalDate dataCadastroCliente = cliente.getDataCadastro();
		if (dataCadastroCliente == null) {
			erros.adicionar("Data do cadastro não informada");
			validado = false;
		}
		else if (dataCadastroCliente.isAfter(LocalDate.now())) {
	        erros.adicionar("Data do cadastro não pode ser posterior à data atual");
	        validado = false;
	    }

		if (contatoCliente != null) {
				String email = contatoCliente.getEmail();
				String celular = contatoCliente.getCelular();
				boolean ehZap = contatoCliente.isEhZap();
				if (StringUtils.estaVazia(email) == true && StringUtils.estaVazia(celular) == true) {
					erros.adicionar("Celular e e-mail não foram informados");
					validado = false;
				}	
				else if (ehZap == true && StringUtils.estaVazia(celular) == true) {
				erros.adicionar("Celular não informado e indicador de zap ativo");
				validado = false;
				}
				else{
					if(StringUtils.estaVazia(celular) == false) {
						if(StringUtils.telefoneValido(celular) == false) {
						erros.adicionar("Celular está em um formato inválido");
						validado = false;
						}
					}
					if(StringUtils.estaVazia(email) == false) {
						if(StringUtils.emailValido(email) == false) {
						erros.adicionar("E-mail está em um formato inválido");
						validado = false;
						}
					}
				}
		}
		ResultadoMediator resultadoMediator = new ResultadoMediator(validado, operacaoRealizada, erros);
		return resultadoMediator;
	}
}