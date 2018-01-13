package com.mastering.jackson.tutorial.model;

import com.fasterxml.jackson.annotation.JsonValue;

public class Customer {

	private Long id;
	
	private String name;

	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@JsonValue
	public String customContent() {
		return "{\'custom_id\":" + this.id +",\"custom_name\":" + this.id + " - " + this.name +"}";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
