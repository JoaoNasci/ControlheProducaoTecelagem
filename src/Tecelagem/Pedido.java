package Tecelagem;

import java.time.LocalDate;

import Enumeradores.StatusProducao;
import Pessoas.Cliente;

public class Pedido {
	private int id;
	private LocalDate dataAbertura;
	private LocalDate privisaoTerminio;
	private double producaoTotal;
	private Cliente cliente;
	private String descricao;
	private StatusProducao status;
	
	
	public Pedido() {
		id = 0;
		dataAbertura = null;
		privisaoTerminio = null;
		producaoTotal = 0;
		cliente = null;
		descricao = null;
		status = null;
		
	}
	
	public Pedido(int id, LocalDate dataAbertura, LocalDate privisaoTerminio, double producaoTotal, Cliente cliente,
			String descricao, String status) {
		this.setId(id);
		this.setDataAbertura(dataAbertura);
		this.setPrivisaoTerminio(privisaoTerminio);
		this.setProducaoTotal(producaoTotal);
		this.setCliente(cliente);
		this.setDescricao(descricao);
		this.setStatus(status);
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		str = 	"ID: " + id + 
				"\nData de Abertura: " + dataAbertura + 
				"\nPrevisão de Término: " + privisaoTerminio + 
				"\nProdução Total: " + producaoTotal + 
				"\nCliente: " + cliente.getNomeFantasia() + 
				"\nDescrição: "+ descricao + 
				"\nStatus: " + status.name();
		return str;
	}
	
	
	
}
