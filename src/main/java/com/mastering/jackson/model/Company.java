package com.mastering.jackson.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder(value = {"id", "name", "employees"}) // Order that will be showed in JSON
public class Company {

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "employees")
	private List<Employee> employees;
	
	@Deprecated // Jackson's eyes only
	Company() {
	}
	
	public Company(String name, List<Employee> employees) {
		this.name = name;
		this.employees = employees;
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
