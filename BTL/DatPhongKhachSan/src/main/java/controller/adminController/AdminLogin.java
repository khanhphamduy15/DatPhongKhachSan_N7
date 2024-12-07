package controller.adminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminFunction;
import dao.AdminFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
		AdminFunction af = new AdminFunctionImpl(cp);
		
		String mail = req.getParameter("admin-email");
		String password = req.getParameter("admin-password");
		
		boolean success = af.login(mail, password);
		
		if (success) {
			    HttpSession session = req.getSession();
			    session.setAttribute("admin-email", mail); // Lưu email vào session
			resp.sendRedirect("/DatPhongKhachSan/Dashboard");
		} else {
			resp.sendRedirect("/DatPhongKhachSan/login.jsp?error=true");
		}
	}
}
