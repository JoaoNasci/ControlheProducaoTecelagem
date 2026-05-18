package Pessoas;

import java.time.LocalDate;

import EnderecoContatos.*;
import Enumeradores.*;

public class Funcionario extends PessoaFisica {
	private String pis;
	private String numeroCadastro;
	private LocalDate dataAdmissao;
	private Cargo cargo;
	private Turno turno;
	
	public Funcionario() {
		this.pis = null;
		this.numeroCadastro = null;
		this.dataAdmissao = null;
		this.cargo = null;
		this.turno = null;
		
	}
	
	public Funcionario(String nome, Endereco endereco, Telefone telefone, String email, String cpf
					,  LocalDate dataNascimento, String sexo,String pis, String numeroCadastro, 
					String dataAdmissao, String cargo, String turno) {
		
		super(nome, endereco, telefone, email, cpf, dataNascimento, sexo);
		this.setPis(pis);
		this.setNumeroCadastro(numeroCadastro);
		this.setDataAdmissao(LocalDate.parse(dataAdmissao));
		this.setCargo(cargo);
		this.setTurno(turno);
		
	}

	public String getPis() {
		return pis;
	}
	
	public void setPis(String pis) {
		this.pis = pis;
	}
	
	public String getNumeroCadastro() {
		return numeroCadastro;
	}
	
	public void setNumeroCadastro(String numeroCadastro) {
		this.numeroCadastro = numeroCadastro;
	}
	
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	public String getCargo() {
		return cargo.name();
	}
	
	public void setCargo(String cargo) {
		this.cargo = Cargo.valueOf(cargo.toUpperCase().trim());
	}
	
	
	public String getTurno() {
		return turno.name();
	}

	public void setTurno(String turno) {
		this.turno = Turno.valueOf("º"+turno.toUpperCase().trim());
	}

	@Override
	public String toString() {
		String str;
		str = super.toString();
		str += "PIS: " + this.getPis() + "\n";
		str += "Número de Cadastro: " + this.getNumeroCadastro() + "\n";
		str += "Data de Admissão: " + this.getDataAdmissao() + "\n";
		str += "Cargo: " + this.getCargo() + "\n";
		str += "Turno: " + this.getTurno() + "\n";
		
		return str;
	}

}
