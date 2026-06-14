package EstoqueMateriaPrima;

import java.util.HashMap;
import java.util.Map;

import EnderecoContatos.Endereco;

public class Deposito {
	private String nome;
	private Endereco endereco;
	private Map<String,MateriaPrima> itens = new HashMap<>();
	private String estante;
	private double capacidadeMaxima ;
	private double capacidadeAtual;
	
	public Deposito() {
		nome = null;
		endereco = null;
		estante = null;
		capacidadeAtual = 0;
		
	}
	
	public Deposito(double capacidadeMaxima) {
		definirCapacidadeMaxima(capacidadeMaxima);
	}
	
	public Deposito(String nome, Endereco endereco, MateriaPrima itens, String estente ) {
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setItens(itens);
		this.setEstante(estente);
		
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
	
	public Map<String,MateriaPrima> getItens() {
		return itens;
	}
	
	public void setItens(MateriaPrima materiaPrima) {
		if (this.capacidadeAtual + materiaPrima.getPeso() <= this.capacidadeMaxima) {
			this.capacidadeAtual += materiaPrima.getPeso();
			this.itens.put(getEstante(), materiaPrima);
		} else {
			System.out.println("Capacidade máxima excedida. Não é possível adicionar o item.");

		}
		
	}
	
	public String getEstante() {
		return estante;
	}
	
	public void setEstante(String estente) {
		this.estante = estente;
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
	

	@Override
	public String toString() {
		String str = "Nome: " + this.getNome() + "\n";
		str += "Endereço: " + this.getEndereco() + "\n";
		str += "Capacidade Máxima: " + this.getCapacidadeMaxima() + "\n";
		str += "Capacidade Atual: " + this.getCapacidadeAtual() + "\n";
		str += " Itens no Depósito \n";
			if (this.getItens().isEmpty()) {
				str += "Nenhum item no depósito.\n";
			}else {
				for (Map.Entry<String, MateriaPrima> iten: this.getItens().entrySet()) {
						str += "Estante: " + iten.getKey() + " - " + iten.getValue() + "\n";
				}
			}
		return str;
	}
	
}
