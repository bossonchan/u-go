package com.ugo.server.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

@Entity
public class Buyer implements Serializable{
	
	private static final long serialVersionUID = -3585847037915481984L;

	@GeneratedValue
	@Id
	private Long id;
	
	@Column(length=30, unique = true, nullable = false)
	private String username;
	
	@Column(length=16, nullable = false)
//	@JsonIgnore
	private String password;
	
	@Column(nullable = false)
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

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
