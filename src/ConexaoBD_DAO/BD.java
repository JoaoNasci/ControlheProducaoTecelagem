package ConexaoBD_DAO;

import java.sql.*;

public class BD {
	public Connection conexao = null;
	private final String  DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String BD_NOME = "controle_producao_tecelagem";
	private final String HOST = "localhost";
	private final String PORTA = "3306";
	private final String URL = "jdbc:mysql://" + HOST + ":" + PORTA + "/" + BD_NOME;
	private final String USUARIO = "root";
	private final String SENHA = "";
	
	public boolean getConectar() {
		boolean conectado = false;
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Conexão estabelecida com sucesso!");
			 conectado = true;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver JDBC não encontrado: " + e.getMessage());
			conectado = false;
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
			conectado = false;
		}
		return conectado;
	}
	
	public void fecharConexao() {
			try {
				conexao.close();
				System.out.println("Conexão fechada com sucesso!");
			} catch (SQLException e) {
				System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			}
		
	}
}
