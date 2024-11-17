package dao;

import db.ConnectionPool;
import model.Customer;

public interface CustomerFunction<T> {

	// Thêm khách hàng mới vào database (Đăng ký)
	boolean customerRegister(Customer customer);

	// Lấy thông tin khách hàng theo ID
	Customer getCustomerById(int customerID);

	// Kiểm tra đăng nhập
	boolean customerLogin(String email, String pass);

	boolean delCustomer(Customer customer);

	ConnectionPool getCP();

	void releaseConnection();

}
