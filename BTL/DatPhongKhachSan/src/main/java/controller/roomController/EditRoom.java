package controller.roomController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.RoomFunction;
import dao.RoomFunctionImpl;
import db.ConnectionPool;
import db.ConnectionPoolImpl;
import model.Room;

/**
 * Servlet implementation class EditRoom
 */
@WebServlet("/EditRoom")
@MultipartConfig
public class EditRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
		response.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
		String rid = request.getParameter("roomID");
		
		ConnectionPool cp = new ConnectionPoolImpl();
        RoomFunction<Room> m = new RoomFunctionImpl(cp);
        
        if (rid != null) {        	
        	String rt_id = rid;
        	Room room = m.getRoomById(rt_id);
        	request.setAttribute("RoomL", room);
        }
        request.getRequestDispatcher("RoomView/EditRoom.jsp").forward(request, response);
        response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        ConnectionPool cp = new ConnectionPoolImpl();
        String rID = req.getParameter("roomID");
        String rName = req.getParameter("roomName");
        int rTypeID = Integer.parseInt(req.getParameter("roomTypeID"));
        double price = Double.parseDouble(req.getParameter("price"));
        int maxPerson = Integer.parseInt(req.getParameter("maxPerson"));
        String status = req.getParameter("status");
        
        RoomFunctionImpl roomFunction = new RoomFunctionImpl(cp);
        
        // Xử lý upload ảnh
        String fileName = null;
        Part filePart = req.getPart("roomImg");
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("/uploads/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            filePart.write(uploadPath + File.separator + fileName);
        }
        
        Room room = new Room();
        room.setRoomID(rID);
        room.setRoomName(rName);
        room.setRoomTypeID(rTypeID);
        room.setRoomImg(fileName != null ? "uploads/" + fileName : null); // Cập nhật đường dẫn ảnh
        room.setPrice(price);
        room.setMaxPerson(maxPerson);
        room.setStatus(status);

        boolean isSuccess = roomFunction.editRoom(room);

        if (isSuccess) {
            res.sendRedirect(req.getContextPath() + "/Room?message=updateSuccess");
        } else {
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

}
