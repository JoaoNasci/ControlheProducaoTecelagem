package EnderecoContatos;

public class Endereco {
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	
	
	
	
	public Endereco() {
		
		this.cep = null;
		this.rua = null;
		this.numero = null;
		this.bairro = null;
		this.cidade = null;
		this.estado = null;
		this.complemento = null;
	}
	
	public Endereco(String cep, String rua, String numero, String bairro, String cidade, String estado,
			String complemento) {
	this.setCep(cep);
	this.setRua(rua);
	this.setNumero(numero);
	this.setBairro(bairro);
	this.setCidade(cidade);
	this.setEstado(estado);
	this.setComplemento(complemento);
	
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
	@Override
	public String toString() {
		String str;
		str = "Endereço :\n";
		str +=  rua + ", " + getRua() + " - " + getBairro() + " - " + getCidade() + " - " + getEstado() +", "+ getCep() +"\n";
		str += "Complemento: " + getComplemento();
		
		return str;
	}
	
	
	
	
}
