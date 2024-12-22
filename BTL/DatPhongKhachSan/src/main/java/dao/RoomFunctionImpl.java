package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import model.Room;
import db.ConnectionPoolImpl;

public class RoomFunctionImpl implements RoomFunction<Room> {
	private Connection con;
	private ConnectionPool cp;

	public RoomFunctionImpl(ConnectionPool cp) {
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		try {
			this.con = this.cp.getConnection("Room ");
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
	public boolean addRoom(Room room) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO phong (");
		sql.append("PhongID, TenPhong, LoaiPhongID, AnhPhong, GiaPhong, SoNguoiToiDa, TinhTrang) ");
		sql.append("VALUES ( ?, ?, ?, ?, ?, ?, ?);");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, room.getRoomID());
			pre.setString(2, room.getRoomName());
			pre.setInt(3, room.getRoomTypeID());
			pre.setString(4, room.getRoomImg());
			pre.setDouble(5, room.getPrice());
			pre.setInt(6, room.getMaxPerson());
			pre.setString(7, room.getStatus());

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
	public boolean editRoom(Room room) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE phong SET ");
		sql.append("TenPhong=? ,LoaiPhongID=?, AnhPhong=?, GiaPhong=?, SoNguoiToiDa=?, TinhTrang=? ");
		sql.append("WHERE PhongID = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, room.getRoomName());
			pre.setInt(2, room.getRoomTypeID());
			pre.setString(3, room.getRoomImg());
			pre.setDouble(4, room.getPrice());
			pre.setInt(5, room.getMaxPerson());
			pre.setString(6, room.getStatus());
			pre.setString(7, room.getRoomID());
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
	public boolean delRoom(Room room) {
		String sql = "DELETE FROM phong WHERE PhongID=?;";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, room.getRoomID());
			return this.exe(pre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Room getRoomById(String roomID) {
		String sql = "SELECT * FROM phong WHERE PhongID = ?";
		Room room = null;

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, roomID);

			ResultSet rs = pre.executeQuery();

			if (rs.next()) {
				room = new Room();
				room.setRoomID(rs.getNString("PhongID"));
				room.setRoomName(rs.getNString("TenPhong"));
				room.setRoomTypeID(rs.getInt("LoaiPhongID"));
				room.setRoomImg(rs.getString("AnhPhong"));
				room.setPrice(rs.getDouble("GiaPhong"));
				room.setMaxPerson(rs.getInt("SoNguoiToiDa"));
				room.setStatus(rs.getNString("TinhTrang"));
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return room;
	}

	@Override
	public ArrayList<Room> getRoomByType(int roomTypeID) {
		String sql = "SELECT * FROM phong WHERE LoaiPhongID =?";
		ArrayList<Room> room = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, roomTypeID);

			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Room item = new Room();
					item.setRoomID(rs.getNString("PhongID"));
					item.setRoomName(rs.getNString("TenPhong"));
					item.setRoomTypeID(rs.getInt("LoaiPhongID"));
					item.setRoomImg(rs.getString("AnhPhong"));
					item.setPrice(rs.getDouble("GiaPhong"));
					item.setMaxPerson(rs.getInt("SoNguoiToiDa"));
					item.setStatus(rs.getNString("TinhTrang"));
					room.add(item);
				}

				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return room;
	}

	@Override
	public ArrayList<Room> getListRoom() {
		ArrayList<Room> list = new ArrayList();
		String sql = "SELECT * FROM phong;";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Room item = new Room();
					item.setRoomID(rs.getNString("PhongID"));
					item.setRoomName(rs.getNString("TenPhong"));
					item.setRoomTypeID(rs.getInt("LoaiPhongID"));
					item.setRoomImg(rs.getString("AnhPhong"));
					item.setPrice(rs.getDouble("GiaPhong"));
					item.setMaxPerson(rs.getInt("SoNguoiToiDa"));
					item.setStatus(rs.getNString("TinhTrang"));
					list.add(item);

				}
				rs.close();
			}
		} catch (SQLException var6) {
			var6.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean updateRoomStatus(String roomID, String status) {
		String sql = "UPDATE phong SET TinhTrang = ? WHERE PhongID = ?";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			pre.setString(1, status);
			pre.setString(2, roomID);
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
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
			this.cp.releaseConnection(this.con, "Room");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public boolean delRoomByID(String roomID) {
		String sql = "DELETE FROM phong ";
		sql += "WHERE PhongID = ?";

		try {

			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, roomID);
			return this.exe(pre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateRoomStatus() {
		String updateQuery = "UPDATE phong p " + "JOIN donDat d ON p.PhongID = d.PhongID " + "SET p.TrangThai = CASE "
				+ "    WHEN CURRENT_DATE >= d.NgayNhanPhong AND CURRENT_DATE < d.NgayTraPhong THEN 'DangSuDung' "
				+ "    WHEN CURRENT_DATE >= d.NgayTraPhong THEN 'DangTrong' " + "    ELSE p.TrangThai " + "END "
				+ "WHERE d.TrangThai = 'DaChapThuan';";

		try {
			PreparedStatement pre = this.con.prepareStatement(updateQuery);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int getFreeRooms() {
		String sql = "SELECT COUNT(*) FROM phong WHERE TinhTrang = 'ConTrong'";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			ResultSet resultSet = pre.executeQuery();
			if (resultSet.next()) {
				try {
					return resultSet.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getTotalRooms() {
		String sql = "SELECT COUNT(*) FROM phong";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			ResultSet resultSet = pre.executeQuery();
			if (resultSet.next()) {
				try {
					return resultSet.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getBookedRooms() {
		String sql = "SELECT COUNT(*) FROM phong WHERE TinhTrang = 'DaDat'";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			ResultSet resultSet = pre.executeQuery();
			if (resultSet.next()) {
				try {
					return resultSet.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}
}
