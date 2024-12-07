package controller.bookingController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookingFunction;
import dao.BookingFunctionImpl;
import dao.CustomerFunction;
import dao.CustomerFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Booking;
import model.Customer;

/**
 * Servlet implementation class BookingHistory
 */
@WebServlet("/CustomerHistory")
public class BookingHistory extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        String email = (String) session.getAttribute("email"); // Lấy email khách hàng từ session
	        
	        ConnectionPool cp = new ConnectionPoolImpl();
	        BookingFunction<Booking> b = new BookingFunctionImpl(cp);
	        int khachHangID = -1; // Biến để lưu KhachHangID
	    	
			CustomerFunction<Customer> c = new CustomerFunctionImpl(cp);

			// Kiểm tra xem email có null không
			if (email != null) {
				khachHangID = c.getCustomerIdByEmail(email); // Lấy KhachHangID từ email
			}
			ArrayList<Booking> booking = b.getBookingHistoryByCustomer(khachHangID);
	
			// Gửi dữ liệu đến trang JSP
	        request.setAttribute("history", booking);
	        request.getRequestDispatcher("/CustomerView/History.jsp").forward(request, response);
	        
	}
}


