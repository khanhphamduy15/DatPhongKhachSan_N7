package controller.roomController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoomTypeFunction;
import dao.RoomTypeFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.RoomType;

/**
 * Servlet implementation class RoomTypeController
 */
@WebServlet("/RoomType")
public class RoomTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Cấu hình kết nối cơ sở dữ liệu
        ConnectionPool cp = new ConnectionPoolImpl();
        RoomTypeFunction<RoomType> rt= new RoomTypeFunctionImpl(cp);

        // Tạo danh sách phòng
        ArrayList<RoomType> roomTypeList = rt.getListRoomType(); // Lấy danh sách từ database
        request.setAttribute("roomTypeList", roomTypeList);
        
     // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/RoomView/listRoomType.jsp");
        dispatcher.forward(request, response);
    }
}
