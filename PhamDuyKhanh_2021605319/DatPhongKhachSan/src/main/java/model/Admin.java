package model;

public class Admin {
	private int adminID;
	private String name;
	private String email;
	private String pass;

	public Admin() {
	}

	public Admin(int adminID, String name, String email, String pass) {
		super();
		this.adminID = adminID;
		this.name = name;
		this.email = email;
		this.pass = pass;
	}

	
	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + ", name=" + name + ", email=" + email + ", pass=" + pass + "]";
	}

}
