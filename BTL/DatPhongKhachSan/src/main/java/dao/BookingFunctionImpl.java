package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Booking;
import model.Customer;

public class BookingFunctionImpl implements BookingFunction<Booking> {

	private Connection con;
	private ConnectionPool cp;

	public BookingFunctionImpl(ConnectionPool cp) {
		if (cp == null) {
			this.cp = new ConnectionPoolImpl();
		} else {
			this.cp = cp;
		}

		try {
			this.con = this.cp.getConnection("Booking ");
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
	public boolean addBookingById(int customerId, String roomId, String bookingDate, String checkInDate,
			String checkOutDate, int guestNum, String status) {
		String sql = "INSERT INTO donDat (KhachHangID, PhongID, NgayDatPhong, NgayNhanPhong, NgayTraPhong, SoLuong, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			pre.setInt(1, customerId); // ID của khách hàng
			pre.setString(2, roomId); // ID của phòng
			pre.setString(3, bookingDate); // Ngày đặt phòng
			pre.setString(4, checkInDate); // Ngày check-in
			pre.setString(5, checkOutDate); // Ngày check-out
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
					item.setCreateDate(rs.getDate("NgayDatPhong"));
					item.setStartDate(rs.getDate("NgayNhanPhong"));
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
					item.setCreateDate(rs.getDate("NgayDatPhong"));
					item.setStartDate(rs.getDate("NgayNhanPhong"));
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
					item.setCreateDate(rs.getDate("NgayDatPhong"));
					item.setStartDate(rs.getDate("NgayNhanPhong"));
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
		String sql = "UPDATE donDat SET TrangThai = ? WHERE DonDatPhongID = ?";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			pre.setString(1, status);
			pre.setInt(2, bookingId);
			return this.exe(pre);

		} catch (SQLException e) {
			// Ghi lại lỗi hoặc xử lý theo cách khác
			System.err.println("Error updating booking status: " + e.getMessage());
			e.printStackTrace();
		}
		return false; // Trả về false nếu có lỗi hoặc không có hàng nào được cập nhật
	}

	@Override
	public boolean delBookingByBookingId(Booking booking) {
		String sql = "DELETE FROM donDat WHERE DonDatPhongID=?;";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, booking.getRoomID());
			return this.exe(pre);
		} catch (SQLException e) {
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
			this.cp.releaseConnection(this.con, "Booking");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public boolean addBooking(Booking booking) {
		String sql = "INSERT INTO donDat (KhachHangID, PhongID, NgayNhanPhong, NgayTraPhong, SoLuong, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pre = this.con.prepareStatement(sql)) {
			pre.setInt(1, booking.getCustomerID());
			pre.setString(2, booking.getRoomID());
			pre.setDate(3, booking.getStartDate());
			pre.setDate(4, booking.getEndDate());
			pre.setInt(5, booking.getGuestNum());
			pre.setString(6, "DangCho");
			return this.exe(pre);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getApprovedOrders() {
		String sql = "SELECT COUNT(*) FROM donDat WHERE TrangThai = 'DaChapThuan'";
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
	public int getTotalOrders() {
		String sql = "SELECT COUNT(*) FROM donDat";
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
	public int getCancelledOrders() {
		String sql = "SELECT COUNT(*) FROM donDat WHERE TrangThai = 'DaHuy'";
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
