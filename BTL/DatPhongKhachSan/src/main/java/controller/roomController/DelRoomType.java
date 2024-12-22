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
 * Servlet implementation class DelRoomType
 */
@WebServlet("/DelRoomType")
public class DelRoomType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelRoomType() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
        RoomTypeFunction<RoomType> m = new RoomTypeFunctionImpl(cp);
        
        String idString = request.getParameter("roomTypeID");
        
        if (idString != null) {
        	int id = Integer.parseInt(idString);
        	
        	boolean check = m.delRoomTypeByID(id);
        	
        	if (check) {
        		response.sendRedirect(request.getContextPath() + "/RoomType?message=deleteSuccess");
        	} else {
        		response.sendRedirect(request.getContextPath() + "/RoomType?error=deleteNotSuccess");
        	}
        } else {
        	response.sendRedirect(request.getContextPath() + "/RoomType?error=invalidid");
        }
	}

}
