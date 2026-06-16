package TecelagemDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConexaoBD_DAO.*;
import EnderecoContatos.Endereco;
import Pessoas.Cliente;
import Tecelagem.Pedido;


public class PedidoDAO implements DAO<Pedido> {
	public BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String  sql ;
	
	public PedidoDAO() {
		bd = new BD();
	}
	
	@Override
	public void Inclusao(Pedido incluir) {
		sql = "INSERT INTO Pedido (idPedido, dataAbertura, previsaoTermino, producaoTotal, "
				+ "FK_cliente, descricao, status)"
				 +" VALUES(?, ?, ?, ?, ?, ?, ?) ";
		
			try {
				if(!bd.getConectar()) {
					System.out.println("Falha ao conectar ao banco de dados.");

				}else {
					
					statement = bd.conexao.prepareStatement(sql);
					statement.setString(1, incluir.getIdPedido());
					statement.setDate(2, Date.valueOf(incluir.getDataAbertura()));
					statement.setDate(3, Date.valueOf(incluir.getPrivisaoTerminio()));
					statement.setDouble(4, incluir.getProducaoTotal());
					statement.setString(5, incluir.getCliente().getCnpj());
					statement.setString(6, incluir.getDescricao());
					statement.setString(7, incluir.getStatus());
				
					
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
			}
		
	}

	@Override
	public void Alter(Pedido alterar) {
		sql =  "UPDATE Pedido "
				+ "SET dataAbertura = ?, previsaoTermino = ?,"
				+ " producaoTotal = ?, descricao = ?, status = ?, "
				+ "WHERE idPedido = ? and FK_cliente = ? ";
		
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setDate(1, Date.valueOf(alterar.getDataAbertura()));
				statement.setDate(2, Date.valueOf(alterar.getPrivisaoTerminio()));
				statement.setDouble(3, alterar.getProducaoTotal());
				statement.setString(4, alterar.getDescricao());
				statement.setString(5, alterar.getStatus());
				statement.setString(6, alterar.getIdPedido());
				statement.setString(7, alterar.getCliente().getCnpj());
				
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Pedido atualizado com sucesso.");
				} else {
					System.out.println("Pedido não encontrado para atualização.");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao atualizar o pedido: " + e.getMessage());
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
	public Pedido Consulta(String consulta) {
		Pedido pedido = null;
		
		sql = "SELECT "
				+ "p.idPedido, "
				+ "p.dataAbertura, "
				+ "p.previsaoTermino, "
				+ "p.producaoTotal, "
				+ "p.descricao, "
				+ "p.status, "
				+ "e.cep,"
				+ "e.rua,"
				+ "e.numero,"
				+ "e.bairro,"
				+ "e.cidade,"
				+ "e.estado,"
				+ "e.pais,"
				+ "e.complemento,"
				+ "c.cnpj, "
				+ "c.nome, "
				+ "c.telefone, "
				+ "c.email, "
				+ "c.inscricaoEstadual, "
				+ "c.nomeFantazia, "
				+ "c.ponteEmpresa, "
				+ "c.dataFundacao, "
				+ "c.Status, "
				+ "c.razaoSocial, "
				+ "c.limiteCredito, "
				+ "c.ramoAtividade "
				+ "FROM Pedido p INNER JOIN Cliente c ON p.FK_cliente = c.cnpj "
				+ "INNER JOIN Endereco e ON c.FK_endereco = e.idEndereco "
				+ "WHERE p.idPedido = ?";
			
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					pedido = new Pedido();
					Cliente cliente = new Cliente();
					Endereco endereco = new Endereco();
					
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					cliente.setCnpj(resultSet.getString("cnpj"));
					cliente.setNome(resultSet.getString("nome"));
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
					pedido.setIdPedido(resultSet.getString("idPedido"));
					pedido.setDataAbertura(resultSet.getDate("dataAbertura").toLocalDate());
					pedido.setPrivisaoTerminio(resultSet.getDate("previsaoTermino").toLocalDate());
					pedido.setProducaoTotal(resultSet.getDouble("producaoTotal"));
					pedido.setCliente(cliente);
					pedido.setDescricao(resultSet.getString("descricao"));
					pedido.setStatus(resultSet.getString("status"));
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Pedido não encontrado.");
					 System.out.println("Pedido não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o pedido: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return pedido;
	}
	

@Override
public List<Pedido> ConsultaList() {
		List<Pedido> listpedido = new ArrayList<Pedido>();
		
		sql = "SELECT "
				+ "p.idPedido, "
				+ "p.dataAbertura, "
				+ "p.previsaoTermino, "
				+ "p.producaoTotal, "
				+ "p.descricao, "
				+ "p.status, "
				+ "e.cep,"
				+ "e.rua,"
				+ "e.numero,"
				+ "e.bairro,"
				+ "e.cidade,"
				+ "e.estado,"
				+ "e.pais,"
				+ "e.complemento,"
				+ "c.cnpj, "
				+ "c.nome, "
				+ "c.telefone, "
				+ "c.email, "
				+ "c.inscricaoEstadual, "
				+ "c.nomeFantazia, "
				+ "c.ponteEmpresa, "
				+ "c.dataFundacao, "
				+ "c.Status, "
				+ "c.razaoSocial, "
				+ "c.limiteCredito, "
				+ "c.ramoAtividade "
				+ "FROM Pedido p INNER JOIN Cliente c ON p.FK_cliente = c.cnpj "
				+ "INNER JOIN Endereco e ON c.FK_endereco = e.idEndereco ";
			
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Pedido pedido = new Pedido();
					Cliente cliente = new Cliente();
					Endereco endereco = new Endereco();
					
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					cliente.setCnpj(resultSet.getString("cnpj"));
					cliente.setNome(resultSet.getString("nome"));
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
					pedido.setIdPedido(resultSet.getString("idPedido"));
					pedido.setDataAbertura(resultSet.getDate("dataAbertura").toLocalDate());
					pedido.setPrivisaoTerminio(resultSet.getDate("previsaoTermino").toLocalDate());
					pedido.setProducaoTotal(resultSet.getDouble("producaoTotal"));
					pedido.setCliente(cliente);
					pedido.setDescricao(resultSet.getString("descricao"));
					pedido.setStatus(resultSet.getString("status"));
					
					listpedido.add(pedido);
					
				} 
					if(listpedido.isEmpty()){
					JOptionPane.showMessageDialog(null, "Pedido não encontrado.");
					 System.out.println("Pedido não encontrado.");  
					
					}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o pedido: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return listpedido;
	}

	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Pedido WHERE idPedido = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Pedido excluído com sucesso.");
				} else {
					System.out.println("Pedido não encontrado para exclusão.");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o pedido: " + e.getMessage());
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
