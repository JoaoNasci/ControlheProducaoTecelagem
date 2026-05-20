package EstoqueMateriaPrima;

import Pessoas.Fornecedor;

public class Fio extends MateriaPrima {
	private String titulo;
	private String composicao;
	
	public Fio() {
		titulo = null;
		composicao = null;
		
	}
	
	public Fio(int id, Fornecedor fornecedor, String cor, double peso, String descricao, String titulo,
			String composicao) {
		super(id, fornecedor, cor, peso, descricao);
		this.setTitulo(titulo);
		this.setComposicao(composicao);
		
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getComposicao() {
		return composicao;
	}
	
	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}
	
	@Override
	public String toString() {
		String str = super.toString();
		str += "Titulo: " + this.getTitulo() + "\n";
		str += "Composição: " + this.getComposicao() + "\n";
		
		return str;
	}
}
