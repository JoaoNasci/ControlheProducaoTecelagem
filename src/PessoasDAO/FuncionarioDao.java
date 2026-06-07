package PessoasDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConexaoBD_DAO.BD;
import ConexaoBD_DAO.DAO;
import EnderecoContatos.Endereco;
import Pessoas.Cliente;
import Pessoas.Funcionario;

public class FuncionarioDao implements DAO<Funcionario>{
	public BD bd ;
	private PreparedStatement statement, statementEndereco;
	private ResultSet resultSet;
	private String  sql;
	
	public FuncionarioDao() {
		this.bd = new BD();
	}

	@Override
	public void Inclusao(Funcionario funcionario) {
		sql = "INSERT INTO Funcionario (nome, FK_endereco, telefone, email, cpf,"
				+ " dataNascimento, sexo, pis, dataAdmissao, cargo, turno)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String sqlEndereco = "INSERT INTO Endereco (cep, rua, numero, bairro, cidade, estado, pais)"
						   + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				
				statementEndereco = bd.conexao.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
				statementEndereco.setString(1, funcionario.getEndereco().getCep());
				statementEndereco.setString(2, funcionario.getEndereco().getRua());
				statementEndereco.setString(3, funcionario.getEndereco().getNumero());
				statementEndereco.setString(4, funcionario.getEndereco().getBairro());
				statementEndereco.setString(5, funcionario.getEndereco().getCidade());
				statementEndereco.setString(6, funcionario.getEndereco().getEstado());
				statementEndereco.setString(7, funcionario.getEndereco().getPais());
				
				statementEndereco.executeUpdate();
				
				int enderecoId = 0;
				var generatedKeys = statementEndereco.getGeneratedKeys();
				if (generatedKeys.next()) {
					enderecoId = generatedKeys.getInt(1);
				} else {
					System.out.println("Falha ao obter o ID do endereço.");
					
				}
				statementEndereco.close();
				
				
				if(enderecoId > 0) {
				
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, funcionario.getNome());
				statement.setInt(2, enderecoId);
				statement.setString(3, funcionario.getTelefone());
				statement.setString(4, funcionario.getEmail());
				statement.setString(5, funcionario.getCpf());
				statement.setDate(6, Date.valueOf(funcionario.getDataNascimento()));
				statement.setString(7, funcionario.getSexo());
				statement.setString(8, funcionario.getPis());
				statement.setDate(9, Date.valueOf(funcionario.getDataAdmissao()));
				statement.setString(10, funcionario.getCargo());
				statement.setString(11, funcionario.getTurno());
				
				
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Funcionario inserido com sucesso.");
				} else {
					System.out.println("Falha ao inserir o funcionario.");
				}
				}else {
					System.out.println("Falha ao inserir o endereço do funcionario.");
				}
			}
		
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o funcionario: " + e.getMessage());
		}
		 finally {
			 try {
				 if(statement != null) statement.close();
				 if(statementEndereco != null) statementEndereco.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
	}

	@Override
	public void Alter(Funcionario alterar) {
		sql = "UPDATE Funcionario f "
				+ "INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
				+ "SET f.nome = ?, e.cep = ?, e.rua = ?, e.numero = ?, e.bairro = ?,"
				+ " e.cidade = ?, e.estado = ?, e.pais = ?, e.complemento = ?,"
				+ " f.telefone = ?, f.email = ?, f.dataNascimento = ?, f.sexo = ?,"
				+ " f.pis = ?, f.dataAdmissao = ?, f.cargo = ?, f.turno = ? "
				+ "WHERE f.cpf = ?";
			
			try {
				if (!bd.getConectar()) {
					System.out.println("Falha ao conectar ao banco de dados.");
				}else {
					statement = bd.conexao.prepareStatement(sql);
					statement.setString(18, alterar.getCpf());
					statement.setString(1, alterar.getNome());
					statement.setString(2, alterar.getEndereco().getCep());
					statement.setString(3, alterar.getEndereco().getRua());
					statement.setString(4, alterar.getEndereco().getNumero());
					statement.setString(5, alterar.getEndereco().getBairro());
					statement.setString(6, alterar.getEndereco().getCidade());
					statement.setString(7, alterar.getEndereco().getEstado());
					statement.setString(8, alterar.getEndereco().getPais());
					statement.setString(9, alterar.getEndereco().getComplemento());
					statement.setString(10, alterar.getTelefone());
					statement.setString(11, alterar.getEmail());
					statement.setDate(12, Date.valueOf(alterar.getDataNascimento()));
					statement.setString(13, alterar.getSexo());
					statement.setString(14, alterar.getPis());
					statement.setDate(15, Date.valueOf(alterar.getDataAdmissao()));
					statement.setString(16, alterar.getCargo());
					statement.setString(17, alterar.getTurno());
					
					int rowsAffected = statement.executeUpdate();
					
					if (rowsAffected > 0) {
						System.out.println("Funcionario atualizado com sucesso.");
					} else {
						System.out.println("Funcionario não encontrado para atualização.");
					}
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao atualizar o funcionario: " + e.getMessage());
			}
			 finally {
				 try {
					 if(statement != null) statement.close();
					 bd.fecharConexao();
				 } catch (SQLException e) {
					 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
				 }
			 }
		
	}

	@Override
	public Funcionario Consulta(String consulta) {
		Funcionario funcionario = null;
		sql = "SELECT "
				+ "c.nome,"
				+ "e.cep,"
				+ "e.rua,"
				+ "e.numero,"
				+ "e.bairro,"
				+ "e.cidade,"
				+ "e.estado,"
				+ "e.pais,"
				+ "e.complemento,"
				+ "f.telefone,"
				+ "f.email,"
				+ "f.cpf,"
				+ "f.dataNascimento,"
				+ "f.sexo,"
				+ "f.pis,"
				+ "f.dataAdmissao,"
				+ "f.cargo,"
				+ "f.turno,"
				+ " FROM Funcionario f INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
				+ "WHERE f.cpf = ?";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					funcionario = new Funcionario();
					Endereco endereco = new Endereco();
					funcionario.setNome(resultSet.getString("nome"));
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					funcionario.setEndereco(endereco);
					funcionario.setTelefone(resultSet.getString("telefone"));
					funcionario.setEmail(resultSet.getString("email"));
					funcionario.setCpf(resultSet.getString("cpf"));
					funcionario.setDataNascimento(resultSet.getDate("dataNascimento").toLocalDate());
					funcionario.setSexo(resultSet.getString("sexo"));
					funcionario.setPis(resultSet.getString("pis"));
					funcionario.setDataAdmissao(resultSet.getDate("dataAdmissao").toLocalDate());
					funcionario.setCargo(resultSet.getString("cargo"));
					funcionario.setTurno(resultSet.getString("turno"));
					
					
					return funcionario;
				} else {
					JOptionPane.showMessageDialog(null, "Funcionario não encontrado.");
					 System.out.println("Funcionario não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o funcionario: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return funcionario;
	}

	@Override
	public List<Funcionario> ConsultaList() {
		List<Funcionario> listaFuncionario = new ArrayList<>();
		sql = "SELECT "
				+ "c.nome,"
				+ "e.cep,"
				+ "e.rua,"
				+ "e.numero,"
				+ "e.bairro,"
				+ "e.cidade,"
				+ "e.estado,"
				+ "e.pais,"
				+ "e.complemento,"
				+ "f.telefone,"
				+ "f.email,"
				+ "f.cpf,"
				+ "f.dataNascimento,"
				+ "f.sexo,"
				+ "f.pis,"
				+ "f.dataAdmissao,"
				+ "f.cargo,"
				+ "f.turno,"
				+ " FROM Funcionario f INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco ";
		
		try {
			
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Funcionario funcionario = new Funcionario();
					Endereco endereco = new Endereco();
					
					funcionario.setNome(resultSet.getString("nome"));
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					funcionario.setEndereco(endereco);
					funcionario.setTelefone(resultSet.getString("telefone"));
					funcionario.setEmail(resultSet.getString("email"));
					funcionario.setCpf(resultSet.getString("cpf"));
					funcionario.setDataNascimento(resultSet.getDate("dataNascimento").toLocalDate());
					funcionario.setSexo(resultSet.getString("sexo"));
					funcionario.setPis(resultSet.getString("pis"));
					funcionario.setDataAdmissao(resultSet.getDate("dataAdmissao").toLocalDate());
					funcionario.setCargo(resultSet.getString("cargo"));
					funcionario.setTurno(resultSet.getString("turno"));
					
					
					listaFuncionario.add(funcionario);
				} 
				
				if(listaFuncionario.isEmpty()) {
					 System.out.println("Funcionario não encontrado.");  
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o funcionario: " + e.getMessage());
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return listaFuncionario;
	}

	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Funcionario WHERE cpf = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Funcionario excluído com sucesso.");
				} else {
					System.out.println("Funcionario não encontrado para exclusão.");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o funcionario: " + e.getMessage());
		}finally {
			 try {
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		
	}

}
