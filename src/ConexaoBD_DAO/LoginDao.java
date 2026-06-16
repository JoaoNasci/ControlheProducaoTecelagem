package ConexaoBD_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao implements DAO<Login> {
	public BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String  sql;
	
	public LoginDao() {
		bd = new BD();
	}
	
	@Override
	public void Inclusao(Login insert) {
		sql = "INSERT INTO Login (email, senha) VALUES (?, ?)";
		
		try {
			if (bd.getConectar()) {
				System.out.println("Conexão estabelecida com sucesso!");
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, insert.getEmail());
				statement.setString(2, insert.getSenha());
				statement.executeUpdate();
			} else {
				System.out.println("Falha ao estabelecer conexão.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) statement.close();
				bd.fecharConexao();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void Alter(Login alterar) {
		sql = "UPDATE Login SET senha = ? WHERE email = ?";
		
		try {
			if (bd.getConectar()) {
				System.out.println("Conexão estabelecida com sucesso!");
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, alterar.getSenha());
				statement.setString(2, alterar.getEmail());
				statement.executeUpdate();
			} else {
				System.out.println("Falha ao estabelecer conexão.");
			}
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) statement.close();
				bd.fecharConexao();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
					
	}

	@Override
	public Login Consulta(String consulta) {
		Login login = null;
		sql = "SELECT * FROM Login WHERE email = ?";
		
			try {
				if (bd.getConectar()) {
					System.out.println("Conexão estabelecida com sucesso!");
					statement = bd.conexao.prepareStatement(sql);
					statement.setString(1, consulta);
					resultSet = statement.executeQuery();
					
					if (resultSet.next()) {
						login = new Login();
						login.setEmail(resultSet.getString("email"));
						login.setSenha(resultSet.getString("senha"));
						return login;
					}
				} else {
					System.out.println("Falha ao estabelecer conexão.");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (resultSet != null) resultSet.close();
					if (statement != null) statement.close();
					bd.fecharConexao();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return login;
	}

	@Override
	public java.util.List<Login> ConsultaList() {
		
		return null;
	}

	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Login WHERE email = ?";
		
		try {
			if (bd.getConectar()) {
				System.out.println("Conexão estabelecida com sucesso!");
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				statement.executeUpdate();
			} else {
				System.out.println("Falha ao estabelecer conexão.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (statement != null) statement.close();
				bd.fecharConexao();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
