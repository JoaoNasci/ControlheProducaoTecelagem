package PessoasDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConexaoBD_DAO.BD;
import ConexaoBD_DAO.DAO;
import EnderecoContatos.Endereco;
import Pessoas.Cliente;


public class ClienteDAO implements DAO<Cliente> {
	public BD bd ;
	private PreparedStatement statement, statementEndereco;
	private ResultSet resultSet;
	private String  sql;
	
	
	public ClienteDAO() {
		bd = new BD();
	}

	@Override
	public void Inclusao(Cliente cliente) {
		sql = "INSERT INTO Cliente (cnpj, nome, FK_endereco, telefone, email, inscricaoEstadual,"
				+ " nomeFantazia, ponteEmpresa, dataFundacao, Status, razaoSocial, limiteCredito, ramoAtividade)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String sqlEndereco = "INSERT INTO Endereco (cep, rua, numero, bairro, cidade, estado, pais)"
						   + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				
				statementEndereco = bd.conexao.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
				statementEndereco.setString(1, cliente.getEndereco().getCep());
				statementEndereco.setString(2, cliente.getEndereco().getRua());
				statementEndereco.setString(3, cliente.getEndereco().getNumero());
				statementEndereco.setString(4, cliente.getEndereco().getBairro());
				statementEndereco.setString(5, cliente.getEndereco().getCidade());
				statementEndereco.setString(6, cliente.getEndereco().getEstado());
				statementEndereco.setString(7, cliente.getEndereco().getPais());
				
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
				statement.setString(1, cliente.getCnpj());
				statement.setString(2, cliente.getNome());
				statement.setInt(3, enderecoId);
				statement.setString(4, cliente.getTelefone());
				statement.setString(5, cliente.getEmail());
				statement.setString(6, cliente.getInscricaoEstadual());
				statement.setString(7, cliente.getNomeFantasia());
				statement.setString(8, cliente.getPorteEmpresa());
				statement.setDate(9, Date.valueOf(cliente.getDataFundacao()));
				statement.setString(10, cliente.getStatus());
				statement.setString(11, cliente.getRazaoSocial());
				statement.setDouble(12, cliente.getLimiteCredito());
				statement.setString(13, cliente.getRamoAtividade());
				
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Cliente inserido com sucesso.");
				} else {
					System.out.println("Falha ao inserir o Cliente.");
				}
				}else {
					System.out.println("Falha ao inserir o endereço do Cliente.");
				}
			}
		
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o Cliente: " + e.getMessage());
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
	public void Alter(Cliente alterar) {
		sql = "UPDATE Cliente c "
				+ "INNER JOIN Endereco e ON c.FK_endereco = e.idEndereco "
				+ "SET c.nome = ?, e.cep = ?, e.rua = ?, e.numero = ?, e.bairro = ?, e.cidade = ?, e.estado = ?, "
				+ "e.pais = ?, e.complemento = ?, c.telefone = ?, c.email = ?, c.inscricaoEstadual = ?, "
				+ "c.nomeFantasia = ?, c.porteEmpresa = ?, c.dataFundacao = ?, c.Status = ?, "
				+ "c.razaoSocial = ?, c.limiteCredito = ?, c.ramoAtividade = ? "
				+ "WHERE c.cnpj = ?";
			
			try {
				if (!bd.getConectar()) {
					System.out.println("Falha ao conectar ao banco de dados.");
				}else {
					statement = bd.conexao.prepareStatement(sql);
					statement.setString(20, alterar.getCnpj());
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
					statement.setString(12, alterar.getInscricaoEstadual());
					statement.setString(13, alterar.getNomeFantasia());
					statement.setString(14, alterar.getPorteEmpresa());
					statement.setDate(15, Date.valueOf(alterar.getDataFundacao()));
					statement.setString(16, alterar.getStatus());
					statement.setString(17, alterar.getRazaoSocial());
					statement.setDouble(18, alterar.getLimiteCredito());
					statement.setString(19, alterar.getRamoAtividade());
					
					int rowsAffected = statement.executeUpdate();
					
					if (rowsAffected > 0) {
						System.out.println("Cliente atualizado com sucesso.");
					} else {
						System.out.println("Cliente não encontrado para atualização.");
					}
				}
			}
			catch (SQLException e) {
				System.out.println("Erro ao atualizar o cliente: " + e.getMessage());
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
	public Cliente Consulta(String consulta) {
		Cliente cliente = null;
		sql = "SELECT c.cnpj,"
				+ " c.nome,"
				+ "e.cep,"
				+ "e.rua,"
				+ "e.numero,"
				+ "e.bairro,"
				+ "e.cidade,"
				+ "e.estado,"
				+ "e.pais,"
				+ "e.complemento,"
				+ "c.telefone,"
				+ "c.email,"
				+ "c.inscricaoEstadual,"
				+ "c.nomeFantasia,"
				+ "c.porteEmpresa,"
				+ "c.dataFundacao,"
				+ "c.Status,"
				+ "c.razaoSocial,"
				+ "c.limiteCredito,"
				+ "c.ramoAtividade"
				+ " FROM Cliente c INNER JOIN Endereco e ON c.FK_endereco = e.idEndereco "
				+ "WHERE c.cnpj = ?";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					cliente = new Cliente();
					Endereco endereco = new Endereco();
					cliente.setCnpj(resultSet.getString("cnpj"));
					cliente.setNome(resultSet.getString("nome"));
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					cliente.setEndereco(endereco);
					cliente.setTelefone(resultSet.getString("telefone"));
					cliente.setEmail(resultSet.getString("email"));
					cliente.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
					cliente.setNomeFantasia(resultSet.getString("nomeFantasia"));
					cliente.setPorteEmpresa(resultSet.getString("porteEmpresa"));
					cliente.setDataFundacao(resultSet.getDate("datafundacao").toLocalDate());
					cliente.setStatus(resultSet.getString("Status"));
					cliente.setRazaoSocial(resultSet.getString("razaoSocial"));
					cliente.setLimiteCredito(resultSet.getDouble("limiteCredito"));
					cliente.setRamoAtividade(resultSet.getString("ramoAtividade"));
					
					
					return cliente;
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
					 System.out.println("Cliente não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o cliente: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return cliente;
	}

	@Override
	public List<Cliente> ConsultaList() {
		List<Cliente> listaCliente = new ArrayList<>();
		sql = "SELECT c.cnpj,"
				+ " c.nome,"
				+ "e.cep,"
				+ "e.rua,"
				+ "e.numero,"
				+ "e.bairro,"
				+ "e.cidade,"
				+ "e.estado,"
				+ "e.pais,"
				+ "e.complemento,"
				+ "c.telefone,"
				+ "c.email,"
				+ "c.inscricaoEstadual,"
				+ "c.nomeFantasia,"
				+ "c.porteEmpresa,"
				+ "c.dataFundacao,"
				+ "c.Status,"
				+ "c.categoriaProduto,"
				+ "c.prazoEntregaMedio"
				+ " FROM Cliente c INNER JOIN Endereco e ON c.FK_endereco = e.idEndereco ";
		
		try {
			
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Cliente cliente = new Cliente();
					Endereco endereco = new Endereco();
					
					cliente.setCnpj(resultSet.getString("cnpj"));
					cliente.setNome(resultSet.getString("nome"));
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					cliente.setEndereco(endereco);
					cliente.setTelefone(resultSet.getString("telefone"));
					cliente.setEmail(resultSet.getString("email"));
					cliente.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
					cliente.setNomeFantasia(resultSet.getString("nomeFantasia"));
					cliente.setPorteEmpresa(resultSet.getString("porteEmpresa"));
					cliente.setDataFundacao(resultSet.getDate("datafundacao").toLocalDate());
					cliente.setStatus(resultSet.getString("Status"));
					cliente.setRazaoSocial(resultSet.getString("razaoSocial"));
					cliente.setLimiteCredito(resultSet.getDouble("limiteCredito"));
					cliente.setRamoAtividade(resultSet.getString("ramoAtividade"));
					
					
					listaCliente.add(cliente);
				} 
				
				if(listaCliente.isEmpty()) {
					 System.out.println("Cliente não encontrado.");  
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o cliente: " + e.getMessage());
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return listaCliente;
	}

	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Cliente WHERE cnpj = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Cliente excluído com sucesso.");
				} else {
					System.out.println("Cliente não encontrado para exclusão.");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o Cliente: " + e.getMessage());
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
