package ads.basic;

import java.sql.*;
import java.util.ArrayList;
import ads.ConnectionPool;
import ads.ConnectionPoolImpl;

public class BasicImpl implements Basic {

    // Đối tượng làm việc với Basic
    private String objectName;

    // Bộ quản lý kết nối được chia sẻ
    private ConnectionPool cp = ConnectionPoolImpl.getInstance();

    // Kết nối của riêng Basic sử dụng
    protected Connection con;

    // Constructor
    public BasicImpl(String objectName) {
        this.objectName = objectName; // Xác định đối tượng làm việc

        // Xin kết nối
        try {
            this.con = this.cp.getConnection(objectName);

            // Kiểm tra chế độ thực thi của kết nối
            if (this.con.getAutoCommit()) {
                this.con.setAutoCommit(false); // Hủy chế độ thực thi tự động
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thực thi câu lệnh PreparedStatement
    private boolean exe(PreparedStatement pre) {
        if (pre != null) {
            try {
                int results = pre.executeUpdate(); // Lấy số lượng bản ghi được tác động
                if (results == 0) {
                    this.con.rollback(); // Nếu không có bản ghi nào bị tác động, rollback
                    return false;
                }
                this.con.commit(); // Thực thi thành công
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    this.con.rollback(); // Trở lại trạng thái an toàn
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(PreparedStatement pre) {
        return this.exe(pre);
    }

    @Override
    public boolean edit(PreparedStatement pre) {
        return this.exe(pre);
    }

    @Override
    public boolean del(PreparedStatement pre) {
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
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResultSet get(String sql, int roomId) {
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            if (roomId > 0) {
                pre.setInt(1, roomId);
            }
            return pre.executeQuery();
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void releaseConnection() {
        try {
            this.cp.releaseConnection(this.con, this.objectName); // Trả lại kết nối
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.releaseConnection(); // Đảm bảo trả kết nối khi đối tượng bị hủy
        super.finalize();
    }

	@Override
	public ResultSet get(String sql, String value) {
		// TODO Auto-generated method stub
		return null;
	}
}
