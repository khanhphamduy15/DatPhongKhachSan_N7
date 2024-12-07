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
 * Servlet implementation class DetailedRoom
 */
@WebServlet("/DetailedRoom")
public class DetailedRoom extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        ConnectionPool cp = new ConnectionPoolImpl();
	        RoomFunction<Room> r = new RoomFunctionImpl(cp);

	        String roomID = request.getParameter("roomID");
	        Room room = r.getRoomById(roomID); // Phương thức lấy thông tin phòng theo ID
	        
	        request.setAttribute("room", room);
	        request.getRequestDispatcher("/RoomView/DetailRoom.jsp").forward(request, response);
	    }

}
