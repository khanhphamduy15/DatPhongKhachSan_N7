 package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Customer;

public class CustomerFunctionImpl implements CustomerFunction<Customer> {
	private Connection con;
	private ConnectionPool cp;

	public CustomerFunctionImpl(ConnectionPool cp) {
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		try {
			this.con = this.cp.getConnection("Customer");
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException var3) {
			var3.printStackTrace();
		}

	}

	private boolean exe(PreparedStatement pre) {
		// pre - đã được biên dịch và truyền giá trị đầy đủ cho các tham số
		if (pre != null) {
			try {
				int results = pre.executeUpdate();
				if (results == 0) {
					this.con.rollback();
					return false;
				}

				// xác nhận thực thi sau cùng
				this.con.commit();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();

				try {
					this.con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				pre = null;
			}
		}
		return false;
	}

	@Override
	public boolean customerRegister(Customer customer) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO KhachHang (");
		sql.append("HoTen, Email, MatKhau, SoDienThoai, DiaChi) ");
		sql.append("VALUES ( ?, ?, ?, ?, ?);");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, customer.getName());
			pre.setString(2, customer.getEmail());
			pre.setString(3, customer.getPass());
			pre.setInt(4, customer.getPhone());
			pre.setString(5, customer.getAddress());

			return this.exe(pre);

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Customer getCustomerById(int customerID) {
		String sql = "SELECT * FROM khachhang ";
		sql += "WHERE KhachHangID = ?";

		Customer item = new Customer();
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, customerID);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				item.setCustomerID(rs.getInt("customerID"));
				item.setName(rs.getString("customer_name"));
				item.setAddress(rs.getString("customer_address"));
				item.setPhone(rs.getInt("customer_phone"));
				item.setEmail(rs.getString("customer_email"));
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return item;
	}

	@Override
	public boolean customerLogin(String email, String pass) {
		String sql = "SELECT * FROM khachhang WHERE Email = ? AND MatKhau = ?";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, email);
			pre.setString(2, pass);
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
	public boolean delCustomer(Customer customer) {
		String sql = "DELETE FROM khachhang WHERE KhachHangID =?;";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setInt(1, customer.getCustomerID());
		return this.exe(pre);
		} catch(SQLException e) {
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
			this.cp.releaseConnection(this.con, "Customer");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	

}
