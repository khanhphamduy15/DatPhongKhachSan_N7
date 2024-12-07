package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerFunction;
import dao.CustomerFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Customer;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
        response.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
        
        // Initialize connection pool and DAO
        ConnectionPool cp = new ConnectionPoolImpl();
        CustomerFunction customerFunc = new CustomerFunctionImpl(cp);

        try {
            // Retrieve form data
            String name = request.getParameter("reg-name");
            String email = request.getParameter("reg-email");
            String password = request.getParameter("reg-password");
            int phone = Integer.parseInt(request.getParameter("reg-phone"));
            String address = request.getParameter("reg-address");

            // Create customer object
            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPass(password);
            customer.setPhone(phone);
            customer.setAddress(address);
            String redirectURL = "http://localhost:8080/DatPhongKhachSan"; // Đường dẫn tuyệt đối


            // Save customer to the database
            boolean isSuccess = customerFunc.customerRegister(customer);

            // Handle success or failure
            if (isSuccess) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write("<html><body>");
                response.getWriter().write("<h1>Đăng ký thành công!</h1>");
                response.getWriter().write("<p>Chuyển hướng sau 3 giây...</p>");
                response.getWriter().write("<script>setTimeout(function() { window.location.href = '" + redirectURL + "'; }, 3000);</script>");
                response.getWriter().write("</body></html>");
            } else {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write("<html><body>");
                response.getWriter().write("<h1>Đăng ký thất bại. Vui lòng thử lại.</h1>");
                response.getWriter().write("<p>Chuyển hướng sau 3 giây...</p>");
                response.getWriter().write("<script>setTimeout(function() { window.location.href = '" + redirectURL + "'; }, 3000);</script>");
                response.getWriter().write("</body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}

