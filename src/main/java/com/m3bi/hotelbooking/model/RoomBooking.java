package com.m3bi.hotelbooking.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.m3bi.hotelbooking.common.Status;

@Entity
@Table(name="roombooking", schema="hotelschma")
public class RoomBooking extends Status{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq")
	@SequenceGenerator(name="gen_seq",sequenceName="hotelschma.booking-id-seq",initialValue=100)
	@Column(name="id")
	private Long id;
	
	private Long userid;
	
	private Long hotelid;
	
	private Long roomid;
	
	@Column(name="no_of_rooms")
	private int noOfRooms;
	
	@Column(name="totalamount")
	private double totalAmount;
	
	@Column(name="bookingdate")
	private LocalDateTime bookingDate;
	
	@Column(name="status")
	private String bookingstatus;

	public Long getId() {
		return id;
	}

	

	public double getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}



	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getHotelid() {
		return hotelid;
	}

	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}

	public Long getRoomid() {
		return roomid;
	}

	public void setRoomid(Long roomid) {
		this.roomid = roomid;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	
	public String getBookingstatus() {
		return bookingstatus;
	}



	public void setBookingstatus(String bookingstatus) {
		this.bookingstatus = bookingstatus;
	}



	public int getNoOfRooms() {
		return noOfRooms;
	}



	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	
	

}
