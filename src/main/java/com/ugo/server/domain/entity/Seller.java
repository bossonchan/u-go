package com.ugo.server.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seller implements Serializable{
	private static final long serialVersionUID = -3794402240715113475L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
//	@JsonIgnore
	private String password;
	private String identity;
	private String phoneNumber;
	
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Item> items;
	
	public Seller() {}
	
	public Seller(String username, String password, String identity, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.identity = identity;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
