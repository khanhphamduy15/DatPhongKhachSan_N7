package controller.bookingController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookingFunction;
import dao.BookingFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Booking;

/**
 * Servlet implementation class BookingController
 */
@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
		BookingFunction<Booking> b = new BookingFunctionImpl(cp);
		ArrayList<Booking> bookings = b.getAllBookings();
		request.setAttribute("bookings", bookings);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookingView/listBooking.jsp");
		dispatcher.forward(request,response);
	}

}
