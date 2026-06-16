package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class GuiCadastroFuncionario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textNome;
	private JTextField textCEP;
	private JTextField textRua;
	private JTextField textNumero;
	private JTextField textBarrio;
	private JTextField textCidade;
	private JTextField textEstado;
	private JTextField textPais;
	private JTextField textComplemento;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GuiCadastroFuncionario() {
		setToolTipText("Funcionario");
		setLayout(null);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setBounds(10, 23, 34, 14);
		add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(54, 20, 86, 20);
		add(textNome);
		textNome.setColumns(10);
		
		JLabel lblEndereco = new JLabel("Endereco ");
		lblEndereco.setBounds(10, 119, 69, 14);
		add(lblEndereco);
		
		textCEP = new JTextField();
		textCEP.setBounds(40, 144, 86, 20);
		add(textCEP);
		textCEP.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP :");
		lblCep.setBounds(10, 147, 26, 14);
		add(lblCep);
		
		JLabel lblRua = new JLabel("Rua : ");
		lblRua.setBounds(136, 147, 34, 14);
		add(lblRua);
		
		textRua = new JTextField();
		textRua.setBounds(165, 144, 175, 20);
		add(textRua);
		textRua.setColumns(10);
		
		JLabel lblNumero = new JLabel("Numero :");
		lblNumero.setBounds(350, 147, 46, 14);
		add(lblNumero);
		
		textNumero = new JTextField();
		textNumero.setBounds(404, 144, 46, 20);
		add(textNumero);
		textNumero.setColumns(10);
		
		JLabel lblBarrio = new JLabel("Barrio :");
		lblBarrio.setBounds(10, 182, 46, 14);
		add(lblBarrio);
		
		textBarrio = new JTextField();
		textBarrio.setBounds(54, 179, 86, 20);
		add(textBarrio);
		textBarrio.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade :");
		lblCidade.setBounds(150, 182, 46, 14);
		add(lblCidade);
		
		textCidade = new JTextField();
		textCidade.setBounds(206, 179, 86, 20);
		add(textCidade);
		textCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("UF :");
		lblEstado.setBounds(302, 182, 26, 14);
		add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setBounds(326, 179, 40, 20);
		add(textEstado);
		textEstado.setColumns(10);
		
		JLabel lblPais = new JLabel("Pais :");
		lblPais.setBounds(376, 182, 36, 14);
		add(lblPais);
		
		textPais = new JTextField();
		textPais.setBounds(404, 179, 55, 20);
		add(textPais);
		textPais.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento :");
		lblComplemento.setBounds(10, 216, 79, 14);
		add(lblComplemento);
		
		textComplemento = new JTextField();
		textComplemento.setBounds(88, 213, 229, 20);
		add(textComplemento);
		textComplemento.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setBounds(150, 23, 60, 14);
		add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setBounds(209, 20, 86, 20);
		add(textTelefone);
		textTelefone.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(301, 23, 46, 14);
		add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(339, 20, 157, 20);
		add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF :");
		lblCPF.setBounds(10, 54, 34, 14);
		add(lblCPF);
		
		textField = new JTextField();
		textField.setBounds(40, 51, 100, 20);
		add(textField);
		textField.setColumns(10);

	}
}
