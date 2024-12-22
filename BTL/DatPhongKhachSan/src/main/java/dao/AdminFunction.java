package dao;

import db.ConnectionPool;

public interface AdminFunction {

	boolean login(String email, String password);

	ConnectionPool getCP();

	void releaseConnection();

}
