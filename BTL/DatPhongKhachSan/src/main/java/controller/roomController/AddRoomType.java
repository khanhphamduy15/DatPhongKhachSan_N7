package controller.roomController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoomTypeFunctionImpl;
import dao.RoomTypeFunction;
import model.RoomType;
import db.ConnectionPool;
import db.ConnectionPoolImpl;

/**
 * Servlet implementation class AddRoomType
 */
@WebServlet("/AddRoomType")
public class AddRoomType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoomType() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
        req.getRequestDispatcher("AddRoomType.jsp").forward(req, res);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
        RoomTypeFunction<RoomType> m = new RoomTypeFunctionImpl(cp);
        String RTname = req.getParameter("roomTypeName");
        String desc = req.getParameter("description");


        
        RoomType a = new RoomType( RTname, desc);
        boolean isSuccess = m.addRoomType(a);
        
        if (isSuccess) {
            res.sendRedirect(req.getContextPath() + "/RoomType?message=addSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
	}
}
