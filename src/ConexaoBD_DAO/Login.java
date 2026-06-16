package ConexaoBD_DAO;

public class Login {
	private String email;
	private String senha;
	
	public Login() {
		email = null;
		senha = null;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean validarLogin(String email, String senha) {
		
		if (this.email.equals(email) && this.senha.equals(senha)) {
			return true; 
		}else {
			return false; 
		}
			
		
		
	}
	

}
