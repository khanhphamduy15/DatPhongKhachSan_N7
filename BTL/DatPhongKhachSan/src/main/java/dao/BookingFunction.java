package dao;

import java.util.ArrayList;

import db.ConnectionPool;
import model.Booking;
import model.Customer;

public interface BookingFunction<T> {
    boolean addBookingById(int customerId, String roomId, String bookingDate, String checkInDate, String checkOutDate, int guestNum, String status);
    ArrayList<Booking> getAllBookings();
    ArrayList<Booking> getBookingHistoryByCustomer(int customerId);
    ArrayList<Booking> getBookingByBookingId(int bookingId);
    boolean updateBookingStatus(int bookingId, String status);
    boolean delBookingByBookingId(Booking booking);
    boolean editBookingByBookingId(Booking booking);
    boolean delBookingByCustomerId(Customer customer);
    boolean editBookingByCustomerId(Customer customer);
    ConnectionPool getCP();
    void releaseConnection();
    boolean addBooking(Booking booking);
}
