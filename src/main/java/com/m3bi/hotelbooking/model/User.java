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
@Table(name="users", schema="hotelschma")
public class User extends Status{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq")
	@SequenceGenerator(name="gen_seq",sequenceName="hotelschma.user-id-seq",initialValue=100)
	@Column(name="id")
	private long id;
	
	@Column(nullable= false)
	private String name;
	
	private int bonus;

	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	

}
