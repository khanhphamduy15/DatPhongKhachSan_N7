package controller.roomController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.RoomTypeFunction;
import dao.RoomTypeFunctionImpl;
import model.RoomType;
import db.ConnectionPool;
import db.ConnectionPoolImpl;

/**
 * Servlet implementation class EditRoomType
 */
@WebServlet("/EditRoomType")
public class EditRoomType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRoomType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
		response.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		String rtid = request.getParameter("roomTypeID");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        RoomTypeFunction<RoomType> m = new RoomTypeFunctionImpl(cp);
        
        if (rtid != null) {        	
        	int rt_id = Integer.parseInt(rtid);
        	RoomType roomType = m.getRoomTypeById(rt_id);
        	request.setAttribute("RoomT", roomType);
        }
        request.getRequestDispatcher("RoomView/EditRoomType.jsp").forward(request, response);
        response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
		response.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		ConnectionPool cp = new ConnectionPoolImpl();
        RoomTypeFunction<RoomType> f = new RoomTypeFunctionImpl(cp);
        int rt_id = Integer.parseInt(request.getParameter("roomTypeID"));

        String name = request.getParameter("roomTypeName");
        String desc = request.getParameter("description");

        
        RoomType m = new RoomType(rt_id,name,desc);

        
        boolean isSuccess = f.editRoomType(m);
        
        if (isSuccess) {
        	response.sendRedirect(request.getContextPath() + "/RoomType?message=updateSuccess");
        } else {
            // Nếu không thành công, xử lý lỗi ở đây
            // Ví dụ: có thể chuyển hướng về trang lỗi, hoặc hiển thị thông báo lỗi khác
        	response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
	}

}
