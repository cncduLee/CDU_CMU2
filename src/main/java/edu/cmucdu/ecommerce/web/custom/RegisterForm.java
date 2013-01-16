package edu.cmucdu.ecommerce.web.custom;

public class RegisterForm {
	private String Username;
	private String Password;
	private String Confirm;
	private String Type;
	private String email;
	private String FullName;
	private String Address;
	private String Telephone;
	private String VerificationCode;
	
	public RegisterForm(){}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getConfirm() {
		return Confirm;
	}
	public void setConfirm(String confirm) {
		Confirm = confirm;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getVerificationCode() {
		return VerificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		VerificationCode = verificationCode;
	}
	
	
}
