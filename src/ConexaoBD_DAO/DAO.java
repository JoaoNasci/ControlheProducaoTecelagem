package ConexaoBD_DAO;

import java.util.List;

public interface DAO<T> {
	public void Inclusao(T sql);
	public void Alter(T sql);
	public T Consulta(String sql);
	public List<T> ConsultaList();
	public void Excluir(String sql);
	
}
