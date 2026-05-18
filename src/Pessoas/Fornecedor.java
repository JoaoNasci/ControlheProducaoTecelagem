package Pessoas;

import java.time.LocalDate;

import EnderecoContatos.*;
import Enumeradores.*;

public class Fornecedor extends PessoaJuridica {
	
	private String categoriaProduto;
	private short prazoEntrega;
	
	public Fornecedor() {
		this.categoriaProduto = null;
		this.prazoEntrega = 0;
	}
	
	public Fornecedor(String nome, Endereco endereco, Telefone telefone, String email, String cnpj,
			String inscricaoEstadual, String nomeFantasia, String porteEmpresa, LocalDate dataFundacao,
			String status, String categoriaProduto, short prazoEntrega) {
		
		super(nome, endereco, telefone, email, cnpj, inscricaoEstadual, nomeFantasia, porteEmpresa, dataFundacao,
				status);
		this.setCategoriaProduto(categoriaProduto);
		this.setPrazoEntrega(prazoEntrega);
	}
	
	public String getCategoriaProduto() {
		return categoriaProduto;
	}
	
	public void setCategoriaProduto(String categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}
	
	public short getPrazoEntrega() {
		return prazoEntrega;
	}
	
	public void setPrazoEntrega(short prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	
	
	@Override
	public String toString() {
		String str;
		str = super.toString();
		str += "Categoria do Produto: " + this.getCategoriaProduto() + "\n";
		str += "Prazo de Entrega: " + this.getPrazoEntrega() + " dias\n";
		return str;
	}
	
	
}
