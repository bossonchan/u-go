package com.ugo.server.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ShoppingCartItem {
	@Id
	@GeneratedValue
	private Long id;
	
	private Date time;
	
	@ManyToOne
	@JoinColumn(name = "buyerId")
	private Buyer buyer;
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	private Item item;
	
	public ShoppingCartItem() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
