package Tecelagem;

import java.util.List;

import Enumeradores.Qualidade;
import EstoqueMateriaPrima.*;
import Pessoas.Funcionario;

public class Producao {
	private int id;
	private Pedido pedido;
	private List<Funcionario> operador = new java.util.ArrayList<>();
	private List<Maquina> maquina = new java.util.ArrayList<>();
	private double quantidadePlanejada;
	private List<Fio> entrada = new java.util.ArrayList<>();
	private Malha saida;
	private double qntProduzida;
	private Qualidade qualidade;
	
	public Producao() {
		id = 0;
		pedido = null;
		quantidadePlanejada = 0;
		saida = null;
		qntProduzida = 0;
		qualidade = null;
		
	}
	
	public Producao(int id, Pedido pedido,Funcionario funcionario, Maquina equipamento,
			double quantidadePlanejada, Fio fio, double qntProduzida, int qualidade) {
		this.setId(id);
		this.setPedido(pedido);
		this.setOperador(funcionario);
		this.setMaquina(equipamento);
		this.setQuantidadePlanejada(quantidadePlanejada);
		this.setEntrada(fio);
		this.setQntProduzida(qntProduzida);
		this.setQualidade(qualidade);
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public List<Funcionario> getOperador() {
		return operador.toString().isEmpty() ? null : operador;
	}
	
	public void setOperador(Funcionario f) {
		this.operador.add(f);
	}
	
	public List<Maquina> getMaquina() {
		return maquina.toString().isEmpty() ? null : maquina;
	}
	
	public void setMaquina(Maquina m) {
		this.maquina.add(m);
	}
	
	public double getQuantidadePlanejada() {
		return quantidadePlanejada;
	}
	
	public void setQuantidadePlanejada(double quantidadePlanejada) {
		this.quantidadePlanejada = quantidadePlanejada;
	}
	
	public List<Fio> getEntrada() {
		return entrada.toString().isEmpty() ? null : entrada;
	}
	
	public void setEntrada(Fio fio) {
		this.entrada.add(fio);
	}
	
	public Malha getSaida() {
		return saida;
	}
	
	public void setSaida(Malha saida) {
		this.saida = saida;
	}
	
	public double getQntProduzida() {
		return qntProduzida;
	}
	
	public void setQntProduzida(double qntProduzida) {
		this.qntProduzida = qntProduzida;
	}
	
	public String getQualidade() {
		return qualidade.name();
	}
	
	public void setQualidade(int qualidade) {
		if (qualidade == 1) {
			this.qualidade = Qualidade.º1_QUALIDADE;
		} else if (qualidade == 2) {
			this.qualidade = Qualidade.º2_QUALIDADE;
		} else {
			this.qualidade = null;
		}
	}
	
	@Override
	public String toString() {
		String str = "ID: " + this.getId() + "\n";
		str += "Pedido: " + this.getPedido() + "\n";
		str += "Operador: " + this.getOperador() + "\n";
		str += "Maquina: " + this.getMaquina() + "\n";
		str += "Quantidade Planejada: " + this.getQuantidadePlanejada() + "\n";
		str += "Entrada: " + this.getEntrada() + "\n";
		str += "Saida: " + this.getSaida() + "\n";
		str += "Quantidade Produzida: " + this.getQntProduzida() + "\n";
		str += "Qualidade: " + this.getQualidade() + "\n";
		
		return str;
	}
}
