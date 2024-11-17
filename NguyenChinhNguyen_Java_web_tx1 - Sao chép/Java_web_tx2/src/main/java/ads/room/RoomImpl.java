package ads.room;

import ads.objects.RoomObject;
import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;

import ads.basic.BasicImpl;

public class RoomImpl extends BasicImpl implements Room {

    public RoomImpl() {
        super("Room");
    }

    @Override
    public boolean addRoom(RoomObject room) {
        String sql = "INSERT INTO phong (PhongID, TenPhong, LoaiPhong, GiaPhong, MoTa, Anh, SoNguoiToiDa, TinhTrang) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, room.getPhongID());
            stmt.setString(2, room.getTenPhong());
            stmt.setString(3, room.getLoaiPhong());
            stmt.setBigDecimal(4, BigDecimal.valueOf(room.getGiaPhong())); // GiaPhong là kiểu double
            stmt.setString(5, room.getMoTa());
            stmt.setBytes(6, room.getAnh());
            stmt.setInt(7, room.getSoNguoiToiDa());
            stmt.setString(8, room.getTinhTrang());
            return this.add(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editRoom(RoomObject room) {
        String sql = "UPDATE phong SET TenPhong = ?, LoaiPhong = ?, GiaPhong = ?, MoTa = ?, Anh = ?, SoNguoiToiDa = ?, TinhTrang = ? " +
                     "WHERE PhongID = ?";
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, room.getTenPhong());
            stmt.setString(2, room.getLoaiPhong());
            stmt.setBigDecimal(3, BigDecimal.valueOf(room.getGiaPhong()));
            stmt.setString(4, room.getMoTa());
            stmt.setBytes(5, room.getAnh());
            stmt.setInt(6, room.getSoNguoiToiDa());
            stmt.setString(7, room.getTinhTrang());
            stmt.setString(8, room.getPhongID());
            return this.edit(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delRoom(RoomObject room) {
        String sql = "DELETE FROM phong WHERE PhongID = ?";
        try (PreparedStatement stmt = this.con.prepareStatement(sql)) {
            stmt.setString(1, room.getPhongID());
            return this.del(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<ResultSet> getRooms(String query) {
        if (query != null && !query.isEmpty()) {
            return this.gets(query);
        } else {
            String sql = "SELECT * FROM phong ORDER BY PhongID DESC LIMIT 10";
            return this.gets(sql);
        }
    }

    @Override
    public ResultSet getRoom(String phongID) {
        String sql = "SELECT * FROM phong WHERE PhongID = ?";
        return this.get(sql, phongID);
    }
}
