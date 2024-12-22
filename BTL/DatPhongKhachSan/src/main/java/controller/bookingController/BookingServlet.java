package controller.bookingController;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
 * Servlet implementation class Booking
 */
@WebServlet("/BookingServlet")
@MultipartConfig
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
		response.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
		HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email"); // Lấy email từ session

			int khachHangID = -1; // Biến để lưu KhachHangID

			CustomerFunction<Customer> c = new CustomerFunctionImpl(cp);

			// Kiểm tra xem email có null không
			if (email != null) {
				khachHangID = c.getCustomerIdByEmail(email); // Lấy KhachHangID từ email
			}

			String roomId = request.getParameter("upRoomId"); // Lấy ID phòng
			String guestCountStr = request.getParameter("upGuestCount"); // Lấy số lượng khách
			String startDate = request.getParameter("upStartDate"); // Lấy ngày bắt đầu
			String endDate = request.getParameter("upEndDate"); // Lấy ngày kết thúc

			// Chuyển đổi số lượng khách từ String sang Integer
			int guestCount = Integer.parseInt(guestCountStr);

			// Tiến hành lưu thông tin đặt phòng vào cơ sở dữ liệu hoặc xử lý tiếp theo
			// Ví dụ:
			BookingFunction<Booking> bookingFunction = new BookingFunctionImpl(cp);
			Booking booking = new Booking();
			booking.setCustomerID(khachHangID);
			booking.setRoomID(roomId);
			booking.setStartDate(Date.valueOf(startDate)); // Chuyển đổi String sang Date
			booking.setEndDate(Date.valueOf(endDate)); // Chuyển đổi String sang Date
			booking.setGuestNum(guestCount);

			// Lưu booking vào cơ sở dữ liệu
			boolean isSuccess = bookingFunction.addBooking(booking);
			
			response.setContentType("text/html;charset=UTF-8");
	        if (isSuccess) {
	            response.getWriter().write(
	                "<html>" +
	                "<body>" +
	                "<h1>Đặt phòng thành công!</h1>" +
	                "<p>Bạn sẽ được chuyển hướng trong giây lát...</p>" +
	                "</body>" +
	                "</html>"
	            );
	        } else {
	            response.getWriter().write(
	                "<html>" +
	                "<body>" +
	                "<h1>Đặt phòng thất bại!</h1>" +
	                "<p>Vui lòng thử lại sau.</p>" +
	                "</body>" +
	                "</html>"
	            );
	        }
	}
}

