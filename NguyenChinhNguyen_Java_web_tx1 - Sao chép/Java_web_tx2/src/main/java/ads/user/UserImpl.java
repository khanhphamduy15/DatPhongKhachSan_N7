package ads.user;

import ads.objects.UserObject;
import java.sql.*;
import java.util.ArrayList;

import ads.basic.BasicImpl;

public class UserImpl extends BasicImpl implements User {

    public UserImpl() {
        super("User");
    }

    @Override
    public boolean addUser(UserObject user) {
        String sql = "INSERT INTO khachhang (HoTen, Email, MatKhau, SoDienThoai, DiaChi, NgayDangKy) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, user.getHoTen());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMatKhau());
            stmt.setString(4, user.getSoDienThoai());
            stmt.setString(5, user.getDiaChi());
            stmt.setDate(6, java.sql.Date.valueOf(user.getNgayDangKy())); // LocalDate to SQL Date
            return this.add(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editUser(UserObject user) {
        String sql = "UPDATE khachhang SET HoTen = ?, Email = ?, MatKhau = ?, SoDienThoai = ?, DiaChi = ? " +
                     "WHERE KhachHangID = ?";
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, user.getHoTen());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMatKhau());
            stmt.setString(4, user.getSoDienThoai());
            stmt.setString(5, user.getDiaChi());
            stmt.setInt(6, user.getKhachHangID());
            return this.edit(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delUser(UserObject user) {
        String sql = "DELETE FROM khachhang WHERE KhachHangID = ?";
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setInt(1, user.getKhachHangID());
            return this.del(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<ResultSet> getUsers(String query) {
        if (query != null && !query.isEmpty()) {
            return this.gets(query);
        } else {
            String sql = "SELECT * FROM khachhang ORDER BY KhachHangID DESC LIMIT 10";
            return this.gets(sql);
        }
    }

    @Override
    public ResultSet getUser(int userId) {
        String sql = "SELECT * FROM khachhang WHERE KhachHangID = ?";
        return this.get(sql, userId);
    }

    @Override
    public ResultSet getUser(String email, String password) {
        String sql = "SELECT * FROM khachhang WHERE Email = ? AND MatKhau = ?";
        return this.get(sql, email, password);
    }

	@Override
	public ResultSet getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
