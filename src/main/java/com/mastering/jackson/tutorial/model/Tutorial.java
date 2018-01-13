package com.mastering.jackson.tutorial.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"title", "id", "language"})
public class Tutorial {

	private Long id;
	
	private String title;
	
	private String language;
	
	public Tutorial(Long id, String title, String language) {
		this.id = id;
		this.title = title;
		this.language = language;
	}

	public Tutorial() {}

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

	public String getUsedLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
}
