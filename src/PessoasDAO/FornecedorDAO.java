package PessoasDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConexaoBD_DAO.BD;
import ConexaoBD_DAO.DAO;
import EnderecoContatos.*;
import Pessoas.Fornecedor;



public class FornecedorDAO implements DAO<Fornecedor> {
	public BD bd ;
	private PreparedStatement statement, statementEndereco;
	private ResultSet resultSet;
	private String  sql;
	
	
	public FornecedorDAO() {
		bd = new BD();
	}
 

	@Override
	public void Inclusao(Fornecedor fornecedor) {  
		sql = "INSERT INTO Fornecedor (cnpj, nome, FK_endereco, telefone, email, inscricaoEstadual,"
				+ " nomeFantazia, ponteEmpresa, dataFundacao, Status, categoriaProduto, prazoEntregaMedio)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String sqlEndereco = "INSERT INTO Endereco (cep, rua, numero, bairro, cidade, estado, pais)"
						   + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				
				statementEndereco = bd.conexao.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
				statementEndereco.setString(1, fornecedor.getEndereco().getCep());
				statementEndereco.setString(2, fornecedor.getEndereco().getRua());
				statementEndereco.setString(3, fornecedor.getEndereco().getNumero());
				statementEndereco.setString(4, fornecedor.getEndereco().getBairro());
				statementEndereco.setString(5, fornecedor.getEndereco().getCidade());
				statementEndereco.setString(6, fornecedor.getEndereco().getEstado());
				statementEndereco.setString(7, fornecedor.getEndereco().getPais());
				
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
				statement.setString(1, fornecedor.getCnpj());
				statement.setString(2, fornecedor.getNome());
				statement.setInt(3, enderecoId);
				statement.setString(4, fornecedor.getTelefone());
				statement.setString(5, fornecedor.getEmail());
				statement.setString(6, fornecedor.getInscricaoEstadual());
				statement.setString(7, fornecedor.getNomeFantasia());
				statement.setString(8, fornecedor.getPorteEmpresa());
				statement.setDate(9, Date.valueOf(fornecedor.getDataFundacao()));
				statement.setString(10, fornecedor.getStatus());
				statement.setString(11, fornecedor.getCategoriaProduto());
				statement.setShort(12, fornecedor.getPrazoEntrega());
				
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Fornecedor inserido com sucesso.");
				} else {
					System.out.println("Falha ao inserir o fornecedor.");
				}
				}else {
					System.out.println("Falha ao inserir o endereço do fornecedor.");
				}
			}
		
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o fornecedor: " + e.getMessage());
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
	public void Alter(Fornecedor alterar) {
		sql = "UPDATE Fornecedor f "
			+ "INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
			+ "SET f.nome = ?, e.cep = ?, e.rua = ?, e.numero = ?, e.bairro = ?, e.cidade = ?, e.estado = ?, "
			+ "e.pais = ?, e.complemento = ?, f.telefone = ?, f.email = ?, f.inscricaoEstadual = ?, "
			+ "f.nomeFantasia = ?, f.porteEmpresa = ?, f.dataFundacao = ?, f.Status = ?, "
			+ "f.categoriaProduto = ?, f.prazoEntregaMedio = ? "
			+ "WHERE f.cnpj = ?";
		
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				statement = bd.conexao.prepareStatement(sql);
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
				statement.setString(17, alterar.getCategoriaProduto());
				statement.setShort(18, alterar.getPrazoEntrega());
				statement.setString(19, alterar.getCnpj());
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Fornecedor atualizado com sucesso.");
				} else {
					System.out.println("Fornecedor não encontrado para atualização.");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao atualizar o fornecedor: " + e.getMessage());
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
	public Fornecedor Consulta(String c) {
		Fornecedor fornecedor = null;
		sql = "SELECT f.cnpj,"
				+ " f.nome,"
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
				+ "f.inscricaoEstadual,"
				+ "f.nomeFantasia,"
				+ "f.porteEmpresa,"
				+ "f.dataFundacao,"
				+ "f.Status,"
				+ "f.categoriaProduto,"
				+ "f.prazoEntregaMedio"
				+ " FROM fornecedor f INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
				+ "WHERE f.cnpj = ?";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, c);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					fornecedor = new Fornecedor();
					Endereco endereco = new Endereco();
					fornecedor.setCnpj(resultSet.getString("cnpj"));
					fornecedor.setNome(resultSet.getString("nome"));
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					fornecedor.setEndereco(endereco);
					fornecedor.setTelefone(resultSet.getString("telefone"));
					fornecedor.setEmail(resultSet.getString("email"));
					fornecedor.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
					fornecedor.setNomeFantasia(resultSet.getString("nomeFantasia"));
					fornecedor.setPorteEmpresa(resultSet.getString("porteEmpresa"));
					fornecedor.setDataFundacao(resultSet.getDate("datafundacao").toLocalDate());
					fornecedor.setStatus(resultSet.getString("Status"));
					fornecedor.setCategoriaProduto(resultSet.getString("categoriaProduto"));
					fornecedor.setPrazoEntrega(resultSet.getShort("prazoEntregaMedio"));
					
					return fornecedor;
				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor não encontrado.");
					 System.out.println("Fornecedor não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o fornecedor: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return fornecedor;
	}
		
	@Override
	public List<Fornecedor> ConsultaList() {
		
		List<Fornecedor> listaFornecedores = new ArrayList<>();
		sql = "SELECT f.cnpj,"
				+ " f.nome,"
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
				+ "f.inscricaoEstadual,"
				+ "f.nomeFantasia,"
				+ "f.porteEmpresa,"
				+ "f.dataFundacao,"
				+ "f.Status,"
				+ "f.categoriaProduto,"
				+ "f.prazoEntregaMedio"
				+ " FROM fornecedor f INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco ";
		
		try {
			
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Fornecedor fornecedor = new Fornecedor();
					Endereco endereco = new Endereco();
					
					fornecedor.setCnpj(resultSet.getString("cnpj"));
					fornecedor.setNome(resultSet.getString("nome"));
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					fornecedor.setEndereco(endereco);
					fornecedor.setTelefone(resultSet.getString("telefone"));
					fornecedor.setEmail(resultSet.getString("email"));
					fornecedor.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
					fornecedor.setNomeFantasia(resultSet.getString("nomeFantasia"));
					fornecedor.setPorteEmpresa(resultSet.getString("porteEmpresa"));
					fornecedor.setDataFundacao(resultSet.getDate("datafundacao").toLocalDate());
					fornecedor.setStatus(resultSet.getString("Status"));
					fornecedor.setCategoriaProduto(resultSet.getString("categoriaProduto"));
					fornecedor.setPrazoEntrega(resultSet.getShort("prazoEntregaMedio"));
					
					listaFornecedores.add(fornecedor);
				} 
				
				if(listaFornecedores.isEmpty()) {
					 System.out.println("Fornecedor não encontrado.");  
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o fornecedor: " + e.getMessage());
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return listaFornecedores;
	}
	
	
	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Fornecedor WHERE cnpj = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Fornecedor excluído com sucesso.");
				} else {
					System.out.println("Fornecedor não encontrado para exclusão.");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o fornecedor: " + e.getMessage());
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
