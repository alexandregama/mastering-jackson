package com.mastering.jackson.tutorial.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"category", "date"})
public class Course {

	private Long id;
	
	private String title;
	
	@JsonIgnore
	private String language;
	
	private String category;
	
	private LocalDate date;
	
	public Course(Long id, String title, String language, String category, LocalDate date) {
		this.id = id;
		this.title = title;
		this.language = language;
		this.category = category;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
