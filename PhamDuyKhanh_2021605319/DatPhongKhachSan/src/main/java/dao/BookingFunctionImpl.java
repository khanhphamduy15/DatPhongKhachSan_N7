package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionPool;
import model.Booking;
import model.Customer;

public class BookingFunctionImpl implements BookingFunction {
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
	public boolean addBookingById(int customerId, String roomId, Date bookingDate, Date checkInDate, Date checkOutDate, int guestNum,
			String status) {
		String sql = "INSERT INTO donDat (KhachHangID, PhongID, NgayDatPhong, NgayNhanPhong, NgayTraPhong, SoLuong, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			pre.setInt(1, customerId); // ID của khách hàng
			pre.setString(2, roomId); // ID của phòng
			pre.setDate(3, bookingDate); // Ngày đặt phòng
			pre.setDate(4, checkInDate); // Ngày check-in
			pre.setDate(5, checkOutDate); // Ngày check-out
			pre.setInt(6, guestNum); // Số lượng khách
			pre.setString(7, status); // Trạng thái của đơn (ví dụ: 'Chờ duyệt')

			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Booking> getAllBookings() {
		ArrayList<Booking> list = new ArrayList();
		String sql = "SELECT * FROM donDat;";

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Booking item = new Booking();
					item.setBookingID(rs.getInt("DonDatPhongID"));
					item.setCustomerID(rs.getInt("KhachHangID"));
					item.setRoomID(rs.getNString("PhongID"));
					item.setStartDate(rs.getDate("NgayDatPhong"));
					item.setStayDate(rs.getDate("NgayNhanPhong"));
					item.setEndDate(rs.getDate("NgayTraPhong"));
					item.setGuestNum(rs.getInt("SoLuong"));
					item.setStatus(rs.getNString("TrangThai"));
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
	public ArrayList<Booking> getBookingHistoryByCustomer(int customerId) {
		String sql = "SELECT * FROM donDat WHERE KhachHangID LIKE ?";
		ArrayList<Booking> booking = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, "%" + customerId + "%");

			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Booking item = new Booking();
					item.setBookingID(rs.getInt("DonDatPhongID"));
					item.setCustomerID(rs.getInt("KhachHangID"));
					item.setRoomID(rs.getNString("PhongID"));
					item.setStartDate(rs.getDate("NgayDatPhong"));
					item.setStayDate(rs.getDate("NgayNhanPhong"));
					item.setEndDate(rs.getDate("NgayTraPhong"));
					item.setGuestNum(rs.getInt("SoLuong"));
					item.setStatus(rs.getNString("TrangThai"));
					booking.add(item);
				}

				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return booking;
	}

	@Override
	public ArrayList<Booking> getBookingByBookingId(int bookingId) {
		String sql = "SELECT * FROM donDat WHERE DonDatPhongID LIKE ?";
		ArrayList<Booking> booking = new ArrayList<>();

		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, "%" + bookingId + "%");

			ResultSet rs = pre.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Booking item = new Booking();
					item.setBookingID(rs.getInt("DonDatPhongID"));
					item.setCustomerID(rs.getInt("KhachHangID"));
					item.setRoomID(rs.getNString("PhongID"));
					item.setStartDate(rs.getDate("NgayDatPhong"));
					item.setStayDate(rs.getDate("NgayNhanPhong"));
					item.setEndDate(rs.getDate("NgayTraPhong"));
					item.setGuestNum(rs.getInt("SoLuong"));
					item.setStatus(rs.getNString("TrangThai"));
					booking.add(item);
				}

				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return booking;
	}

	@Override
	public boolean updateBookingStatus(int bookingId, String status) {
		String sql = "UPDATE donDat SET TrangThai = ? WHERE BookingID = ?";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			pre.setString(1, status);
			pre.setInt(2, bookingId);
			return pre.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delBookingByBookingId(Booking booking) {
		String sql = "DELETE FROM donDat WHERE DonDatPhongID=?;";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setString(1, booking.getRoomID());
		return this.exe(pre);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

	@Override
	public boolean editBookingByBookingId(Booking booking) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delBookingByCustomerId(Customer customer) {
		String sql = "DELETE FROM donDat WHERE KhachHangID=?;";
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
	public boolean editBookingByCustomerId(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

}
