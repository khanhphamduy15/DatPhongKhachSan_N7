package controller.bookingController;

import java.io.IOException;
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
 * Servlet implementation class BookingDeny
 */
@WebServlet("/Deny")
public class BookingDeny extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingDeny() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
		BookingFunction<Booking> b = new BookingFunctionImpl(cp);
		int idString = Integer.parseInt(request.getParameter("bookingID"));
		String huy = "DaHuy";
		boolean check = b.updateBookingStatus(idString, huy);
		if (check) {
				response.sendRedirect(request.getContextPath() + "/BookingController?message=denySuccess");
			} else {
				response.sendRedirect(request.getContextPath() + "/BookingController?error=denyFailed");
			}

	}
}

