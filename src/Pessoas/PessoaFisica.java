package Pessoas;

import java.time.LocalDate;
import EnderecoContatos.*;
import Enumeradores.Sexo;

public class PessoaFisica extends Pessoa {
	private String cpf;
	private LocalDate dataNascimento;
	private Sexo sexo;
	
	public PessoaFisica() {
		this.cpf = null;
		this.dataNascimento = null;
		this.sexo = null;
		
	}
	
	public PessoaFisica(String nome, Endereco endereco, Telefone telefone, String email, String cpf, LocalDate dataNascimento, String sexo) {
		super(nome, endereco, telefone, email);
		this.setCpf(cpf);
		this.setDataNascimento(dataNascimento);
		this.setSexo(sexo);
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo.name();
	}

	public void setSexo(String sexo) {
		this.sexo = Sexo.valueOf(sexo.toUpperCase().trim());
	}
	
	
@Override
public String toString() {
	String str;
	str = super.toString();
	str += "CPF: " + this.getCpf() + "\n";
	str += "Data de Nascimento: " + this.getDataNascimento() + "\n";
	str += "Sexo: " + this.getSexo()+ "\n";
	return str;
}

}
