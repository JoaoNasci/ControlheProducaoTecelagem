package TecelagemDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import ConexaoBD_DAO.*;
import Tecelagem.Maquina;

public class MaqinaDAO implements DAO<Maquina> {
	public BD bd ;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String  sql ;
	
	public MaqinaDAO() {
		bd = new BD();
	}
	
	
	@Override
	public void Inclusao(Maquina sql) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Alter(Maquina sql) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Maquina Consulta(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Maquina> ConsultaList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void Excluir(String sql) {
		// TODO Auto-generated method stub
		
	}
	
	
}
