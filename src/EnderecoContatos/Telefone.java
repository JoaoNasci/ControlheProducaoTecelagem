package EnderecoContatos;

public class Telefone {
	private String ddd;
	private String numero;
	
	public Telefone() {
		this.ddd = null;
		this.numero = null;
	}
	
	public Telefone(String ddd, String numero) {
		this.setDdd(ddd);
		this.setNumero(numero);
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
@Override
public String toString() {
	return "(" + this.getDdd() + ") " + this.getNumero();
}

}
