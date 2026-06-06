package Pessoas;

import java.time.LocalDate;
import EnderecoContatos.*;


public class Cliente extends PessoaJuridica {
	private String razaoSocial;
	private double limiteCredito;
	private String ramoAtividade;
	
	
	public Cliente() {
		this.razaoSocial = null;
		this.limiteCredito = 0.0;
		this.ramoAtividade = null;
		
	}
	
	public Cliente(String nome, Endereco endereco, String telefone, String email, String cnpj,
			String inscricaoEstadual, String nomeFantasia, String porteEmpresa, LocalDate dataFundacao,
			String status, String razaoSocial, double limiteCredito, String ramoAtividade) {
		
		super(nome, endereco, telefone, email, cnpj, inscricaoEstadual, nomeFantasia, porteEmpresa, dataFundacao,
				status);
		this.setRazaoSocial(razaoSocial);
		this.setLimiteCredito(limiteCredito);
		this.setRamoAtividade(ramoAtividade);
		
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
	
	
	@Override
	public String toString() {
		String str;
		str = super.toString();
		str += "Razão Social: " + this.getRazaoSocial() + "\n";
		str += "Limite de Crédito: R$ " + this.getLimiteCredito() + "\n";
		str += "Ramo de Atividade: " + this.getRamoAtividade() + "\n";
		return str;
	}
	
}
