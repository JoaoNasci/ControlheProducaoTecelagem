package Pessoas;

import EnderecoContatos.*;

public abstract class Pessoa {
	private String nome;
	private Endereco endereco;
	private Telefone telefone;
	private String email;
	
	public Pessoa() {
		this.nome = null;
		this.endereco = null;
		this.telefone = null;
		this.email = null;
	}
	
	public Pessoa(String nome, Endereco endereco, Telefone telefone, String email) {
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setTelefone(telefone);
		this.setEmail(email);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
@Override
public String toString() {
	String str;
	str =  "Nome: " + this.getNome() + "\n";
	str += "Endereço: " + endereco.toString() + "\n";
	str += "Telefone: " + telefone.toString() + "\n";
	str += "Email: " + this.getEmail() + "\n";
	
	return str;
}
	

}
