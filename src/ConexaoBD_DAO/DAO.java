package ConexaoBD_DAO;


public interface DAO {
	public void Inclusao(String sql);
	public void Alter(String sql);
	public void Consulta(String sql);
	public void Excluir(String sql);
	
}
