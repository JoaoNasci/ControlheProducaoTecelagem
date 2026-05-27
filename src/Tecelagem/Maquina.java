package Tecelagem;

import java.time.LocalDateTime;

import Enumeradores.Situacao;

public class Maquina {
	private short id;
	private String modelo;
	private String tipo;
	private Situacao situacao;
	private byte  velocidade;
	private int  quantidadeVoltas;
	private LocalDateTime  tempoExecucao;
	private double eficiencia = 100.0;
	
	
	public Maquina() {
		id = 0;
		modelo = null;
		tipo = null;
		situacao = null;
		velocidade = 0;
		quantidadeVoltas = 0;
		tempoExecucao = null;
		
	}
	
	public Maquina(short id, String modelo, String tipo, String situacao, byte velocidade, int quantidadeVoltas) {
		setId(id);
		setModelo(modelo);
		setTipo(tipo);
		setSituacao(situacao);
		setVelocidade(velocidade);
		setQuantidadeVoltas(quantidadeVoltas);
		setTempoExecucao(LocalDateTime.now());
		
		
	}
	
	public short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getSituacao() {
		return this.situacao.name();
	}
	
	public void setSituacao(String situacao) {
		this.situacao = Situacao.valueOf(situacao.toUpperCase().trim());
	}
	
	public byte getVelocidade() {
		return velocidade;
	}
	
	public void setVelocidade(byte velocidade) {
		this.velocidade = velocidade;
	}
	
	public int getQuantidadeVoltas() {
		return quantidadeVoltas;
	}
	
	public void setQuantidadeVoltas(int quantidadeVoltas) {
		this.quantidadeVoltas = quantidadeVoltas;
	}	
	
	public LocalDateTime getTempoExecucao() {
		return tempoExecucao;
	}
	
	public void setTempoExecucao(LocalDateTime tempo) {
		if (Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.LIGADA) {
			this.tempoExecucao = tempo;
		}
				
	}
	
	public double getEficiencia() {
		return eficiencia;
	}
	
	public void setEficiencia() {
		while ( Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.TROCA_DE_ARTIGO ||
				Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.FALTA_MATÉRIA_PRIMA ||
				Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.SEM_PROGRAMACAO) {
			
			
			if (Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.PARADA) {
				
				this.eficiencia = this.eficiencia * ((this.eficiencia - 1.5) /100);
				
				}else if (Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.LIGADA) {
					if(this.eficiencia < 100.0) {
						this.eficiencia = this.eficiencia * ((this.eficiencia + 1.5) /100);
				 
					}
				
				}else if (Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.MANUTENCAO) {
					this.eficiencia = this.eficiencia * ((this.eficiencia - 0.5) /100);
					
					
				}else if (Situacao.valueOf(this.getSituacao().toUpperCase().trim()) == Situacao.SEM_OPERADOR) {
					this.eficiencia = this.eficiencia * ((this.eficiencia - 5) /100);
					
				}else {
					this.eficiencia = this.eficiencia * ((this.eficiencia - 2) /100);
				}
		
			}
		}
	

@Override
	public String toString() {
		String str;
		str = "ID: " + this.getId() + "\n" +
			  "Modelo: " + this.getModelo() + "\n" +
			  "Tipo: " + this.getTipo() + "\n" +
			  "Situação: " + this.getSituacao() + "\n" +
			  "Velocidade: " + this.getVelocidade() + "\n" +
			  "Quantidade de Voltas: " + this.getQuantidadeVoltas() + "\n" +
			  "Tempo de Execução: " + this.getTempoExecucao() + "\n" +
			  "Eficiência: " + this.getEficiencia() + "%\n";
		
		return str;
	}
	
}
	
	
	
   

