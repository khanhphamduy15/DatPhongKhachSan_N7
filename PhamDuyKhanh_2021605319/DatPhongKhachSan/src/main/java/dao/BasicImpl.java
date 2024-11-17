package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import db.ConnectionPoolImpl;

public class BasicImpl implements Basic {

	private String objectName;
	protected Connection con;
	private ConnectionPool cp;

	@Override
	public boolean add(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean edit(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public boolean del(PreparedStatement pre) {
		// TODO Auto-generated method stub
		return this.exe(pre);
	}

	@Override
	public ArrayList<ResultSet> gets(String multiSelect) {
		ArrayList<ResultSet> res = new ArrayList<>();

		try {
			PreparedStatement stmt = this.con.prepareStatement(multiSelect);
			boolean results = stmt.execute();
			do {
				if (results) {
					res.add(stmt.getResultSet());
				}
				results = stmt.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);
			} while (results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ResultSet get(String sql, int value) {

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			if (value > 0) {
				pre.setInt(1, value);
			}
			return pre.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet get(String sql, String name, String pass) {
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, pass);
			return pre.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ConnectionPool getCP() {
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		try {
			this.cp.releaseConnection(this.con, this.objectName);
		} catch (SQLException var2) {
			var2.printStackTrace();
		}

	}

	public BasicImpl(ConnectionPool cp) {
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		try {
			this.con = this.cp.getConnection("Admin");
			if (this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		} catch (SQLException var3) {
			var3.printStackTrace();
		}

	}

	private boolean exe(PreparedStatement pre) {
		if (pre == null) {
			return false;
		} else {
			try {
				int result = pre.executeUpdate();
				if (result != 0) {
					this.con.commit();
					return true;
				}

				this.con.rollback();
				return false;
			} catch (SQLException var9) {
				var9.printStackTrace();

				try {
					this.con.rollback();
				} catch (SQLException var8) {
					var8.printStackTrace();
				}
			} finally {
				pre = null;
			}

			return false;
		}
	}

}
