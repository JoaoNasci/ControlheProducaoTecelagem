package View;

import java.awt.Container;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import EstoqueMateriaPrima.Fio;
import Pessoas.Funcionario;
import Tecelagem.Maquina;
import Tecelagem.Producao;
import TecelagemDAO.ProducaoDAO;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;




public class GuiPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private Container contentPane;
    private JMenuBar mnBarra;
    private JMenu mnConsultas, mnCadastros, mnAjuda;
    private JMenuItem miPessoaFisica, miPessoaJuridica, miProduto, miCompra;
    private JMenuItem miSobre,miSair;
    JButton btnConsultarProducao;
    private JTextField textProdLote;
    private JTable tableProducao;
    private JScrollPane scrollPaneProducao;

    public GuiPrincipal() {
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setTitle("Menu Principal");
        setBounds(0, 0, 800, 600);

        contentPane = getContentPane();
        getContentPane().setLayout(null);
        
        
        JLabel lblProducao = new JLabel("Produção");
        lblProducao.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblProducao.setBounds(10, 11, 99, 14);
        getContentPane().add(lblProducao);
        
        textProdLote = new JTextField();
        textProdLote.setBounds(49, 54, 86, 20);
        getContentPane().add(textProdLote);
        textProdLote.setColumns(10);
        
        JLabel lblProdLote = new JLabel("Lote :");
        lblProdLote.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblProdLote.setBounds(10, 56, 46, 14);
        getContentPane().add(lblProdLote);
        
        btnConsultarProducao = new JButton("Consultar");
        
        btnConsultarProducao.setBounds(145, 53, 89, 23);
        getContentPane().add(btnConsultarProducao);
        
        scrollPaneProducao = new JScrollPane();
        scrollPaneProducao.setBounds(10, 80, 764, 406);
        getContentPane().add(scrollPaneProducao);
        
      
       
        mnBarra = new JMenuBar();

        mnConsultas = new JMenu("Arquivo");
        mnConsultas.setMnemonic('A');
        mnBarra.add(mnConsultas);
        
        mnCadastros = new JMenu("Cadastro");
        mnCadastros.setMnemonic('C');
        mnBarra.add(mnCadastros);
        
        mnAjuda = new JMenu("Ajuda");
        mnAjuda.setMnemonic('A');
        mnBarra.add(mnAjuda);

        miSair = new JMenuItem( "Sair" );
        miSair.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.ALT_MASK) );
        mnConsultas.add(miSair);

        miCompra = new JMenuItem("Compra");
        miCompra.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_C, ActionEvent.ALT_MASK) );
        mnCadastros.add(miCompra);
        
        miProduto = new JMenuItem("Produto");
        miProduto.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_P, ActionEvent.ALT_MASK) );
        mnCadastros.add(miProduto);

        miPessoaFisica = new JMenuItem("Pessoa Física");
        miPessoaFisica.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_J, ActionEvent.ALT_MASK) );
        mnCadastros.add(miPessoaFisica);
        
        miPessoaJuridica = new JMenuItem("Pessoa Jurídica");
        miPessoaJuridica.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_J, ActionEvent.ALT_MASK) );
        mnCadastros.add(miPessoaJuridica);
        
        miSobre = new JMenuItem("Sobre...");
        //miSobre.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_H, ActionEvent.ALT_MASK) );
        mnAjuda.add(miSobre);
        
        setJMenuBar(mnBarra);
    }

    private void definirEventos() {
    	btnConsultarProducao.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!textProdLote.getText().isEmpty()) {
        			try {
        				  ProducaoDAO producaoDao = new ProducaoDAO();
        				  Producao producao = producaoDao.Consulta(textProdLote.getName());
        				  List<Funcionario> listfuncio = producao.getOperador();
        				  List<Maquina> listmaquina = producao.getMaquina();
        				  List<Fio> listfio = producao.getEntradaMateriaPrima(); 
        				  
        				
        				  
        				  DefaultTableModel tableModel = new DefaultTableModel(
        				            new String[]{"Id Pedido","Data Abertura","Privisão Termino","Produção Total","Cliente","Descrição","Status",
        				            		"Operador", "ID Maquina","Modelo","Tipo","Situação", 
        				            		"Lote Fio","Cor","Peso","Descrição","Titulo","Composição",
        				            		"Lote Malha","Cor","Peso","Descrição","Largura","Gramatura","Tipo Trama",
        				            		"Qtd Planejada", "Qtd Produzida", "Qualidade"}, 0
        				        );
        				  
        				  
        				  for(Funcionario funcio : listfuncio ){
        					  for(Maquina maquina : listmaquina) {
        						  for(Fio fio : listfio) {
        							  tableModel.addRow(new String[] {
        									producao.getPedido().getIdPedido(),
        									producao.getPedido().getDataAbertura().toString(),
        									producao.getPedido().getPrivisaoTerminio().toString(),
        									String.valueOf(producao.getPedido().getProducaoTotal()),
        									producao.getPedido().getCliente().getNomeFantasia(),
        									producao.getPedido().getDescricao(),
        									producao.getPedido().getStatus(),
        									funcio.getNome(),
        									String.valueOf(maquina.getIdMaquina()),
        									maquina.getModelo(),
        									maquina.getTipo(),
        									maquina.getSituacao(),
        									fio.getLote(),
        									fio.getCor(),
        									String.valueOf(fio.getPeso()),
        									fio.getDescricao(),
        									fio.getTitulo(),
        									fio.getComposicao(),
        									producao.getSaidaMateriaPrima().getLote(),
        									producao.getSaidaMateriaPrima().getCor(),
        									String.valueOf(producao.getSaidaMateriaPrima().getPeso()),
        									producao.getSaidaMateriaPrima().getDescricao(),
        									String.valueOf(producao.getSaidaMateriaPrima().getLargura()),
        									String.valueOf(producao.getSaidaMateriaPrima().getGramatura()),
        									producao.getSaidaMateriaPrima().getTipoTrama(),
        									String.valueOf(producao.getQuantidadePlanejada()),
        									String.valueOf(producao.getQuantidadePlanejada()),
        									producao.getQualidade()
        							  });
        						  }
        					  }
        				  }
        				  
        				  	tableProducao = new JTable(tableModel);
        				  	scrollPaneProducao.setViewportView(tableProducao);
        				
        			} catch (Exception ee) {
						// TODO: handle exception
					}
        		}
        	}
        });
    	
        miSair.addActionListener(
                new ActionListener() {
                	public void actionPerformed( ActionEvent e) {
                		System.exit(0);
                	}
                });

        miPessoaFisica.addActionListener(
                new ActionListener() {
                	public void actionPerformed( ActionEvent e) {
                		// aqui vai o codigo para chamar o exemplo 8.3
                		JOptionPane.showMessageDialog(null, "Ação de cadastro de Pessoa Física",
                				"Informação", JOptionPane.INFORMATION_MESSAGE );
                	}
                });
        
        miPessoaJuridica.addActionListener(
                new ActionListener() {
                	public void actionPerformed( ActionEvent e) {
                		// aqui vai o codigo para chamar o exemplo 8.3
                		JOptionPane.showMessageDialog(null, "Ação de cadastro de Pessoa Jur�dica",
								"Informação", JOptionPane.QUESTION_MESSAGE );
                	}
                });
        
        miCompra.addActionListener(
                new ActionListener() {
                	public void actionPerformed( ActionEvent e) {
                		// aqui vai o codigo para chamar o exemplo 8.3
                		JOptionPane.showMessageDialog(null, "Ação de cadastro de Compra",
								"Informação", JOptionPane.PLAIN_MESSAGE );
                	}
                });
        
        miProduto.addActionListener(
                new ActionListener() {
                	public void actionPerformed( ActionEvent e) {
                		// aqui vai o codigo para chamar o exemplo 8.3
                		JOptionPane.showMessageDialog(null, "Ação de cadastro de Produto",
								"Informação", JOptionPane.WARNING_MESSAGE );
                	}
                });
        
        miSobre.addActionListener(
                new ActionListener() {
                	public void actionPerformed( ActionEvent e) {
                		// aqui vai o codigo para chamar o exemplo 8.3
                		String message = "Desenvolvidos por:\n" +
                						 "JoaoNascin - https://github.com/JoaoNascin" ;
                		String title = "Créditos";
                		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE );
                	}
                });
    }

    public static void abrir() {
        GuiPrincipal frame = new GuiPrincipal();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation(
                (tela.width - frame.getSize().width) / 2,
                (tela.height - frame.getSize().height) / 2
        );

        frame.setVisible(true);
    }
}
