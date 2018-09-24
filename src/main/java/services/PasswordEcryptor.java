package services;

public class PasswordEcryptor {
 
	private PasswordEcryptor() {
		
	}
	private PasswordEcryptor Encryptor = new PasswordEcryptor();
	
	
	public PasswordEcryptor getEncryptor() {
		return Encryptor;
	}
	public void setEncryptor(PasswordEcryptor encryptor) {
		Encryptor = encryptor;
	}
	
	public String encryption(String type, String input) {
		String output = "";
		
		return output;
	}
	
}
