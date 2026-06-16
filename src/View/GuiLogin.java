package View;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTextField;


import ConexaoBD_DAO.*;



public class GuiLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField tfLogin;
    private JLabel lbSenha;
    private JLabel lbLogin;
    private JButton btLogar;
    private JButton btCancelar;
    private JPasswordField pfSenha;

    public GuiLogin() {
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setTitle("Login no Sistema");
        setBounds(0, 0, 250, 200);
        setLayout(null);

        tfLogin = new JTextField(5);
        pfSenha = new JPasswordField(5);

        lbSenha = new JLabel("Senha:");
        lbLogin = new JLabel("Login:");

        btLogar = new JButton("Logar");
        btCancelar = new JButton("Cancelar");

        tfLogin.setBounds(100, 30, 120, 25);
        lbLogin.setBounds(30, 30, 80, 25);

        lbSenha.setBounds(30, 75, 80, 25);
        pfSenha.setBounds(100, 75, 120, 25);

        btLogar.setBounds(20, 120, 100, 25);
        btCancelar.setBounds(125, 120, 100, 25);

        add(tfLogin);
        add(lbSenha);
        add(lbLogin);
        add(btLogar);
        add(btCancelar);
        add(pfSenha);
    }
    
    private void closeFrame() {
    	this.setVisible(false);
    }

    private void definirEventos() {
        btLogar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String codigo = tfLogin.getText();
            	String senha = String.valueOf( pfSenha.getPassword() );
            	
            	Login login = new Login();
            	login.setEmail(codigo);
            	login.setSenha(senha);
            	BD bd = new BD();
            	if ( bd.getConectar() ) {
            		LoginDao loginDao = new LoginDao();
            		loginDao.Consulta(login.getEmail());
            		
            		bd.fecharConexao();
            		if( loginDao.Consulta(login.getEmail()) != null ) {
            			
            			if ( login.validarLogin(codigo,loginDao.Consulta(login.getEmail()).getSenha()) ) {
                			JOptionPane.showMessageDialog( null, "Login e Senha corretas!" );
                			GuiPrincipal.abrir();
                			closeFrame();
                		}
                		else {
                			JOptionPane.showMessageDialog( null, "Login ou Senha incorretas!", "Informa��o", JOptionPane.ERROR_MESSAGE );
                		}
            		}
            		else {
            			JOptionPane.showMessageDialog( null, "Login ou Senha incorretas!", "Informa��o", JOptionPane.ERROR_MESSAGE );
            		}
            	}
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
