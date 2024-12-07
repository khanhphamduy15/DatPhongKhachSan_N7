package model;

public class RoomType {
	private int roomTypeID;
	private String roomTypeName;
	private String description;
	
	public RoomType() {
	}
	
	

	public RoomType(int roomTypeID, String roomTypeName, String description) {
		super();
		this.roomTypeID = roomTypeID;
		this.roomTypeName = roomTypeName;
		this.description = description;
	}
	public RoomType( String roomTypeName, String description) {
		super();
		this.roomTypeName = roomTypeName;
		this.description = description;
	}

	public int getRoomTypeID() {
		return roomTypeID;
	}

	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "RoomType [roomTypeID=" + roomTypeID + ", roomTypeName=" + roomTypeName + ", description=" + description
				+ "]";
	}
	
}
