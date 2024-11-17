package ads.room;

import ads.objects.RoomObject;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface Room {
    boolean addRoom(RoomObject room);
    boolean editRoom(RoomObject room);
    boolean delRoom(RoomObject room);
    ArrayList<ResultSet> getRooms(String query);
    ResultSet getRoom(String roomId);
}
