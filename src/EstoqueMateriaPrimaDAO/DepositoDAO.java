package EstoqueMateriaPrimaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ConexaoBD_DAO.*;
import EstoqueMateriaPrima.Deposito;

public class DepositoDAO implements DAO<Deposito> {
	public BD bd ;
	private PreparedStatement statement, statementEndereco;
	private ResultSet resultSet;
	private String  sql;
	
	@Override
	public void Inclusao(Deposito deposito) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Alter(Deposito deposito) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Deposito Consulta(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.util.List<Deposito> ConsultaList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Excluir(String sql) {
		// TODO Auto-generated method stub
		
	}

}
