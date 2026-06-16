package TecelagemDAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import ConexaoBD_DAO.*;
import Tecelagem.Producao;
import EstoqueMateriaPrimaDAO.*;
import PessoasDAO.FuncionarioDao;

public class ProducaoDAO implements DAO<Producao>{
	public BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private int idProducao;
	private String  sql ;
	
	public ProducaoDAO() {
		bd = new BD();
	}
	
	public int getIdProducao() {
		return idProducao;
	}

	@Override
	public void Inclusao(Producao incluir) {
		sql = "INSERT INTO Producao (FK_pedido, qntPlanejada, saidaMateriaPrima, qntProduzida, "
				+ "qualidade)"
				 +" VALUES(?, ?, ?, ?, ?) ";
		
			try {
				if(!bd.getConectar()) {
					System.out.println("Falha ao conectar ao banco de dados.");

				}else {
					MalhaDao ma = new MalhaDao();
					statement = bd.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					statement.setString(1, incluir.getPedido().getIdPedido());
					statement.setDouble(2, incluir.getQuantidadePlanejada());
					ma.Inclusao(incluir.getSaidaMateriaPrima());
					statement.setInt(3, ma.getIdMalha());
					statement.setDouble(4, incluir.getQntProduzida());
					statement.setString(5, incluir.getQualidade());
					
					statement.executeUpdate();
					
					var id = statement.getGeneratedKeys();
					this.idProducao = id.getInt(1);
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o producao: " + e.getMessage());
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
	public void Alter(Producao alterar) {
		sql = "INSERT INTO Producao (FK_pedido, qntPlanejada, saidaMateriaPrima, qntProduzida, "
				+ "qualidade)"
				 +" VALUES(?, ?, ?, ?, ?) ";
		
			try {
				if(!bd.getConectar()) {
					System.out.println("Falha ao conectar ao banco de dados.");

				}else {
					MalhaDao ma = new MalhaDao();
					statement = bd.conexao.prepareStatement(sql);
					statement.setString(1, alterar.getPedido().getIdPedido());
					statement.setDouble(2, alterar.getQuantidadePlanejada());
					ma.Alter(alterar.getSaidaMateriaPrima());
					statement.setInt(3, ma.getIdMalha());
					statement.setDouble(4, alterar.getQntProduzida());
					statement.setString(5, alterar.getQualidade());
					
					
					statement.executeUpdate();
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o producao: " + e.getMessage());
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
public Producao Consulta(String consulta) {
 Producao producao = null;
		
		sql  = "select  p.idProducao as Producao, p.FK_pedido as Pedido , p.qntPlanejada as Quantidade_Planejada, "
				+ "p.saidaMateriaPrima as ID_Malha, p.qntProduzida as Quantidade_Produzida, "
				+ " p.qualidade as Qualidade, o.Funcionario_numeroCadastro as Operador, "
				+ " ma.idMaquina as Maquina, f.Fio_idFio as Fio "
				+ "from Producao p right join Pedido ped on p.FK_pedido = ped.idPedido "
				+ "right join Malha m on p.saidaMateriaPrima = m.idMalha "
				+ "inner join entradaMateriaPrima f on p.idProducao = f.Producao_idProducao "
				+ "right join Maquina ma on p.idProducao = ma.FK_Producao "
				+ "right join Operador o on p.idProducao = o.Producao_idProducao "
				+ "where  p.FK_pedido = ? ";
		
		
		try {
			
			if (!bd.getConectar()) {
				
				System.out.println("Falha ao conectar ao banco de dados.");  
				
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, consulta);
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					producao = new Producao();
					PedidoDAO pedido = new PedidoDAO();
					MalhaDao malha = new MalhaDao();
					
					while(resultSet.next()) {
						FioDao fio = new FioDao();
						FuncionarioDao operador = new FuncionarioDao();
						MaquinaDAO maquina = new MaquinaDAO();
						
						producao.setOperador(operador.Consulta(resultSet.getString("Operador")));
						producao.setMaquina(maquina.Consulta(resultSet.getString("Maquina")));
						producao.setEntradaMateriaPrima(fio.Consulta(resultSet.getString("Fio")));
					}
				
					producao.setPedido(pedido.Consulta(resultSet.getString("Pedido")));
					producao.setQuantidadePlanejada(resultSet.getDouble("Quantidade_Planejada"));
					producao.setSaidaMateriaPrima(malha.Consulta(resultSet.getString("ID_Malha")));
					producao.setQntProduzida(resultSet.getDouble("Quantidade_Produzida"));
					producao.setQualidade(resultSet.getShort("Qualidade"));
					
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Produção não encontrado.");
					 System.out.println("Produção não encontrado.");  
					
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o produção: " + e.getMessage());
			
		}finally {
			 try {
				 if(resultSet != null) resultSet.close();
				 if(statement != null) statement.close();
				 bd.fecharConexao();
			 } catch (SQLException e) {
				 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
			 }
		 }
		return producao;
	}

	@Override
	public List<Producao> ConsultaList() {
		 List<Producao> Listaproducao = new ArrayList<Producao>();
			
			sql  = "select  p.idProducao as Producao, p.FK_pedido as Pedido , p.qntPlanejada as Quantidade_Planejada, "
					+ "p.saidaMateriaPrima as ID_Malha, p.qntProduzida as Quantidade_Produzida, "
					+ " p.qualidade as Qualidade, o.Funcionario_numeroCadastro as Operador, "
					+ " ma.idMaquina as Maquina, f.Fio_idFio as Fio "
					+ "from Producao p right join Pedido ped on p.FK_pedido = ped.idPedido "
					+ "right join Malha m on p.saidaMateriaPrima = m.idMalha "
					+ "inner join entradaMateriaPrima f on p.idProducao = f.Producao_idProducao "
					+ "right join Maquina ma on p.idProducao = ma.FK_Producao "
					+ "right join Operador o on p.idProducao = o.Producao_idProducao ";
			
			
			try {
				
				if (!bd.getConectar()) {
					
					System.out.println("Falha ao conectar ao banco de dados.");  
					
				}else {
					statement = bd.conexao.prepareStatement(sql);
					resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						Producao producao = new Producao();
						PedidoDAO pedido = new PedidoDAO();
						MalhaDao malha = new MalhaDao();
						
						while(resultSet.next()) {
							FioDao fio = new FioDao();
							FuncionarioDao operador = new FuncionarioDao();
							MaquinaDAO maquina = new MaquinaDAO();
							
							producao.setOperador(operador.Consulta(resultSet.getString("Operador")));
							producao.setMaquina(maquina.Consulta(resultSet.getString("Maquina")));
							producao.setEntradaMateriaPrima(fio.Consulta(resultSet.getString("Fio")));
						}
					
						producao.setPedido(pedido.Consulta(resultSet.getString("Pedido")));
						producao.setQuantidadePlanejada(resultSet.getDouble("Quantidade_Planejada"));
						producao.setSaidaMateriaPrima(malha.Consulta(resultSet.getString("ID_Malha")));
						producao.setQntProduzida(resultSet.getDouble("Quantidade_Produzida"));
						producao.setQualidade(resultSet.getShort("Qualidade"));
						
						Listaproducao.add(producao);
						
					} 
						if(!Listaproducao.isEmpty()){
						JOptionPane.showMessageDialog(null, "Produção não encontrado.");
						 System.out.println("Produção não encontrado.");  
						
						}
				}
				
			} catch (SQLException e) {
				System.out.println("Erro ao consultar o produção: " + e.getMessage());
				
			}finally {
				 try {
					 if(resultSet != null) resultSet.close();
					 if(statement != null) statement.close();
					 bd.fecharConexao();
				 } catch (SQLException e) {
					 System.out.println("Erro ao fechar a conexão: " + e.getMessage());
				 }
			 }
			return Listaproducao;
	}

	@Override
	public void Excluir(String excluir) {
		sql = "DELETE FROM Producao WHERE idProducao = ? or FK_pedido = ? ";
		try {
			if (!bd.getConectar()) {
				System.out.println("Falha ao conectar ao banco de dados.");  
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, excluir);
				statement.setString(2, excluir);
				int rowsAffected = statement.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Produção excluído com sucesso.");
				} else {
					System.out.println("Produção não encontrado para exclusão.");
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
