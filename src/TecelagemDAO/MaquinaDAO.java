package TecelagemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConexaoBD_DAO.*;
import Tecelagem.Maquina;

public class MaquinaDAO implements DAO<Maquina> {
	public BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String  sql ;
	
	public MaquinaDAO() {
		bd = new BD();
	}
	
	
	@Override
	public void Inclusao(Maquina incluir) {
		sql = "INSERT INTO Malha (idMaquina, modelo, tipo, situacao, velocidadeRPM, qntVoltas,"
				+ " tempoExecucao, enficiencia)"
				 +" VALUES(?,?,?,?,?,?,?,?) ";
		
			try {
				if(!bd.getConectar()) {
					System.out.println("Falha ao conectar ao banco de dados.");

				}else {
					
					statement = bd.conexao.prepareStatement(sql);
					statement.setShort(1, incluir.getIdMaquina());
					statement.setString(2, incluir.getModelo());
					statement.setString(3, incluir.getTipo());
					statement.setString(4, incluir.getSituacao());
					statement.setByte(5, incluir.getVelocidade());
					statement.setInt(6, incluir.getQuantidadeVoltas());
					statement.setTime(7, Time.valueOf( incluir.getTempoExecucao().toLocalTime()));
					statement.setDouble(8, incluir.getEficiencia());
				
					
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
	public void Alter(Maquina alterar) {
		sql =  "UPDATE Maquina "
				+ "SET modelo = ?, tipo = ?, situacao = ?, velocidadeRPM = ?, qntVoltas = ?, "
				+ "tempoExecucao = ?, enficiencia = ?, FK_Producao = ?"
				+ "WHERE idMaquina = ? ";
		
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, alterar.getModelo());
				statement.setString(2, alterar.getTipo());
				statement.setString(3, alterar.getSituacao());
				statement.setByte(4, alterar.getVelocidade());
				statement.setInt(5, alterar.getQuantidadeVoltas());
				statement.setTime(6, Time.valueOf(alterar.getTempoExecucao().toLocalTime()));
				statement.setDouble(7, alterar.getEficiencia());
				statement.setInt(8, alterar.getFK_Producao());
				statement.setShort(9, alterar.getIdMaquina());
				
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Maquina atualizado com sucesso.");
				} else {
					System.out.println("Maquina não encontrado para atualização.");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao atualizar o maquina: " + e.getMessage());
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
	public Maquina Consulta(String consulta) {
		Maquina maquina = null;
		sql = "SELECT idMaquina, modelo, tipo, situacao, velocidadeRPM, qntVoltas, "
				+ "tempoExecucao, enficiencia, FK_Producao "
				+ " FROM Maquina WHERE  idMaquina = ?";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					maquina = new Maquina();
					
					maquina.setIdMaquina(resultSet.getShort("idMaquina"));;
					maquina.setModelo(resultSet.getString("modelo"));
					maquina.setTipo(resultSet.getString("tipo"));
					maquina.setSituacao(resultSet.getString("situacao"));
					maquina.setVelocidade(resultSet.getByte("velocidadeRPM"));
					maquina.setQuantidadeVoltas(resultSet.getShort("qntVoltas"));
					maquina.setTempoExecucao(resultSet.getTimestamp("tempoExecucao").toLocalDateTime());
					maquina.setEficiencia(resultSet.getDouble("enficiencia"));
					maquina.setFK_Producao(resultSet.getInt("FK_Producao"));
					
				} else {
					JOptionPane.showMessageDialog(null, "Maquina não encontrado.");
					 System.out.println("Maquina não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o maquina: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return maquina;
	}
	
	
	@Override
	public List<Maquina> ConsultaList() {
		List<Maquina> listamaquina = new ArrayList<Maquina>();
		sql = "SELECT idMaquina, modelo, tipo, situacao, velocidadeRPM, qntVoltas, "
				+ "tempoExecucao, enficiencia, FK_Producao "
				+ " FROM Maquina ";
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					Maquina maquina = new Maquina();
					
					maquina.setIdMaquina(resultSet.getShort("idMaquina"));;
					maquina.setModelo(resultSet.getString("modelo"));
					maquina.setTipo(resultSet.getString("tipo"));
					maquina.setSituacao(resultSet.getString("situacao"));
					maquina.setVelocidade(resultSet.getByte("velocidadeRPM"));
					maquina.setQuantidadeVoltas(resultSet.getShort("qntVoltas"));
					maquina.setTempoExecucao(resultSet.getTimestamp("tempoExecucao").toLocalDateTime());
					maquina.setEficiencia(resultSet.getDouble("enficiencia"));
					maquina.setFK_Producao(resultSet.getInt("FK_Producao"));
					
					listamaquina.add(maquina);
				} 
					if(!listamaquina.isEmpty()){
					JOptionPane.showMessageDialog(null, "Maquina não encontrado.");
					 System.out.println("Maquina não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o maquina: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return listamaquina;
	}
	
	
	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Maquina WHERE idMaquina = ?";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Maquina excluído com sucesso.");
				} else {
					System.out.println("Maquina não encontrado para exclusão.");
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o maquina: " + e.getMessage());
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
