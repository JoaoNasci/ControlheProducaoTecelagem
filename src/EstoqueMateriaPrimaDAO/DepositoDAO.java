package EstoqueMateriaPrimaDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import ConexaoBD_DAO.*;
import EnderecoContatos.Endereco;
import EstoqueMateriaPrima.*;

import PessoasDAO.FornecedorDAO;

public class DepositoDAO implements DAO<Deposito> {
	public BD bd ;
	private PreparedStatement statement, statementEndereco, statementFio, statementMalha;
	private ResultSet resultSet;
	private String  sql ;
	
	public DepositoDAO() {
		bd = new BD();
	}
	
	private void IncluirMaterialNoDeposito(int depositoId, String estante, Deposito materiaPrima) {
	    String sqlMaFi = "";
	   
	    
	    if (materiaPrima.getItens() instanceof Fio) {
	    	sql = "INSERT INTO Fio_no_Deposito (Fio_idFio, Deposito_idDeposito, estante_Fio) VALUES (?, ?, ?)";
	        sqlMaFi = "select idMalha, loteFio, FK_fornecedor, cor, peso, descricao, largura, gramatura, tipoTrama  from Malha where loteMalha = ?";
	       
	    } else if (materiaPrima.getItens() instanceof Malha) {
	    	sql = "INSERT INTO Malha_no_Deposito ( Malha_idMalha, Deposito_idDeposito, estante_Malha) VALUES (?, ?, ?)";
	        sqlMaFi = "select idMalha, loteMalha, FK_fornecedor, cor, peso, descricao, largura, gramatura, tipoTrama  from Malha where loteMalha = ?";
	        
	    } else {
	        System.out.println("Tipo de matéria-prima não suportado.");
	        
	    }
	    
	    try {
	        if (!bd.getConectar()) {
	            System.out.println("Falha ao conectar ao banco de dados.");
	           
	        }
	        
	        statement = bd.conexao.prepareStatement(sql);
	        statement.setInt(2, depositoId);
	        resultSet = statement.executeQuery();
	        
	         while(resultSet.next()) {
	        	 
	            if (materiaPrima.getItens() instanceof Fio) {
	                statementFio = bd.conexao.prepareStatement(sqlMaFi, Statement.RETURN_GENERATED_KEYS);
	
	            	Fio fio = (Fio) materiaPrima.getItens();
	            	statementFio.setString(1,fio.getFornecedor().getCnpj());
	            	statementFio.setString(2, fio.getCor());
	            	statementFio.setDouble(3, fio.getPeso());
	            	statementFio.setString(4, fio.getDescricao());
	            	statementFio.setString(5, fio.getTitulo());
	            	statementFio.setString(6, fio.getComposicao());
	            	
	            	int FioId = 0;
					var generatedKeys = statementFio.getGeneratedKeys();
					if (generatedKeys.next()) {
						FioId = generatedKeys.getInt(1);
					} else {
						System.out.println("Falha ao obter o ID do Fio.");
						
					}
					statement.setInt(2, FioId);
	                statement.setString(3, estante);
	                
	            } else if (materiaPrima.getItens() instanceof Malha) {
	            	statementMalha = bd.conexao.prepareStatement(sqlMaFi, Statement.RETURN_GENERATED_KEYS);
	            	
	            	Malha malha = (Malha) materiaPrima.getItens();
	            	statementMalha.setString(1,malha.getFornecedor().getCnpj());
	            	statementMalha.setString(2, malha.getCor());
	            	statementMalha.setDouble(3, malha.getPeso());
	            	statementMalha.setString(4, malha.getDescricao());
	            	statementMalha.setDouble(5, malha.getLargura());
	            	statementMalha.setInt(6, malha.getGramatura());
	            	statementMalha.setString(7, malha.getTipoTrama());
	            	
	            	
	            	int MalhaId = 0;
					var generatedKeys = statementFio.getGeneratedKeys();
					if (generatedKeys.next()) {
						MalhaId = generatedKeys.getInt(1);
					} else {
						System.out.println("Falha ao obter o ID do Fio.");
						
					}
					statement.setInt(2, MalhaId);
	                statement.setString(3, estante);
	            }
	            
	            int rowsAffected = statement.executeUpdate();
	            
	            if (rowsAffected > 0) {
	                System.out.println("Matéria-prima inserida no depósito com sucesso.");
	            } else {
	                System.out.println("Falha ao inserir a matéria-prima no depósito.");
	            }
	    	}
	            
	        
	    } catch (SQLException e) {
	        System.out.println("Erro ao inserir a matéria-prima no depósito: " + e.getMessage());
	    } catch (Exception e) {
	    	System.out.println("Erro inesperado: " + e.getMessage());
	        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
	    }
	    finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (statement != null) statement.close();
	            if (statementMalha != null) statementMalha.close();
	            if (statementFio != null) statementFio.close();
	            bd.fecharConexao();
	        } catch (SQLException e) {
	            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
	        }
	    }
	}
	
	@Override
	public void Inclusao(Deposito deposito) {
		
		sql = "Select idDeposito From Deposito Where nome = ?";
		
		String sqlEndereco = "INSERT INTO Endereco (cep, rua, numero, bairro, cidade, estado, pais)"
						   + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, deposito.getNome());
				resultSet = statement.executeQuery();
				int idDeposito = resultSet.getInt("idDeposito");
						
				if(resultSet.next()) {
					System.out.println("Já existe um depósito com esse nome.");
					//IncluirMaterialNoDeposito(idDeposito,deposito.getEstante(), deposito);
				} 
				else {
				 sql = "INSERT INTO Deposito (nome, FK_endereco, capacidadeMaxima, capacidadeAtual) VALUES (?, ?, ?, ?)";
				
				statementEndereco = bd.conexao.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);
				statementEndereco.setString(1, deposito.getEndereco().getCep());
				statementEndereco.setString(2, deposito.getEndereco().getRua());
				statementEndereco.setString(3, deposito.getEndereco().getNumero());
				statementEndereco.setString(4, deposito.getEndereco().getBairro());
				statementEndereco.setString(5, deposito.getEndereco().getCidade());
				statementEndereco.setString(6, deposito.getEndereco().getEstado());
				statementEndereco.setString(7, deposito.getEndereco().getPais());
				
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
				statement.setString(1, deposito.getNome());
				statement.setInt(2, enderecoId);
				statement.setDouble(3, deposito.getCapacidadeMaxima());
				statement.setDouble(4, deposito.getCapacidadeAtual());
				
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Deposito inserido com sucesso.");
				} else {
					System.out.println("Falha ao inserir o deposito.");
				}
				}else {
					System.out.println("Falha ao inserir o endereço do deposito.");
				}
				}
			}
		
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o funcionario: " + e.getMessage());
		} catch (Exception e) {
			e.getMessage();
			 System.out.println("Erro inesperado: " + e.getMessage());
			 JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
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
	public void Alter(Deposito deposito) {
		sql = "UPDATE Deposito d "
				+ "INNER JOIN Endereco e ON f.FK_endereco = e.idEndereco "
				+ "SET d.nome = ?, e.cep = ?, e.rua = ?, e.numero = ?, e.bairro = ?,"
				+ " e.cidade = ?, e.estado = ?, e.pais = ?, e.complemento = ?,"
				+ " d.capacidadeMaxima = ?, d.capasidadeAtual = ?"
				+ "WHERE d.nome = ?";
		
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(12, deposito.getNome());
				statement.setString(1, deposito.getNome());
				statement.setString(2, deposito.getEndereco().getCep());
				statement.setString(3, deposito.getEndereco().getRua());
				statement.setString(4, deposito.getEndereco().getNumero());
				statement.setString(5, deposito.getEndereco().getBairro());
				statement.setString(6, deposito.getEndereco().getCidade());
				statement.setString(7, deposito.getEndereco().getEstado());
				statement.setString(8, deposito.getEndereco().getPais());
				statement.setString(9, deposito.getEndereco().getComplemento());
				statement.setDouble(10, deposito.getCapacidadeMaxima());
				statement.setDouble(11, deposito.getCapacidadeAtual());
		
				
			}
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o funcionario: " + e.getMessage());
			} catch (Exception e) {
				 System.out.println("Erro inesperado: " + e.getMessage());
				 JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
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
	public Deposito Consulta(String consulta) {
		Deposito deposito = null;
		Fio fio = null;
		Malha  malha = null;
		
		sql = "SELECT " 
				+" d.nome, d.capacidadeMaxima,"
				+" e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado, e.pais, e.complemento,"
				+" f.estante_Fio,"
				+" fi.loteFio, fi.FK_fornecedor, fi.cor, fi.peso, "
				+" fi.descricao, fi.titulo, fi.composicao,"
				+" NULL AS tipoTrama,'Fio' AS tipoItem "
				+"FROM Fio_no_Deposito f "
				+"INNER JOIN Deposito d ON f.Deposito_idDeposito = d.idDeposito "
				+"INNER JOIN Fio fi ON f.Fio_idFio = fi.idFio "
				+"INNER JOIN Endereco e ON d.FK_endereco = e.idEndereco "
				+ "WHERE d.nome = ? "
				+"UNION ALL "
				+"SELECT"
				+" d.nome, d.capacidadeMaxima,"
				+" e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado, e.pais, e.complemento,"
				+" m.estante_Malha,"
				+" ma.loteMalha, ma.FK_fornecedor, ma.cor,"
				+" ma.peso, ma.descricao, ma.largura, ma.gramatura, ma.tipoTrama,"
				+" 'Malha' AS tipoItem "
				+"FROM Malha_no_Deposito m "
				+"INNER JOIN Deposito d ON m.Deposito_idDeposito = d.idDeposito "
				+"INNER JOIN Malha ma ON m.Malha_idMalha = ma.idMalha "
				+"INNER JOIN Endereco e ON d.FK_endereco = e.idEndereco "
				+"WHERE d.nome = ? ";
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				statement.setString(2, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					deposito = new Deposito();
					fio = new Fio();
	
					
					Endereco endereco = new Endereco();
					FornecedorDAO fornecedorDAO = new FornecedorDAO();
					
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					
					deposito.setNome(resultSet.getString("nome"));
					deposito.setEndereco(endereco);
					deposito.setEstante(resultSet.getString("estante_Fio"));
					deposito.definirCapacidadeMaxima(resultSet.getDouble("capacidadeMaxima"));
					
					
					String tipoItem = resultSet.getString("tipoItem");
					
					if ("Fio".equals(tipoItem)) {
						fio = new Fio();
						fio.setLote(resultSet.getString("loteFio"));
						fio.setFornecedor(fornecedorDAO.Consulta(resultSet.getString("FK_fornecedor")));
						fio.setCor(resultSet.getString("cor"));
						fio.setPeso(resultSet.getDouble("peso"));
						fio.setDescricao(resultSet.getString("descricao"));
						fio.setTitulo(resultSet.getString("titulo"));	
						fio.setComposicao(resultSet.getString("composicao"));
						
						deposito.setItens(fio);
						
					} else if ("Malha".equals(tipoItem)) {
						malha = new Malha();
						malha.setLote(resultSet.getString("loteMalha"));
						malha.setFornecedor(fornecedorDAO.Consulta(resultSet.getString("FK_fornecedor")));
						malha.setCor(resultSet.getString("cor"));
						malha.setPeso(resultSet.getDouble("peso"));
						malha.setDescricao(resultSet.getString("descricao"));
						malha.setLargura(resultSet.getDouble("largura"));
						malha.setGramatura(resultSet.getInt("gramatura"));
						malha.setTipoTrama(resultSet.getString("tipoTrama"));
						
						deposito.setItens(malha);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao consultar .");
					 System.out.println("Erro ao consultar.");
					}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar: " + e.getMessage());
			
		} catch (Exception e) {
			 System.out.println("Erro inesperado: " + e.getMessage());
			 JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
		}
		finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return deposito;
		
	}

	@Override
	public List<Deposito> ConsultaList() {
		Map<String,Deposito> depositolist = new HashMap<>();
		Deposito deposito = null;
		Fio fio = null;
		Malha  malha = null;
		
		sql = "SELECT " 
				+" d.nome, d.capacidadeMaxima,"
				+" e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado, e.pais, e.complemento,"
				+" f.estante_Fio,"
				+" fi.loteFio, fi.FK_fornecedor, fi.cor, fi.peso, "
				+" fi.descricao, fi.titulo, fi.composicao,"
				+" NULL AS tipoTrama,'Fio' AS tipoItem "
				+"FROM Fio_no_Deposito f "
				+"INNER JOIN Deposito d ON f.Deposito_idDeposito = d.idDeposito"
				+"INNER JOIN Fio fi ON f.Fio_idFio = fi.idFio "
				+"INNER JOIN Endereco e ON d.FK_endereco = e.idEndereco "
				+"UNION ALL "
				+"SELECT"
				+" d.nome, d.capacidadeMaxima,"
				+" e.cep, e.rua, e.numero, e.bairro, e.cidade, e.estado, e.pais, e.complemento,"
				+" m.estante_Malha, ma.FK_fornecedor, ma.loteMalha, ma.cor,"
				+" ma.peso, ma.descricao, ma.largura, ma.gramatura, ma.tipoTrama, 'Malha' AS tipoItem "
				+"FROM Malha_no_Deposito m "
				+"INNER JOIN Deposito d ON m.Deposito_idDeposito = d.idDeposito "
				+"INNER JOIN Malha ma ON m.Malha_idMalha = ma.idMalha "
				+"INNER JOIN Endereco e ON d.FK_endereco = e.idEndereco";
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					deposito = new Deposito();
					fio = new Fio();
	
					
					Endereco endereco = new Endereco();
					FornecedorDAO fornecedorDAO = new FornecedorDAO();
					
					endereco.setCep(resultSet.getString("cep"));
					endereco.setRua(resultSet.getString("rua"));
					endereco.setNumero(resultSet.getString("numero"));
					endereco.setBairro(resultSet.getString("bairro"));
					endereco.setCidade(resultSet.getString("cidade"));
					endereco.setEstado(resultSet.getString("estado"));
					endereco.setPais(resultSet.getString("pais"));
					endereco.setComplemento(resultSet.getString("complemento"));
					
					deposito.setNome(resultSet.getString("nome"));
					deposito.setEndereco(endereco);
					deposito.setEstante(resultSet.getString("estante_Fio"));
					deposito.definirCapacidadeMaxima(resultSet.getDouble("capacidadeMaxima"));
					
					
					
					String tipoItem = resultSet.getString("tipoItem");
					
					if ("Fio".equals(tipoItem)) {
						fio = new Fio();
						fio.setLote(resultSet.getString("loteFio"));
						fio.setFornecedor(fornecedorDAO.Consulta(resultSet.getString("FK_fornecedor")));
						fio.setCor(resultSet.getString("cor"));
						fio.setPeso(resultSet.getDouble("peso"));
						fio.setDescricao(resultSet.getString("descricao"));
						fio.setTitulo(resultSet.getString("titulo"));	
						fio.setComposicao(resultSet.getString("composicao"));
						
						deposito.setItens(fio);
						
					} else if ("Malha".equals(tipoItem)) {
						malha = new Malha();
						malha.setLote(resultSet.getString("loteMalha"));
						malha.setFornecedor(fornecedorDAO.Consulta(resultSet.getString("FK_fornecedor")));
						malha.setCor(resultSet.getString("cor"));
						malha.setPeso(resultSet.getDouble("peso"));
						malha.setDescricao(resultSet.getString("descricao"));
						malha.setLargura(resultSet.getDouble("largura"));
						malha.setGramatura(resultSet.getInt("gramatura"));
						malha.setTipoTrama(resultSet.getString("tipoTrama"));
						
						deposito.setItens(malha);
					}
					
					depositolist.put(deposito.getNome(), deposito);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar: " + e.getMessage());
			
		} catch (Exception e) {
			 System.out.println("Erro inesperado: " + e.getMessage());
			 JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
		}
		finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return new ArrayList<>(depositolist.values());
		
	}

	@Override
	public void Excluir(String exclusao) {
		sql = "DELETE FROM Deposito WHERE nome = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, exclusao);
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Deposito excluído com sucesso.");
				} else {
					System.out.println("Nenhum deposito encontrado com esse nome.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o deposito: " + e.getMessage());
		} catch (Exception e) {
			 System.out.println("Erro inesperado: " + e.getMessage());
			 JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
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

}
