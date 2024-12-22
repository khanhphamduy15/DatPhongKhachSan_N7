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
 * Servlet implementation class AddRoom
 */

@WebServlet("/AddRoom")
@MultipartConfig
public class AddRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("AddRoom.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // Set request encoding to UTF-8
	    res.setContentType("text/html; charset=UTF-8"); // Set response encoding to UTF-8
	    ConnectionPool cp = new ConnectionPoolImpl();

	    // Lấy thông tin từ form
	    String rID = req.getParameter("roomID");
	    String rName = req.getParameter("roomName");
	    int rTypeID = Integer.parseInt(req.getParameter("roomTypeID"));
	    double price = Double.parseDouble(req.getParameter("price"));
	    int maxPerson = Integer.parseInt(req.getParameter("maxPerson"));
	    String status = req.getParameter("status");

	    // Xử lý upload file
	    Part filePart = req.getPart("roomImg");
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    String uploadPath = getServletContext().getRealPath("/uploads/");
	    
	    // Tạo thư mục uploads nếu chưa tồn tại
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdir();
	    }
	    System.out.println("Upload path: " + uploadPath);

	    // Lưu file vào thư mục uploads
	    filePart.write(uploadPath + File.separator + fileName);

	    // Tạo đối tượng Room
	    Room room = new Room(rID, rName, rTypeID, "uploads/" + fileName, price, maxPerson, status);

	    // Thêm vào DB
	    RoomFunction<Room> r = new RoomFunctionImpl(cp);
	    boolean isSuccess = r.addRoom(room);
	    // Điều hướng kết quả
	    if (isSuccess) {
	        res.sendRedirect(req.getContextPath() + "/Room?message=addSuccess");
	    } else {
	        res.sendRedirect(req.getContextPath() + "/Room?message=addFailed");
	    }
	}
}

