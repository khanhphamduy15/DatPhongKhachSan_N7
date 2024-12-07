package dao;

import java.util.ArrayList;
import db.ConnectionPool;
import model.RoomType;

public interface RoomTypeFunction<T> {
	boolean addRoomType(RoomType roomType);

	boolean editRoomType(RoomType roomType);

	boolean delRoomType(RoomType roomType);
	
	boolean delRoomTypeByID(int roomTypeID);

	T getRoomTypeById(int roomTypeID);

	ArrayList<RoomType> getListRoomType();

	ConnectionPool getCP();

	void releaseConnection();

}
