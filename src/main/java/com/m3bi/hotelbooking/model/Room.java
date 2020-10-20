package com.m3bi.hotelbooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.m3bi.hotelbooking.common.Status;

@Entity
@Table(name="rooms", schema="hotelschma")
public class Room extends Status{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq")
	@SequenceGenerator(name="gen_seq",sequenceName="hotelschma.room-id-seq",initialValue=100)
	@Column(name="id")
	private Long id;
	
	@Column(name="hotelid")
	private Long hotelid;
	
	@Column(name="roomtype")
	private String roomType;
	
	private double price;
	
	@Column(name="roomcount")
	private int roomCount;
	
	@Column(name="availablerooms")
	private int availableRooms;

	public Long getId() {
		return id;
	}
	
	

	public Long getHotelid() {
		return hotelid;
	}



	public void setHotelid(Long hotelid) {
		this.hotelid = hotelid;
	}



	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public int getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}
	
	
}
