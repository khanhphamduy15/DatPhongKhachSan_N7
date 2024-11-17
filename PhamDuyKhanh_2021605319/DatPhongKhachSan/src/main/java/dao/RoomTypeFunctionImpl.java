package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import model.RoomType;


public class RoomTypeFunctionImpl implements RoomTypeFunction {
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
	public boolean addRoomType(RoomType roomType) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO loaiPhong (");
		sql.append("TenLoai, MoTa) ");
		sql.append("VALUES (?, ?);");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, roomType.getRoomTypeName());
			pre.setString(2, roomType.getDescription());
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
	public boolean editRoomType(RoomType roomType) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE loaiPhong SET ");
		sql.append("TenLoai=? ,MoTa=? ");
		sql.append("WHERE LoaiPhongID = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1, roomType.getRoomTypeName());
			pre.setString(2, roomType.getDescription());
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
	public boolean delRoomType(RoomType roomType) {
		String sql = "DELETE FROM loaiPhong WHERE LoaiPhongID=?;";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setInt(1, roomType.getRoomTypeID());
		return this.exe(pre);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public ArrayList<RoomType> getListRoomType() {
		ArrayList<RoomType> list = new ArrayList();
        String sql = "SELECT * FROM loaiPhong;";

        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs != null) {
                while(rs.next()) {
                	RoomType item = new RoomType();
                	item.setRoomTypeID(rs.getInt("LoaiPhongID"));
    				item.setRoomTypeName(rs.getNString("TenLoai"));
    				item.setDescription(rs.getNString("MoTa"));
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
	public ConnectionPool getCP() {
		return this.cp;
	}

	@Override
	public void releaseConnection() {
		try {
			this.cp.releaseConnection(this.con, "RoomType");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
