package controller.adminController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingFunction;
import dao.BookingFunctionImpl;
import dao.RoomFunction;
import dao.RoomFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Booking;
import model.Room;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/Dashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectionPool cp = new ConnectionPoolImpl();
		BookingFunction<Booking> bf = new BookingFunctionImpl(cp);
		RoomFunction<Room> rf = new RoomFunctionImpl(cp);
		try {
			// Gọi các phương thức để lấy số liệu
			int totalOrders = bf.getTotalOrders();
			int approvedOrders = bf.getApprovedOrders();
			int cancelledOrders = bf.getCancelledOrders();
			
			int totalRooms = rf.getTotalRooms();
			int freeRooms = rf.getFreeRooms();
			int bookedRooms = rf.getBookedRooms();

			// Gán dữ liệu vào thuộc tính request
			request.setAttribute("totalOrders", totalOrders);
			request.setAttribute("approvedOrders", approvedOrders);
			request.setAttribute("cancelledOrders", cancelledOrders);
			
			request.setAttribute("totalRooms", totalRooms);
			request.setAttribute("freeRooms", freeRooms);
			request.setAttribute("bookedRooms", bookedRooms);

			request.getRequestDispatcher("/AdminView/AdminDashboard.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// Xử lý lỗi nếu cần
			request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình lấy số liệu.");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
