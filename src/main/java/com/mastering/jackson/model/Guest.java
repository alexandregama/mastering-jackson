package com.mastering.jackson.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class Guest {

	@JsonProperty(value = "id")
	private Long id;
	
	/*
	 * The name first_name will be used in the json string
	 */
	@JsonProperty(value = "first_name", required = true)
	private String firstName;
	
	@JsonProperty(value = "age")
	private int age;
	
	/*
	 * We must buil the non-arg constructor for Jackson
	 */
	@Deprecated // Jackson's eyes only
	Guest() {
	}
	
	public Guest(String firstName, int age) {
		this.firstName = firstName;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
