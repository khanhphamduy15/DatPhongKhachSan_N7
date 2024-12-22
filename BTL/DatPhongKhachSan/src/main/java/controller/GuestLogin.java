package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerFunction;
import dao.CustomerFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;


@WebServlet("/GuestLogin")
public class GuestLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
		CustomerFunction cf = new CustomerFunctionImpl(cp);
		
		String mail = req.getParameter("email");
		String password = req.getParameter("pass");
		
		boolean success = cf.customerLogin(mail, password);
		
		if (success) {
			    HttpSession session = req.getSession();
			    session.setAttribute("email", mail); // Lưu email vào session
			resp.sendRedirect("/DatPhongKhachSan/");
		} else {
			resp.sendRedirect("/DatPhongKhachSan/login.jsp?error=true");
		}
	}
}
