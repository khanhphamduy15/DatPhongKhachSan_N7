package dao;

import java.sql.Date;
import java.util.ArrayList;

import model.Booking;
import model.Customer;

public interface BookingFunction {
    // Thêm một đơn đặt phòng mới
    boolean addBookingById(int customerId, String roomId, Date bookingDate, Date checkInDate, Date checkOutDate, int guestNum, String status);
    
    boolean delBookingByBookingId(Booking booking);
    
    boolean delBookingByCustomerId(Customer customer);

	boolean editBookingByBookingId(Booking booking);
	
    boolean editBookingByCustomerId(Customer customer);


    // Lấy tất cả các đơn đặt phòng
    ArrayList<Booking> getAllBookings();

    // Lấy lịch sử đặt phòng của khách hàng
    ArrayList<Booking> getBookingHistoryByCustomer(int customerId);

    // Lấy thông tin chi tiết của một đơn đặt phòng
    ArrayList<Booking> getBookingByBookingId(int bookingId);

    // Cập nhật trạng thái của đơn đặt phòng (chấp nhận, hủy)
    boolean updateBookingStatus(int bookingId, String status);
    
    
}