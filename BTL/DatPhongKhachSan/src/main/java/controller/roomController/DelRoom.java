package controller.roomController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoomFunction;
import dao.RoomFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Room;

/**
 * Servlet implementation class DelRoom
 */
@WebServlet("/DelRoom")
public class DelRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelRoom() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
        RoomFunction<Room> m = new RoomFunctionImpl(cp);
        
        String idString = request.getParameter("roomID");
        
        if (idString != null) {
        	
        	boolean check = m.delRoomByID(idString);
        	
        	if (check) {
        		response.sendRedirect(request.getContextPath() + "/Room?message=deleteSuccess");
        	} else {
        		response.sendRedirect(request.getContextPath() + "/Room?error=deleteNotSuccess");
        	}
        } else {
        	response.sendRedirect(request.getContextPath() + "/Room?error=invalidid");
        }
	}

}
