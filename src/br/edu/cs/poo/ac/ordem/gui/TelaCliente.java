package br.edu.cs.poo.ac.ordem.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.ordem.entidades.Contato;
import br.edu.cs.poo.ac.ordem.mediators.ClienteMediator;
import br.edu.cs.poo.ac.ordem.mediators.ResultadoMediator;

public class TelaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField txtCpfCnpj;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCelular;
	private JCheckBox chckbxEZap;
	private JFormattedTextField txtDataCadastro;
	
	private ClienteMediator clienteMediator;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCliente() {
		clienteMediator = ClienteMediator.getInstancia();
		
		setTitle("Cadastro de Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelAcesso = new JPanel();
		panelAcesso.setBorder(new TitledBorder(null, "Acesso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelDados = new JPanel();
		panelDados.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dados do Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panelBotoes = new JPanel();
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					incluirCliente();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(TelaCliente.this, "Ocorreu um erro inesperado ao incluir:\n" + ex.getMessage(), "Erro Crítico", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alterarCliente();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(TelaCliente.this, "Ocorreu um erro inesperado ao alterar:\n" + ex.getMessage(), "Erro Crítico", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					excluirCliente();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(TelaCliente.this, "Ocorreu um erro inesperado ao excluir:\n" + ex.getMessage(), "Erro Crítico", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buscarCliente();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(TelaCliente.this, "Ocorreu um erro inesperado ao buscar:\n" + ex.getMessage(), "Erro Crítico", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});

		GroupLayout gl_panelBotoes = new GroupLayout(panelBotoes);
		gl_panelBotoes.setHorizontalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnIncluir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAlterar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExcluir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar)
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addComponent(btnLimpar)
					.addContainerGap())
		);
		gl_panelBotoes.setVerticalGroup(
			gl_panelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBotoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBotoes.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnBuscar)
						.addComponent(btnLimpar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBotoes.setLayout(gl_panelBotoes);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelDados, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
						.addComponent(panelAcesso, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
						.addComponent(panelBotoes, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelAcesso, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelDados, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBotoes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JPanel panelContato = new JPanel();
		panelContato.setBorder(new TitledBorder(null, "Contato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblDataCadastro = new JLabel("Data do Cadastro:");
		lblDataCadastro.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		try {
			txtDataCadastro = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		GroupLayout gl_panelDados = new GroupLayout(panelDados);
		gl_panelDados.setHorizontalGroup(
			gl_panelDados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDados.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDados.createParallelGroup(Alignment.LEADING)
						.addComponent(panelContato, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
						.addGroup(gl_panelDados.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
						.addGroup(gl_panelDados.createSequentialGroup()
							.addComponent(lblDataCadastro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDataCadastro, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelDados.setVerticalGroup(
			gl_panelDados.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDados.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDados.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panelContato, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelDados.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataCadastro)
						.addComponent(txtDataCadastro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		
		JLabel lblEmail = new JLabel("E-mail:");
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular:");
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		
		chckbxEZap = new JCheckBox("É WhatsApp");
		GroupLayout gl_panelContato = new GroupLayout(panelContato);
		gl_panelContato.setHorizontalGroup(
			gl_panelContato.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContato.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContato.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCelular)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContato.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContato.createSequentialGroup()
							.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(chckbxEZap))
						.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelContato.setVerticalGroup(
			gl_panelContato.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContato.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContato.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(18)
					.addGroup(gl_panelContato.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCelular)
						.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxEZap))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		panelContato.setLayout(gl_panelContato);
		panelDados.setLayout(gl_panelDados);
		
		JLabel lblCpfCnpj = new JLabel("CPF ou CNPJ:");
		lblCpfCnpj.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtCpfCnpj = new JFormattedTextField();
		txtCpfCnpj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				formatarCpfCnpj();
			}
		});
		
		GroupLayout gl_panelAcesso = new GroupLayout(panelAcesso);
		gl_panelAcesso.setHorizontalGroup(
			gl_panelAcesso.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAcesso.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCpfCnpj)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtCpfCnpj, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(218, Short.MAX_VALUE))
		);
		gl_panelAcesso.setVerticalGroup(
			gl_panelAcesso.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAcesso.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAcesso.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpfCnpj)
						.addComponent(txtCpfCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelAcesso.setLayout(gl_panelAcesso);
		contentPane.setLayout(gl_contentPane);
	}
	
	private String apenasNumeros(String texto) {
		if (texto == null) {
			return "";
		}
		return texto.replaceAll("[^0-9]", "");
	}
	
	private void formatarCpfCnpj() {
		String texto = apenasNumeros(txtCpfCnpj.getText());
		
		try {
			MaskFormatter formatter = null;
			if (texto.length() == 11) {
				formatter = new MaskFormatter("###.###.###-##");
			} else if (texto.length() == 14) {
				formatter = new MaskFormatter("##.###.###/####-##");
			} else {
				txtCpfCnpj.setText(texto);
				return;
			}
			
			if (txtCpfCnpj.getFormatter() != null) {
				txtCpfCnpj.setValue(null);
			}
			
			formatter.setValueContainsLiteralCharacters(false);
			txtCpfCnpj.setText(formatter.valueToString(texto));
			
		} catch (ParseException ex) {
			txtCpfCnpj.setText(texto);
		}
	}
	
	private void limparCampos() {
		txtCpfCnpj.setText("");
		txtNome.setText("");
		txtEmail.setText("");
		txtCelular.setText("");
		chckbxEZap.setSelected(false);
		txtDataCadastro.setText("");
		txtCpfCnpj.requestFocus();
	}
	
	private Cliente montarCliente() {
		String cpfCnpj = apenasNumeros(txtCpfCnpj.getText());
		String nome = txtNome.getText();
		String email = txtEmail.getText();
		String celular = txtCelular.getText();
		boolean ehZap = chckbxEZap.isSelected();
		
		Contato contato = new Contato(email, celular, ehZap);
		
		LocalDate dataCadastro = null;
        String textoData = txtDataCadastro.getText();
        String textoDataSemMascara = textoData.replace("/", "").trim();

		if (!textoDataSemMascara.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataCadastro = LocalDate.parse(textoData, formatter);
            } catch (DateTimeParseException e) {
                // Deixa a data como nula, a validação do Mediator vai pegar o erro.
            }
        }
		
		return new Cliente(cpfCnpj, nome, contato, dataCadastro);
	}
	
	private void preencherCampos(Cliente cliente) {
		txtCpfCnpj.setText(cliente.getCpfCnpj());
		formatarCpfCnpj();
		txtNome.setText(cliente.getNome());
		
		if (cliente.getContato() != null) {
			txtEmail.setText(cliente.getContato().getEmail());
			txtCelular.setText(cliente.getContato().getCelular());
			chckbxEZap.setSelected(cliente.getContato().isEhZap());
		}
		
		if (cliente.getDataCadastro() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			txtDataCadastro.setText(cliente.getDataCadastro().format(formatter));
		}
	}
	
	private void incluirCliente() {
		Cliente cliente = montarCliente();
		ResultadoMediator resultado = clienteMediator.incluir(cliente);
		
		if (resultado.isOperacaoRealizada()) {
			JOptionPane.showMessageDialog(this, "Cliente incluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			limparCampos();
		} else {
            if (resultado.getMensagensErro() != null && !resultado.getMensagensErro().isEmpty()) {
			    JOptionPane.showMessageDialog(this, resultado.getMensagensErro().toString(), "Erros de Validação", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "A operação falhou sem retornar uma mensagem de erro específica.", "Erro Desconhecido", JOptionPane.WARNING_MESSAGE);
            }
		}
	}
	
	private void alterarCliente() {
		Cliente cliente = montarCliente();
		ResultadoMediator resultado = clienteMediator.alterar(cliente);
		
		if (resultado.isOperacaoRealizada()) {
			JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
            if (resultado.getMensagensErro() != null && !resultado.getMensagensErro().isEmpty()) {
			    JOptionPane.showMessageDialog(this, resultado.getMensagensErro().toString(), "Erros", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "A operação falhou sem retornar uma mensagem de erro específica.", "Erro Desconhecido", JOptionPane.WARNING_MESSAGE);
            }
		}
	}
	
	private void excluirCliente() {
		String cpfCnpj = apenasNumeros(txtCpfCnpj.getText());
		int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este cliente?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
		
		if (resposta == JOptionPane.YES_OPTION) {
			ResultadoMediator resultado = clienteMediator.excluir(cpfCnpj);
			if (resultado.isOperacaoRealizada()) {
				JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				limparCampos();
			} else {
                if (resultado.getMensagensErro() != null && !resultado.getMensagensErro().isEmpty()) {
				    JOptionPane.showMessageDialog(this, resultado.getMensagensErro().toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "A exclusão falhou sem retornar uma mensagem de erro específica.", "Erro Desconhecido", JOptionPane.WARNING_MESSAGE);
                }
			}
		}
	}
	
	private void buscarCliente() {
		String cpfCnpj = apenasNumeros(txtCpfCnpj.getText());
		Cliente cliente = clienteMediator.buscar(cpfCnpj);
		
		if (cliente != null) {
			preencherCampos(cliente);
		} else {
			JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}