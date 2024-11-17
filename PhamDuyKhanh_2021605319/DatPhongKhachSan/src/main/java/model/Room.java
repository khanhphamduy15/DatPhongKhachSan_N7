package model;

public class Room {
	private String roomID;
	private String roomName;
	private int roomTypeID;
	private String roomImg;
	private double price;
	private String description;
	private int maxPerson;
	private String status;

	public Room() {
	}

	public Room(String roomID, String roomName, int roomTypeID, String roomImg, double price, String description, int maxPerson,
			String status) {
		super();
		this.roomID = roomID;
		this.roomName = roomName;
		this.roomTypeID = roomTypeID;
		this.roomImg = roomImg;
		this.price = price;
		this.description = description;
		this.maxPerson = maxPerson;
		this.status = status;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomTypeID() {
		return roomTypeID;
	}

	public void setRoomTypeID(int roomTypeID) {
		this.roomTypeID = roomTypeID;
	}
	

	public String getRoomImg() {
		return roomImg;
	}

	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", roomName=" + roomName + ", roomTypeID=" + roomTypeID + ", roomImg="
				+ roomImg + ", price=" + price + ", description=" + description + ", maxPerson=" + maxPerson
				+ ", status=" + status + "]";
	}

	
}
