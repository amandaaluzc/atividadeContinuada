package br.edu.cs.poo.ac.ordem.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import javax.swing.text.NumberFormatter;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.edu.cs.poo.ac.ordem.entidades.Desktop;
import br.edu.cs.poo.ac.ordem.entidades.Equipamento;
import br.edu.cs.poo.ac.ordem.entidades.Notebook;
import br.edu.cs.poo.ac.ordem.mediators.EquipamentoMediator;
import br.edu.cs.poo.ac.ordem.mediators.ResultadoMediator;

public class TelaEquipamento extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtSerial;
    private JTextArea txtDescricao;
    private JFormattedTextField txtValorEstimado;
    private JComboBox<String> comboBoxTipo;
    private JRadioButton rdbtnNovoNao;
    private JRadioButton rdbtnNovoSim;
    private JRadioButton rdbtnSensiveisNao;
    private JRadioButton rdbtnSensiveisSim;
    private JRadioButton rdbtnServidorNao;
    private JRadioButton rdbtnServidorSim;
    private final ButtonGroup groupEhNovo = new ButtonGroup();
    private final ButtonGroup groupSensiveis = new ButtonGroup();
    private final ButtonGroup groupServidor = new ButtonGroup();
    private JPanel panelCamposDinamicos;
    private EquipamentoMediator equipamentoMediator;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaEquipamento frame = new TelaEquipamento();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaEquipamento() {
        // Inicializa o mediator (Singleton)
        equipamentoMediator = EquipamentoMediator.getInstancia();
        
        setTitle("Cadastro de Equipamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- PAINEL DE ACESSO ---
        JPanel panelAcesso = new JPanel();
        panelAcesso.setBorder(new TitledBorder(null, "Acesso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelAcesso.setBounds(10, 11, 444, 100);
        contentPane.add(panelAcesso);
        panelAcesso.setLayout(null);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(10, 25, 46, 14);
        panelAcesso.add(lblTipo);

        comboBoxTipo = new JComboBox<>();
        comboBoxTipo.setModel(new DefaultComboBoxModel<>(new String[] { "Notebook", "Desktop" })); // [cite: 596]
        comboBoxTipo.setBounds(70, 21, 150, 22);
        panelAcesso.add(comboBoxTipo);

        JLabel lblSerial = new JLabel("Serial:");
        lblSerial.setBounds(10, 55, 46, 14);
        panelAcesso.add(lblSerial);

        txtSerial = new JTextField(); // 
        txtSerial.setBounds(70, 52, 364, 20);
        panelAcesso.add(txtSerial);
        
        // --- PAINEL DE DADOS ---
        JPanel panelDados = new JPanel();
        panelDados.setBorder(new TitledBorder(null, "Dados do Equipamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDados.setBounds(10, 122, 444, 230);
        contentPane.add(panelDados);
        panelDados.setLayout(null);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(10, 24, 70, 14);
        panelDados.add(lblDescricao);

        txtDescricao = new JTextArea(); // 
        txtDescricao.setBounds(90, 24, 344, 60);
        panelDados.add(txtDescricao);

        JLabel lblEhNovo = new JLabel("É novo:");
        lblEhNovo.setBounds(10, 98, 70, 14);
        panelDados.add(lblEhNovo);

        rdbtnNovoNao = new JRadioButton("NÃO"); // 
        groupEhNovo.add(rdbtnNovoNao);
        rdbtnNovoNao.setSelected(true); // 
        rdbtnNovoNao.setBounds(90, 94, 60, 23);
        panelDados.add(rdbtnNovoNao);

        rdbtnNovoSim = new JRadioButton("SIM"); // 
        groupEhNovo.add(rdbtnNovoSim);
        rdbtnNovoSim.setBounds(150, 94, 60, 23);
        panelDados.add(rdbtnNovoSim);

        JLabel lblValorEstimado = new JLabel("Valor Estimado:");
        lblValorEstimado.setBounds(10, 128, 90, 14);
        panelDados.add(lblValorEstimado);
        
        // Máscara para valor decimal com até 10 casas inteiras e 2 decimais 
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(decimalFormat);
        formatter.setValueClass(Double.class);
        formatter.setAllowsInvalid(false);
        txtValorEstimado = new JFormattedTextField(formatter);
        txtValorEstimado.setBounds(110, 125, 120, 20);
        panelDados.add(txtValorEstimado);

        // --- PAINEL COM CAMPOS DINÂMICOS (NOTEBOOK/DESKTOP) ---
        panelCamposDinamicos = new JPanel();
        panelCamposDinamicos.setBounds(10, 153, 424, 66);
        panelDados.add(panelCamposDinamicos);
        panelCamposDinamicos.setLayout(new CardLayout(0, 0));

        // PAINEL NOTEBOOK [cite: 605]
        JPanel panelNotebook = new JPanel();
        panelCamposDinamicos.add(panelNotebook, "Notebook");
        panelNotebook.setLayout(null);
        
        JLabel lblDadosSensiveis = new JLabel("Carrega dados sensíveis:"); // [cite: 608]
        lblDadosSensiveis.setBounds(10, 11, 150, 14);
        panelNotebook.add(lblDadosSensiveis);
        
        rdbtnSensiveisNao = new JRadioButton("NÃO"); // [cite: 608]
        groupSensiveis.add(rdbtnSensiveisNao);
        rdbtnSensiveisNao.setSelected(true); // [cite: 610]
        rdbtnSensiveisNao.setBounds(170, 7, 60, 23);
        panelNotebook.add(rdbtnSensiveisNao);
        
        rdbtnSensiveisSim = new JRadioButton("SIM"); // [cite: 608]
        groupSensiveis.add(rdbtnSensiveisSim);
        rdbtnSensiveisSim.setBounds(230, 7, 60, 23);
        panelNotebook.add(rdbtnSensiveisSim);

        // PAINEL DESKTOP 
        JPanel panelDesktop = new JPanel();
        panelCamposDinamicos.add(panelDesktop, "Desktop");
        panelDesktop.setLayout(null);
        
        JLabel lblEhServidor = new JLabel("É Servidor:"); // 
        lblEhServidor.setBounds(10, 11, 150, 14);
        panelDesktop.add(lblEhServidor);
        
        rdbtnServidorNao = new JRadioButton("NÃO"); // 
        groupServidor.add(rdbtnServidorNao);
        rdbtnServidorNao.setSelected(true); // 
        rdbtnServidorNao.setBounds(170, 7, 60, 23);
        panelDesktop.add(rdbtnServidorNao);
        
        rdbtnServidorSim = new JRadioButton("SIM"); // 
        groupServidor.add(rdbtnServidorSim);
        rdbtnServidorSim.setBounds(230, 7, 60, 23);
        panelDesktop.add(rdbtnServidorSim);

        comboBoxTipo.addActionListener(e -> {
            String itemSelecionado = (String) comboBoxTipo.getSelectedItem();
            CardLayout cl = (CardLayout) (panelCamposDinamicos.getLayout());
            cl.show(panelCamposDinamicos, itemSelecionado);
        });

        // Inicia com Notebook selecionado e seu painel visível [cite: 597]
        comboBoxTipo.setSelectedIndex(0);
        
        JPanel panelBotoes = new JPanel();
        panelBotoes.setBounds(10, 363, 444, 37);
        contentPane.add(panelBotoes);
        panelBotoes.setLayout(null);
        
        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.addActionListener(e -> incluirEquipamento());
        btnIncluir.setBounds(10, 5, 80, 23);
        panelBotoes.add(btnIncluir);
        
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(e -> alterarEquipamento());
        btnAlterar.setBounds(95, 5, 80, 23);
        panelBotoes.add(btnAlterar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> excluirEquipamento());
        btnExcluir.setBounds(180, 5, 80, 23);
        panelBotoes.add(btnExcluir);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarEquipamento());
        btnBuscar.setBounds(265, 5, 80, 23);
        panelBotoes.add(btnBuscar);
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        btnLimpar.setBounds(350, 5, 80, 23);
        panelBotoes.add(btnLimpar);
    }
    
    // --- MÉTODOS DE LÓGICA DA TELA ---
    
    private void limparCampos() {
        comboBoxTipo.setSelectedIndex(0);
        txtSerial.setText("");
        txtDescricao.setText("");
        rdbtnNovoNao.setSelected(true);
        txtValorEstimado.setValue(null);
        rdbtnSensiveisNao.setSelected(true);
        rdbtnServidorNao.setSelected(true);
        txtSerial.requestFocus();
    }
    
    private Equipamento montarEquipamento() {
        String tipo = (String) comboBoxTipo.getSelectedItem();
        String serial = txtSerial.getText();
        String descricao = txtDescricao.getText();
        boolean ehNovo = rdbtnNovoSim.isSelected();
        double valorEstimado = 0.0;
        
        if (txtValorEstimado.getValue() instanceof Number) {
            valorEstimado = ((Number) txtValorEstimado.getValue()).doubleValue();
        }

        if ("Notebook".equals(tipo)) {
            boolean carregaDadosSensiveis = rdbtnSensiveisSim.isSelected();
            return new Notebook(serial, descricao, ehNovo, valorEstimado, carregaDadosSensiveis);
        } else {
            boolean ehServidor = rdbtnServidorSim.isSelected();
            return new Desktop(serial, descricao, ehNovo, valorEstimado, ehServidor);
        }
    }
    
    private void preencherCampos(Equipamento equipamento) {
        limparCampos();
        txtSerial.setText(equipamento.getSerial());
        txtDescricao.setText(equipamento.getDescricao());
        rdbtnNovoSim.setSelected(equipamento.isEhNovo());
        rdbtnNovoNao.setSelected(!equipamento.isEhNovo());
        txtValorEstimado.setValue(equipamento.getValorEstimado());

        if (equipamento instanceof Notebook) {
            comboBoxTipo.setSelectedItem("Notebook");
            Notebook notebook = (Notebook) equipamento;
            rdbtnSensiveisSim.setSelected(notebook.isCarregaDadosSensiveis());
            rdbtnSensiveisNao.setSelected(!notebook.isCarregaDadosSensiveis());
        } else if (equipamento instanceof Desktop) {
            comboBoxTipo.setSelectedItem("Desktop");
            Desktop desktop = (Desktop) equipamento;
            rdbtnServidorSim.setSelected(desktop.isEhServidor());
            rdbtnServidorNao.setSelected(!desktop.isEhServidor());
        }
    }


	private void incluirEquipamento() {
	    Equipamento equipamento = montarEquipamento();
	    ResultadoMediator resultado = null;
	
	    // --- MUDANÇA AQUI ---
	    // Verificamos o tipo de objeto e chamamos o método correto do mediator.
	    if (equipamento instanceof Notebook) {
	        resultado = equipamentoMediator.incluirNotebook((Notebook) equipamento);
	    } else if (equipamento instanceof Desktop) {
	        resultado = equipamentoMediator.incluirDesktop((Desktop) equipamento);
	    }
	
	    if (resultado != null && resultado.isOperacaoRealizada()) {
	        JOptionPane.showMessageDialog(this, "Inclusão realizada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	        limparCampos();
	    } else {
	        String mensagemErro = (resultado != null) ? resultado.getMensagensErro().toString() : "Ocorreu um erro desconhecido.";
	        JOptionPane.showMessageDialog(this, mensagemErro, "Erros de Validação", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void alterarEquipamento() {
	    Equipamento equipamento = montarEquipamento();
	    ResultadoMediator resultado = null;
	
	    // --- MUDANÇA AQUI ---
	    // A mesma lógica de verificação é aplicada para a alteração.
	    if (equipamento instanceof Notebook) {
	        resultado = equipamentoMediator.alterarNotebook((Notebook) equipamento);
	    } else if (equipamento instanceof Desktop) {
	        resultado = equipamentoMediator.alterarDesktop((Desktop) equipamento);
	    }
	
	    if (resultado != null && resultado.isOperacaoRealizada()) {
	        JOptionPane.showMessageDialog(this, "Alteração realizada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        String mensagemErro = (resultado != null) ? resultado.getMensagensErro().toString() : "Ocorreu um erro desconhecido.";
	        JOptionPane.showMessageDialog(this, mensagemErro, "Erros de Validação", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void excluirEquipamento() {
	    String tipo = (String) comboBoxTipo.getSelectedItem();
	    String serial = txtSerial.getText();
	    ResultadoMediator resultado = null;
	    
	    int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este equipamento?", "Confirmação", JOptionPane.YES_NO_OPTION);
	    if (resposta == JOptionPane.YES_OPTION) {
	        // --- MUDANÇA AQUI ---
	        // Verificamos o tipo selecionado para chamar o método de exclusão correto.
	        if ("Notebook".equals(tipo)) {
	            // O seu mediator espera o ID completo (prefixo + serial)
	            resultado = equipamentoMediator.excluirNotebook("NO" + serial);
	        } else {
	            resultado = equipamentoMediator.excluirDesktop("DE" + serial);
	        }
	
	        if (resultado != null && resultado.isOperacaoRealizada()) {
	            JOptionPane.showMessageDialog(this, "Exclusão realizada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	            limparCampos();
	        } else {
	            String mensagemErro = (resultado != null) ? resultado.getMensagensErro().toString() : "Exclusão não pôde ser realizada.";
	            JOptionPane.showMessageDialog(this, mensagemErro, "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void buscarEquipamento() {
	    String tipo = (String) comboBoxTipo.getSelectedItem();
	    String serial = txtSerial.getText();
	    Equipamento equipamentoEncontrado = null;
	
	    // --- MUDANÇA AQUI ---
	    // Verificamos o tipo para chamar o método de busca correto.
	    if ("Notebook".equals(tipo)) {
	        equipamentoEncontrado = equipamentoMediator.buscarNotebook("NO" + serial);
	    } else {
	        equipamentoEncontrado = equipamentoMediator.buscarDesktop("DE" + serial);
	    }
	
	    if (equipamentoEncontrado != null) {
	        preencherCampos(equipamentoEncontrado);
	    } else {
	        JOptionPane.showMessageDialog(this, "Equipamento não encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
}