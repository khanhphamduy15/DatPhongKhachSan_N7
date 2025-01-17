package dao;

import java.util.ArrayList;

import db.ConnectionPool;
import model.Room;

public interface RoomFunction<T> {
	boolean addRoom(Room room);

	boolean editRoom(Room room);

	boolean delRoom(Room room);

	Room getRoomById(String roomID);

	ArrayList<Room> getRoomByType(String roomTypeID);

	ArrayList<Room> getListRoom();

	boolean updateRoomStatus(String roomID, String status);

	ConnectionPool getCP();

	void releaseConnection();

}
