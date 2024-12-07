package model;

import java.sql.Date;

public class Booking {
	private int bookingID;
	private int customerID;
	private String roomID;
	private Date createDate;
	private Date startDate;
	private Date endDate;
	private int guestNum;
	private String status;

	public Booking() {

	}

	public Booking(int bookingID, int customerID, String roomID, Date createDate, Date startDate, Date endDate, int guestNum,
			String status) {
		super();
		this.bookingID = bookingID;
		this.customerID = customerID;
		this.roomID = roomID;
		this.createDate = createDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.guestNum = guestNum;
		this.status = status;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public int getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
		
	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ", customerID=" + customerID + ", roomID=" + roomID + ", createDate="
				+ createDate + ", startDate=" + startDate + ", endDate=" + endDate + ", guestNum=" + guestNum + ", status="
				+ status + "]";
	}

}

	

