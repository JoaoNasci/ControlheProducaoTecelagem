package EstoqueMateriaPrimaDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConexaoBD_DAO.*;
import EnderecoContatos.Endereco;
import EstoqueMateriaPrima.Malha;
import Pessoas.Fornecedor;

public class MalhaDao implements DAO<Malha>{
	public BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String  sql ;
	
	public MalhaDao() {
		bd = new BD();
	}
	
	@Override
	public void Inclusao(Malha incluir) {
		sql = "INSERT INTO Malha (loteMalha, FK_fornecedor, cor, peso, descricao,"
				+ " largura, gramatura, tipoTrama)"
				 +" VALUES(?,?,?,?,?,?,?,?) ";
		
			try {
				if(!bd.getConectar()) {
					System.out.println("Falha ao conectar ao banco de dados.");

				}else {
					
					statement = bd.conexao.prepareStatement(sql);
					
					statement.setString(1, incluir.getLote());
					statement.setString(2, incluir.getFornecedor().getCnpj());
					statement.setString(3, incluir.getCor());
					statement.setDouble(4, incluir.getPeso());
					statement.setString(5, incluir.getDescricao());
					statement.setDouble(6, incluir.getLargura());
					statement.setInt(7, incluir.getGramatura());
					statement.setString(8, incluir.getTipoTrama());
					
					statement.executeUpdate();
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o malha: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Erro não previsto: "+ e.getMessage());
			}finally {
				try {
					if(statement != null) statement.close();
					 bd.fecharConexao();
				 } catch (SQLException e) {
					 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
				 }
			};
			
		
	}
	
	
	@Override
	public void Alter(Malha alterar) {
		sql =  "UPDATE FIO fio "
				+ "SET cor = ?, peso = ?, descricao = ?, largura = ?, gramatura = ?, tipoTrama =? "
				+ "WHERE loteMalha = ? and FK_fornecedor = ? ";
		
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, alterar.getCor());
				statement.setDouble(2, alterar.getPeso());
				statement.setString(3, alterar.getDescricao());
				statement.setDouble(4, alterar.getLargura());
				statement.setInt(5, alterar.getGramatura());
				statement.setString(6, alterar.getTipoTrama());
				statement.setString(7, alterar.getLote());
				statement.setString(8, alterar.getFornecedor().getCnpj());
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Malha atualizado com sucesso.");
				} else {
					System.out.println("Malha não encontrado para atualização.");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao atualizar o malha: " + e.getMessage());
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
	public Malha Consulta(String consulta) {
		Malha malha = null;
		sql = "SELECT "
				+ "e.cep, "
				+ "e.rua, "
				+ "e.numero, "
				+ "e.bairro, "
				+ "e.cidade, "
				+ "e.estado, "
				+ "e.pais, "
				+ "e.complemento, "
				+ "f.cnpj, "
				+ "f.nome, "
				+ "f.telefone, "
				+ "f.email, "
				+ "f.inscricaoEstadual, "
				+ "f.nomeFantasia, "
				+ "f.porteEmpresa, "
				+ "f.dataFundacao, "
				+ "f.Status, "
				+ "f.categoriaProduto, "
				+ "f.prazoEntregaMedio, "
				+ "ma.loteMalha, "
				+ "ma.cor, "
				+ "ma.peso, "
				+ "ma.descricao, "
				+ "ma.largura, "
				+ "ma.gramatura, "
				+ "ma.tipoTrama "
				+ "FROM Malha ma "
				+ "INNER JOIN Fornecedor f ON ma.FK_fornecedor = f.cnpj "
				+ "INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
				+ "WHERE ma.loteMalha = ?";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					malha = new Malha();
					Endereco endereco = new Endereco();
					Fornecedor fornecedor = new Fornecedor();
					
					
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					fornecedor.setCnpj(resultSet.getString("cnpj"));
					fornecedor.setNome(resultSet.getString("nome"));
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
					malha.setLote(resultSet.getString("loteFio"));
					malha.setFornecedor(fornecedor);
					malha.setCor(resultSet.getString("cor"));
					malha.setPeso(resultSet.getDouble("peso"));
					malha.setDescricao(resultSet.getString("descricao"));
					malha.setLargura(resultSet.getDouble("largura"));
					malha.setGramatura(resultSet.getInt("gramatura"));
					malha.setTipoTrama(resultSet.getString("tipoTrama"));
					
				} else {
					JOptionPane.showMessageDialog(null, "Malha não encontrado.");
					 System.out.println("Malha não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o malha: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return malha;
	}
	
	
	@Override
	public List<Malha> ConsultaList() {
		List<Malha> listaMalha = new ArrayList<Malha>();
		sql = "SELECT "
				+ "e.cep, "
				+ "e.rua, "
				+ "e.numero, "
				+ "e.bairro, "
				+ "e.cidade, "
				+ "e.estado, "
				+ "e.pais, "
				+ "e.complemento, "
				+ "f.cnpj, "
				+ "f.nome, "
				+ "f.telefone, "
				+ "f.email, "
				+ "f.inscricaoEstadual, "
				+ "f.nomeFantasia, "
				+ "f.porteEmpresa, "
				+ "f.dataFundacao, "
				+ "f.Status, "
				+ "f.categoriaProduto, "
				+ "f.prazoEntregaMedio, "
				+ "ma.loteMalha, "
				+ "ma.cor, "
				+ "ma.peso, "
				+ "ma.descricao, "
				+ "ma.largura, "
				+ "ma.gramatura, "
				+ "ma.tipoTrama "
				+ "FROM Malha ma "
				+ "INNER JOIN Fornecedor f ON ma.FK_fornecedor = f.cnpj "
				+ "INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco ";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Malha malha = new Malha();
					Endereco endereco = new Endereco();
					Fornecedor fornecedor = new Fornecedor();
					
					
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					fornecedor.setCnpj(resultSet.getString("cnpj"));
					fornecedor.setNome(resultSet.getString("nome"));
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
					malha.setLote(resultSet.getString("loteFio"));
					malha.setFornecedor(fornecedor);
					malha.setCor(resultSet.getString("cor"));
					malha.setPeso(resultSet.getDouble("peso"));
					malha.setDescricao(resultSet.getString("descricao"));
					malha.setLargura(resultSet.getDouble("largura"));
					malha.setGramatura(resultSet.getInt("gramatura"));
					malha.setTipoTrama(resultSet.getString("tipoTrama"));
					
					listaMalha.add(malha);
				} 
				if(listaMalha.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Malha não encontrado.");
					 System.out.println("Malha não encontrado.");  
					
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
		return listaMalha;
	}
	
	
	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Malha WHERE loteMalha = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Malha excluído com sucesso.");
				} else {
					System.out.println("Malha não encontrado para exclusão.");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o malha: " + e.getMessage());
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
