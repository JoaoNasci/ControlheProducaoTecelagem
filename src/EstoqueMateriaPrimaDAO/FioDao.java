package EstoqueMateriaPrimaDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import ConexaoBD_DAO.*;
import EnderecoContatos.Endereco;
import EstoqueMateriaPrima.Fio;
import Pessoas.Fornecedor;

public class FioDao implements DAO<Fio> {
	public BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String  sql ;
	
	public FioDao() {
		bd = new BD();
	}

	@Override
	public void Inclusao(Fio incluir) {
		sql = "INSERT INTO Fio(loteFio,Fk_fornecedor, cor, peso, descricao, titulo, composicao)"
			 +" VALUES(?,?,?,?,?,?,?) ";
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
				statement.setString(6, incluir.getTitulo());
				statement.setString(7, incluir.getComposicao());
				
				statement.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o fio: " + e.getMessage());
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
	public void Alter(Fio alterar) {
		sql =  "UPDATE FIO fio "
				+ "SET cor = ?, peso = ?, descricao = ?, titulo = ?, composicao = ? "
				+ "WHERE loteFio = ? and FK_fornecedor = ? ";
		
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, alterar.getCor());
				statement.setDouble(2, alterar.getPeso());
				statement.setString(3, alterar.getDescricao());
				statement.setString(4, alterar.getTitulo());
				statement.setString(5, alterar.getComposicao());
				statement.setString(6, alterar.getLote());
				statement.setString(7, alterar.getFornecedor().getCnpj());
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Fio atualizado com sucesso.");
				} else {
					System.out.println("Fio não encontrado para atualização.");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao atualizar o fio: " + e.getMessage());
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
	public Fio Consulta(String consulta) {
		Fio fio = null;
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
				+ "fi.loteFio, "
				+ "fi.cor, "
				+ "fi.peso, "
				+ "fi.descricao, "
				+ "fi.titulo, "
				+ "fi.composicao "
				+ "FROM Fio fi "
				+ "INNER JOIN Fornecedor f ON fi.FK_fornecedor = f.cnpj "
				+ "INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
				+ "WHERE fi.loteFio = ?";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					fio = new Fio();
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
					fio.setLote(resultSet.getString("loteFio"));
					fio.setFornecedor(fornecedor);
					fio.setCor(resultSet.getString("cor"));
					fio.setPeso(resultSet.getDouble("peso"));
					fio.setDescricao(resultSet.getString("descricao"));
					fio.setTitulo(resultSet.getString("titulo"));
					fio.setComposicao(resultSet.getString("composicao"));
					
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
		return fio;
	}

	@Override
	public List<Fio> ConsultaList() {
		List<Fio> listaFio = new ArrayList<Fio>();
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
				+ "fi.loteFio, "
				+ "fi.cor, "
				+ "fi.peso, "
				+ "fi.descricao, "
				+ "fi.titulo, "
				+ "fi.composicao "
				+ "FROM Fio fi "
				+ "INNER JOIN Fornecedor f ON fi.FK_fornecedor = f.cnpj "
				+ "INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
				+ "WHERE fi.loteFio = ?";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Fio fio = new Fio();
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
					fio.setLote(resultSet.getString("loteFio"));
					fio.setFornecedor(fornecedor);
					fio.setCor(resultSet.getString("cor"));
					fio.setPeso(resultSet.getDouble("peso"));
					fio.setDescricao(resultSet.getString("descricao"));
					fio.setTitulo(resultSet.getString("titulo"));
					fio.setComposicao(resultSet.getString("composicao"));
					
					listaFio.add(fio);
				} 
				if(listaFio.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fio não encontrado.");
					 System.out.println("Fio não encontrado.");  
					
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
		return listaFio;
	}

	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Foi WHERE loteFio = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Fio excluído com sucesso.");
				} else {
					System.out.println("Fio não encontrado para exclusão.");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o fio: " + e.getMessage());
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
