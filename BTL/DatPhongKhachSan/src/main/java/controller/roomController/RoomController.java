package controller.roomController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.RoomFunction;
import dao.RoomFunctionImpl;
import model.Room;
import db.ConnectionPool;
import db.ConnectionPoolImpl;

/**
 * Servlet implementation class RoomController
 */
@WebServlet("/Room")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
        RoomFunction<Room> m = new RoomFunctionImpl(cp);
        ArrayList<Room> rooms = m.getListRoom(); // Lấy danh sách từ database
        req.setAttribute("roomList", rooms);
        
        // Forward to JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("/RoomView/listRoom.jsp");
        dispatcher.forward(req, res);
	}

}
