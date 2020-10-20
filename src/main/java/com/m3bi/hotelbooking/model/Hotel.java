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
@Table(name="hotels",schema="hotelschma")
public class Hotel extends Status{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_hotel_seq")
	@SequenceGenerator(name="gen_hotel_seq",sequenceName="hotelschma.hotel-id-seq")
	@Column(name="id")
	private Long id;

	@Column(name="hotelname" , nullable= false)
	private String hotelName;

	private String place;

	public Long getId() {
		return id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
