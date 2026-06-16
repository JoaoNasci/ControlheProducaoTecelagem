package Tecelagem;

import java.time.LocalDate;

import Enumeradores.StatusProducao;
import Pessoas.Cliente;

public class Pedido {
	private String idPedido;
	private LocalDate dataAbertura;
	private LocalDate privisaoTerminio;
	private double producaoTotal;
	private Cliente cliente;
	private String descricao;
	private StatusProducao status;
	
	
	public Pedido() {
		idPedido = null;
		dataAbertura = null;
		privisaoTerminio = null;
		producaoTotal = 0;
		cliente = null;
		descricao = null;
		status = null;
		
	}
	
	public Pedido(String id, LocalDate dataAbertura, LocalDate privisaoTerminio, double producaoTotal, Cliente cliente,
			String descricao, String status) {
		this.setIdPedido(id);
		this.setDataAbertura(dataAbertura);
		this.setPrivisaoTerminio(privisaoTerminio);
		this.setProducaoTotal(producaoTotal);
		this.setCliente(cliente);
		this.setDescricao(descricao);
		this.setStatus(status);
	}
	
	
	
	public String getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(String id) {
		this.idPedido = id;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public LocalDate getPrivisaoTerminio() {
		return privisaoTerminio;
	}
	public void setPrivisaoTerminio(LocalDate privisaoTerminio) {
		this.privisaoTerminio = privisaoTerminio;
	}
	public double getProducaoTotal() {
		return producaoTotal;
	}
	public void setProducaoTotal(double producaoTotal) {
		this.producaoTotal = producaoTotal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status.name();
	}
	public void setStatus(String status) {
		this.status = StatusProducao.valueOf(status.toUpperCase().trim());
	}
	
	@Override
	public String toString() {
		String str;
		str = 	"ID do Pedido: " + getIdPedido() + 
				"\nData de Abertura: " + getDataAbertura() + 
				"\nPrevisão de Término: " + getPrivisaoTerminio() + 
				"\nProdução Total: " + getProducaoTotal() + 
				"\nCliente: " + cliente.getNomeFantasia() + 
				"\nDescrição: "+ getDescricao() + 
				"\nStatus: " + getStatus();
		return str;
	}
	
	
	
}
