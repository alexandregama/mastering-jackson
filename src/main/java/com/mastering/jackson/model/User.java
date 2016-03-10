package com.mastering.jackson.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/*
 * This is necessary because we don't want to include Null objects into the JSON generated
 */
@JsonInclude(value = Include.NON_NULL)
public class User {

	private Long id;

	private String name;
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	private int age;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
