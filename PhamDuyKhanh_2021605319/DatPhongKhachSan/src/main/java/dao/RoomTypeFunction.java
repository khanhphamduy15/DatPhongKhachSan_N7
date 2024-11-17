package dao;

import java.util.ArrayList;
import db.ConnectionPool;
import model.RoomType;

public interface RoomTypeFunction {
	boolean addRoomType(RoomType roomType);

	boolean editRoomType(RoomType roomType);

	boolean delRoomType(RoomType roomType);

	ArrayList<RoomType> getListRoomType();

	ConnectionPool getCP();

	void releaseConnection();

}
