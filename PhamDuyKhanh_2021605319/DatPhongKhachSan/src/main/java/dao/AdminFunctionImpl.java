package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionPool;
import db.ConnectionPoolImpl;

public class AdminFunctionImpl implements AdminFunction {

	private Connection con;
	private ConnectionPool cp;

	public AdminFunctionImpl(ConnectionPool cp) {
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		try {
			this.con = this.cp.getConnection("Book ");
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException var3) {
			var3.printStackTrace();
		}

	}
	
	@Override
	public boolean login(String username, String password) {
		String sql = "SELECT * FROM Admins WHERE username = ? AND password = ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, username);
			pre.setString(2, password);
			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public ConnectionPool getCP() {
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		try {
			this.cp.releaseConnection(this.con, "Book");
		} catch (SQLException var2) {
			var2.printStackTrace();
		}

	}
}
