package Pessoas;

import java.time.LocalDate;

import EnderecoContatos.*;
import Enumeradores.*;

public class Cliente extends PessoaJuridica {
	
	private String categoriaProduto;
	private short prazoEntrega;
	private FormaPagamento formaPagamento;
	
	public Cliente() {
		this.categoriaProduto = null;
		this.prazoEntrega = 0;
		this.formaPagamento = null;
	}
	
	public Cliente(String nome, Endereco endereco, Telefone telefone, String email, String cnpj,
			String inscricaoEstadual, String nomeFantasia, String porteEmpresa, LocalDate dataFundacao,
			String status, String categoriaProduto, short prazoEntrega, String formaPagamento) {
		
		super(nome, endereco, telefone, email, cnpj, inscricaoEstadual, nomeFantasia, porteEmpresa, dataFundacao,
				status);
		this.setCategoriaProduto(categoriaProduto);
		this.setPrazoEntrega(prazoEntrega);
		this.setFormaPagamento(formaPagamento);
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
	
	public String getFormaPagamento() {
		return formaPagamento.name();
	}
	
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = FormaPagamento.valueOf(formaPagamento.toUpperCase().trim());
	}
	
	@Override
	public String toString() {
		String str;
		str = super.toString();
		str += "Categoria do Produto: " + this.getCategoriaProduto() + "\n";
		str += "Prazo de Entrega: " + this.getPrazoEntrega() + " dias\n";
		str += "Forma de Pagamento: " + this.getFormaPagamento() + "\n";
		return str;
	}
	
	
}
