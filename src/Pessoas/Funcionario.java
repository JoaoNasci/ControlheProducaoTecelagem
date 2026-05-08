package Pessoas;

import java.time.LocalDate;

import EnderecoContatos.*;
import Enumeradores.*;

public class Funcionario extends PessoaFisica {
	private String pis;
	private String numeroCadastro;
	private LocalDate dataAdmissao;
	private Cargo cargo;
	private double salario;
	private Turno turno;
	
	public Funcionario() {
		this.cargo = null;
		this.salario = 0.0;
	}
	
	public Funcionario(String nome, Endereco endereco, Telefone telefone, String email, String cpf
					,  LocalDate dataNascimento, String sexo,String pis, String numeroCadastro, String dataAdmissao, String cargo, double salario, String turno) {
		
		super(nome, endereco, telefone, email, cpf, dataNascimento, sexo);
		this.setPis(pis);
		this.setNumeroCadastro(numeroCadastro);
		this.setDataAdmissao(LocalDate.parse(dataAdmissao));
		this.setCargo(cargo);
		this.setSalario(salario);
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
	
	public double getSalario() {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
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
		str += "Salário: R$" + this.getSalario() + "\n";
		str += "Turno: " + this.getTurno() + "\n";
		
		return str;
	}

}
