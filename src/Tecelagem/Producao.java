package Tecelagem;

import java.util.ArrayList;
import java.util.List;

import Enumeradores.Qualidade;
import EstoqueMateriaPrima.*;
import Pessoas.Funcionario;

public class Producao {
	private Pedido pedido;
	private List<Funcionario> operador = new ArrayList<>();
	private List<Maquina> maquina = new ArrayList<>();
	private double quantidadePlanejada;
	private List<Fio> entradaMateriaPrima = new ArrayList<>();
	private Malha saidaMateriaPrima;
	private double qntProduzida;
	private Qualidade qualidade;
	
	public Producao() {
	
		pedido = null;
		quantidadePlanejada = 0;
		saidaMateriaPrima = null;
		qntProduzida = 0;
		qualidade = null;
		
	}
	
	public Producao(Pedido pedido,Funcionario funcionario, Maquina equipamento,
			double quantidadePlanejada, Fio fio, double qntProduzida, byte qualidade) {
		
		this.setPedido(pedido);
		this.setOperador(funcionario);
		this.setMaquina(equipamento);
		this.setQuantidadePlanejada(quantidadePlanejada);
		this.setEntradaMateriaPrima(fio);
		this.setQntProduzida(qntProduzida);
		this.setQualidade(qualidade);
		
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
	
	public List<Fio> getEntradaMateriaPrima() {
		
		return entradaMateriaPrima != null ? entradaMateriaPrima : new ArrayList<>();
	}
	
	public void setEntradaMateriaPrima(Fio fio) {
		this.entradaMateriaPrima.add(fio);
	}
	
	public Malha getSaidaMateriaPrima() {
		return saidaMateriaPrima;
	}
	
	public void setSaidaMateriaPrima(Malha saida) {
		this.saidaMateriaPrima = saida;
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
	
	public void setQualidade(short qualidade) {
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
		String str = "Pedido: " + this.getPedido() + "\n";
		str += "Operador: " + this.getOperador() + "\n";
		str += "Maquina: " + this.getMaquina() + "\n";
		str += "Quantidade Planejada: " + this.getQuantidadePlanejada() + "\n";
		str += "Entrada: " + this.getEntradaMateriaPrima() + "\n";
		str += "Saida: " + this.getSaidaMateriaPrima() + "\n";
		str += "Quantidade Produzida: " + this.getQntProduzida() + "\n";
		str += "Qualidade: " + this.getQualidade() + "\n";
		
		return str;
	}
}
