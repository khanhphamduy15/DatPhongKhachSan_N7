package model;

import java.sql.Date;

public class Customer {
	private int customerID;
	private String name;
	private String email;
	private String pass;
	private int phone;
	private String address;
	private Date regDate;

	public Customer() {

	}

	public Customer(int customerID, String name, String email, String pass, int phone, String address, Date regDate) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.phone = phone;
		this.address = address;
		this.regDate = regDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + ", email=" + email + ", pass=" + pass
				+ ", phone=" + phone + ", address=" + address + ", regDate=" + regDate + "]";
	}
}
