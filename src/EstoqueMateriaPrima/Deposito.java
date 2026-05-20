package EstoqueMateriaPrima;

import java.util.List;

import EnderecoContatos.Endereco;

public class Deposito {
	private String nome;
	private Endereco endereco;
	private List<MateriaPrima> itens;
	private String estente;
	private double capacidadeMaxima ;
	private double capacidadeAtual;
	
	public Deposito() {
		nome = null;
		endereco = null;
		itens = null;
		estente = null;
		capacidadeAtual = 0;
		
	}
	
	public Deposito(String nome, Endereco endereco, MateriaPrima itens, String estente ) {
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setItens(itens);
		this.setEstente(estente);
		this.Ocupacidade(itens.getPeso());
		
		
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
	
	public List<MateriaPrima> getItens() {
		return itens;
	}
	
	public void setItens(MateriaPrima itens) {
		this.itens.add(itens);
	}
	
	public String getEstente() {
		return estente;
	}
	
	public void setEstente(String estente) {
		this.estente = estente;
	}
	
	public void definirCapacidadeMaxima(double capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}
	
	public double getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
	
	
	public double getCapacidadeAtual() {
		return capacidadeAtual;
	}
	
	public void Ocupacidade(double ocupacidade) {
		if (this.capacidadeAtual + ocupacidade <= this.capacidadeMaxima) {
			this.capacidadeAtual += ocupacidade;
		} else {
			System.out.println("Capacidade máxima excedida. Não é possível adicionar o item.");
		}
	}
	
	@Override
	public String toString() {
		String str = "Nome: " + this.getNome() + "\n";
		str += "Endereço: " + this.getEndereco() + "\n";
		str += "Itens: " + this.getItens() + "\n";
		str += "Estante: " + this.getEstente() + "\n";
		str += "Capacidade Máxima: " + this.getCapacidadeMaxima() + "\n";
		str += "Capacidade Atual: " + this.getCapacidadeAtual() + "\n";
		
		return str;
	}
	
}
