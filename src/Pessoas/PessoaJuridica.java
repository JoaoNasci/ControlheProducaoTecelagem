package Pessoas;

import java.time.LocalDate;
import EnderecoContatos.*;
import Enumeradores.*;

public abstract class PessoaJuridica extends Pessoa {
	private String cnpj;
	private String inscricaoEstadual;
	private String nomeFantasia;
	private Porte_TipoSociedade porteEmpresa;
	private LocalDate dataFundacao;
	private Status status;
	
	public PessoaJuridica() {
		this.cnpj = null;
		this.inscricaoEstadual = null;
		this.nomeFantasia = null;
		this.porteEmpresa = null;
		this.dataFundacao = null;
		this.status = null;
		
	}
	
	public PessoaJuridica(String nome, Endereco endereco, String telefone, String email, String cnpj,
			String inscricaoEstadual, String nomeFantasia, String porteEmpresa, LocalDate dataFundacao, String status) {
		super(nome, endereco, telefone, email);
		this.setCnpj(cnpj);
		this.setInscricaoEstadual(inscricaoEstadual);
		this.setNomeFantasia(nomeFantasia);
		this.setPorteEmpresa(porteEmpresa);
		this.setDataFundacao(dataFundacao);
		this.setStatus(status);
		
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getPorteEmpresa() {
		return porteEmpresa.name();
	}

	public void setPorteEmpresa(String porteEmpresa) {
		this.porteEmpresa = Porte_TipoSociedade.valueOf(porteEmpresa.toUpperCase().trim());
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getStatus() {
		return status.name();
	}

	public void setStatus(String status) {
		this.status = Status.valueOf(status.toUpperCase().trim());
	}
	
@Override
public String toString() {
	String str;
	str = super.toString();
	str += "CNPJ: " + this.getCnpj() + "\n";
	str += "Inscrição Estadual: " + this.getInscricaoEstadual() + "\n";
	str += "Nome Fantasia: " + this.getNomeFantasia() + "\n";
	str += "Porte da Empresa: " + this.getPorteEmpresa() + "\n";
	str += "Data de Fundação: " + this.getDataFundacao() + "\n";
	str += "Status: " + this.getStatus() + "\n";
	
	return str;
	}
	

}
