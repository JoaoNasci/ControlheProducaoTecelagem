package EstoqueMateriaPrima;

import Pessoas.Fornecedor;

public abstract class MateriaPrima {
	private int id;
	private Fornecedor fornecedor;
	private String cor;
	private double peso;
	private String descricao;
	
	public MateriaPrima() {
		id = 0;
		fornecedor = null;
		cor = null;
		peso = 0;
		descricao = null;
		
	}
	
	public MateriaPrima(int id, Fornecedor fornecedor, String cor, double peso, String descricao) {
		this.setId(id);
		this.setFornecedor(fornecedor);
		this.setCor(cor);
		this.setPeso(peso);
		this.setDescricao(descricao);
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
		String str;
		str = "ID: " + id + "\n";
		str += "Fornecedor: " + fornecedor.getNomeFantasia() + "\n";
		str += "Cor: " + cor + "\n";
		str += "Peso: " + peso + "\n";
		str += "Descrição: " + descricao + "\n";
		
		return str;
	}

}
