package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import model.RoomType;
import db.ConnectionPoolImpl;


public class RoomTypeFunctionImpl implements RoomTypeFunction<RoomType> {
	private Connection con;
	private ConnectionPool cp;
	
	public RoomTypeFunctionImpl(ConnectionPool cp) {
        if (cp == null) {
            this.cp = new ConnectionPoolImpl();
        } else {
            this.cp = cp;
        }

        try {
            this.con = this.cp.getConnection("RoomType ");
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
            pre.setInt(3, roomType.getRoomTypeID());

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
		ArrayList<RoomType> list = new ArrayList<RoomType>();
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

	@Override
	public boolean delRoomTypeByID(int roomTypeID) {
		String sql = "DELETE FROM loaiPhong ";
    	sql += "WHERE LoaiPhongID = ?";
    	
    	try {
    		
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, roomTypeID);
			return this.exe(pre);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}

	@Override
	public RoomType getRoomTypeById(int LoaiPhongID) {
		String sql = "SELECT * FROM loaiPhong ";
    	sql += "WHERE LoaiPhongID = ?";
    	
    	RoomType item = new RoomType();
    	try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, LoaiPhongID);
			
			ResultSet rs = pre.executeQuery();
			
			if (rs.next()) {
				item.setRoomTypeID(rs.getInt("LoaiPhongID"));
				item.setRoomTypeName(rs.getNString("TenLoai"));
				item.setDescription(rs.getNString("MoTa"));
            }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return item;
	}

}
