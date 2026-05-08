package Pessoas;

import java.time.LocalDate;
import EnderecoContatos.*;
import Enumeradores.FormaPagamento;

public class Fornecedor extends PessoaJuridica {
	private String razaoSocial;
	private double limiteCredito;
	private String ramoAtividade;
	private FormaPagamento formaPagamento;
	
	public Fornecedor() {
		this.razaoSocial = null;
		this.limiteCredito = 0.0;
		this.ramoAtividade = null;
		this.formaPagamento = null;
	}
	
	public Fornecedor(String nome, Endereco endereco, Telefone telefone, String email, String cnpj,
			String inscricaoEstadual, String nomeFantasia, String porteEmpresa, LocalDate dataFundacao,
			String status, String razaoSocial, double limiteCredito, String ramoAtividade, String formaPagamento) {
		
		super(nome, endereco, telefone, email, cnpj, inscricaoEstadual, nomeFantasia, porteEmpresa, dataFundacao,
				status);
		this.setRazaoSocial(razaoSocial);
		this.setLimiteCredito(limiteCredito);
		this.setRamoAtividade(ramoAtividade);
		this.setFormaPagamento(formaPagamento);
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public double getLimiteCredito() {
		return limiteCredito;
	}
	
	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	
	public String getRamoAtividade() {
		return ramoAtividade;
	}
	
	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
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
		str += "Razão Social: " + this.getRazaoSocial() + "\n";
		str += "Limite de Crédito: R$ " + this.getLimiteCredito() + "\n";
		str += "Ramo de Atividade: " + this.getRamoAtividade() + "\n";
		str += "Forma de Pagamento: " + this.getFormaPagamento() + "\n";
		return str;
	}
	
}
