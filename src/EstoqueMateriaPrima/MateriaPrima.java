package EstoqueMateriaPrima;

import Pessoas.Fornecedor;

public abstract class MateriaPrima {
	private String lote;
	private Fornecedor fornecedor;
	private String cor;
	private double peso;
	private String descricao;
	
	public MateriaPrima() {
		lote = null;
		fornecedor = null;
		cor = null;
		peso = 0;
		descricao = null;
		
	}
	
	public MateriaPrima(String lote, Fornecedor fornecedor, String cor, double peso, String descricao) {
		this.setLote(lote);
		this.setFornecedor(fornecedor);
		this.setCor(cor);
		this.setPeso(peso);
		this.setDescricao(descricao);
		
	}
	

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public String getCor() {
		return cor;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		String str = "Lote: " + lote + "\n";
		str = "Fornecedor: " + fornecedor.getNomeFantasia() + "\n";
		str += "Cor: " + cor + "\n";
		str += "Peso: " + peso + "\n";
		str += "Descrição: " + descricao + "\n";
		
		return str;
	}

}
