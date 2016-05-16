package com.ugo.server.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Buyer{
	@GeneratedValue
	@Id
	private Long id;
	
	private String username;
	@JsonIgnore
	private String password;
	
	private String phoneNumber;
	
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ShoppingCartItem> shoppingCartItems;
	
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Reservation> reservations;
	
	public Buyer() {}
	
	public Buyer(String username, String password, String phoneNumber) {
		this.username = username;
		this.password = password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
