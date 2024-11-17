package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import model.Room;

public class RoomFunctionImpl implements RoomFunction{
	private Connection con;
	private ConnectionPool cp;
	
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
		sql.append("PhongID, TenPhong, LoaiPhong, AnhPhong, GiaPhong, MoTa, SoNguoiToiDa) ");
		sql.append("VALUES ( ?, ?, ?, ?, ?, ?, ?);");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, room.getRoomID());
			pre.setString(2, room.getRoomName());
			pre.setInt(3, room.getRoomTypeID());
			pre.setString(4, room.getRoomImg());
			pre.setDouble(5, room.getPrice());
			pre.setString(6, room.getDescription());
			pre.setInt(7, room.getMaxPerson());

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
		sql.append("TenPhong=? ,LoaiPhong=?, AnhPhong=?, GiaPhong=?, MoTa=?, SoNguoiToiDa=? ");
		sql.append("WHERE PhongID = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, room.getRoomName());
			pre.setInt(2, room.getRoomTypeID());
			pre.setString(3, room.getRoomImg());
			pre.setDouble(4, room.getPrice());
			pre.setString(5, room.getDescription());
			pre.setInt(6, room.getMaxPerson());
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public Room getRoomById(String roomID) {
		String sql = "SELECT * FROM phong ";
    	sql += "WHERE PhongID = ?";
    	
    	Room item = new Room();
    	try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, roomID);
			
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				item.setRoomID(rs.getNString("PhongID"));
				item.setRoomName(rs.getNString("TenPhong"));
				item.setRoomTypeID(rs.getInt("LoaiPhong"));
				item.setRoomImg(rs.getString("AnhPhong"));
				item.setPrice(rs.getDouble("GiaPhong"));
				item.setDescription(rs.getNString("MoTa"));
				item.setMaxPerson(rs.getInt("SoNguoiToiDa"));
				item.setStatus(rs.getNString("TinhTrang"));

            }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return item;
	}

	@Override
	public ArrayList<Room> getRoomByType(String roomTypeID) {
		String sql = "SELECT * FROM phong WHERE LoaiPhong LIKE ?";
        ArrayList<Room> room = new ArrayList<>();
        
        try {
        	PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, "%" + roomTypeID + "%");

            ResultSet rs = pre.executeQuery();
            
            if (rs != null) {
                while(rs.next()) {
                	Room item = new Room();
                	item.setRoomID(rs.getNString("PhongID"));
    				item.setRoomName(rs.getNString("TenPhong"));
    				item.setRoomTypeID(rs.getInt("LoaiPhong"));
    				item.setRoomImg(rs.getString("AnhPhong"));
    				item.setPrice(rs.getDouble("GiaPhong"));
    				item.setDescription(rs.getNString("MoTa"));
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
                while(rs.next()) {
                	Room item = new Room();
                	item.setRoomID(rs.getNString("PhongID"));
    				item.setRoomName(rs.getNString("TenPhong"));
    				item.setRoomTypeID(rs.getInt("LoaiPhong"));
    				item.setRoomImg(rs.getString("AnhPhong"));
    				item.setPrice(rs.getDouble("GiaPhong"));
    				item.setDescription(rs.getNString("MoTa"));
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
}
