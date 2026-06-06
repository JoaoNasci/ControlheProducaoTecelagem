package ConexaoBD_DAO;

import java.sql.*;
import EnderecoContatos.*;
import Pessoas.Fornecedor;


public class FornecedorDAO implements DAO {
	public Fornecedor fornecedor;
	public Endereco endereco;
	private BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String mensagem , sql;
	public  byte Operacao = 1;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte CONSULTA = 3;
	public static final byte EXCLUSAO = 4;
	
	public FornecedorDAO() {
		bd = new BD();
	}
 

	@Override
	public void Inclusao(String sql) {     
		
	}
	@Override
	public void Alter(String sql) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Consulta(String sql) {
		sql = "SELECT f.cnpj,"
				+ " f.nome,"
				+ "e.cep,"
				+ "e.rua,"
				+ "e.numero,"
				+ "e.barrio,"
				+ "e.cidade,"
				+ "e.estado,"
				+ "e.pais,"
				+ "t.telefone,"
				
				
				+ " FROM fornecedor WHERE cnpj = ?";
		
		try {
			
			if (!bd.getConectar()) {
				mensagem = "Falha ao conectar ao banco de dados.";
			}else {
				statement = bd.conexao.prepareStatement(sql);
				statement.setString(1, fornecedor.getCnpj());
				resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					fornecedor.setNome(resultSet.getString("nome"));
					fornecedor.setEndereco(null);
					fornecedor.setTelefone(resultSet.getString("telefone"));
					fornecedor.setEmail(resultSet.getString("email"));
					fornecedor.setCnpj(resultSet.getString("cnpj"));
					fornecedor.setInscricaoEstadual(resultSet.getString("inscricao_estadual"));
					fornecedor.setNomeFantasia(resultSet.getString("nome_fantasia"));
					fornecedor.setPorteEmpresa(resultSet.getString("porte_empresa"));
					fornecedor.setDataFundacao(resultSet.getDate("data_fundacao").toLocalDate());
					fornecedor.setStatus(resultSet.getString("status"));
					fornecedor.setCategoriaProduto(resultSet.getString("categoria_produto"));
					fornecedor.setPrazoEntrega(resultSet.getShort("prazo_entrega"));
					
				} else {
					 mensagem = "Fornecedor não encontrado.";
				}
			}
			resultSet.close();
			statement.close();
			bd.fecharConexao();
		} catch (SQLException e) {
			mensagem = "Erro ao consultar o fornecedor: " + e.getMessage();
		}
	}
		
	@Override
	public void Excluir(String sql) {
		// TODO Auto-generated method stub
	}

    
}
