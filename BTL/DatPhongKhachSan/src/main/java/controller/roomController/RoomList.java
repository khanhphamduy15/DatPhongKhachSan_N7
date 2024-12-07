package controller.roomController;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class RoomList
 */
@WebServlet("/RoomList")
public class RoomList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionPool cp = new ConnectionPoolImpl();
		RoomFunction<Room> r = new RoomFunctionImpl(cp);
		
        int roomTypeID = Integer.parseInt(request.getParameter("roomTypeID"));
        // Lấy danh sách các phòng thuộc loại phòng này

        ArrayList<Room> roomList = r.getRoomByType(roomTypeID);
        // Gửi dữ liệu đến trang JSP
        request.setAttribute("roomList", roomList);
        request.getRequestDispatcher("/RoomView/Room.jsp").forward(request, response);
        for (Room ro : roomList) {
        	System.out.println(ro.getRoomImg());
        }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
