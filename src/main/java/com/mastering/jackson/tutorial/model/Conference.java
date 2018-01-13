package com.mastering.jackson.tutorial.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mastering.jackson.tutorial.one.CustomDateSerializer;

public class Conference {

	private Long id;
	
	private String name;

	private Map<String, String> presentations = new HashMap<>();
	
	@JsonSerialize(using = CustomDateSerializer.class)
	private LocalDate date;

	public Conference(Long id, String name, LocalDate date) {
		this.id = id;
		this.name = name;
		this.date = date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setPresentations(Map<String, String> presentations) {
		this.presentations = presentations;
	}

	@JsonAnyGetter
	public Map<String, String> getPresentations() {
		return presentations;
	}
	
}
