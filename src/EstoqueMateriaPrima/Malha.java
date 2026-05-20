package EstoqueMateriaPrima;

import Pessoas.Fornecedor;

public class Malha extends MateriaPrima {
	private double largura;
	private int gramatura;
	private String tipoTrama;
	
	
	public Malha() {
		largura = 0;
		gramatura = 0;
		tipoTrama = null;
		
	}
	
	public Malha(int id, Fornecedor fornecedor, String cor, double peso, String descricao, double largura,
			int gramatura, String tipoTrama) {
		super(id, fornecedor, cor, peso, descricao);
		this.setLargura(largura);
		this.setGramatura(gramatura);
		this.setTipoTrama(tipoTrama);
		
	}
	
	public double getLargura() {
		return largura;
	}
	
	public void setLargura(double largura) {
		this.largura = largura;
	}
	
	public int getGramatura() {
		return gramatura;
	}
	
	public void setGramatura(int gramatura) {
		this.gramatura = gramatura;
	}
	
	public String getTipoTrama() {
		return tipoTrama;
	}
	
	public void setTipoTrama(String tipoTrama) {
		this.tipoTrama = tipoTrama;
	}
	
	@Override
	public String toString() {
		String str = super.toString();
		str += "Largura: " + this.getLargura() + "\n";
		str += "Gramatura: " + this.getGramatura() + "\n";
		str += "Tipo de Trama: " + this.getTipoTrama() + "\n";
		
		return str;
	}
	
	

}
