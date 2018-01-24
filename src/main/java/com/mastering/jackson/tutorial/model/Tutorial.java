package com.mastering.jackson.tutorial.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Tutorial {

	private Long id;

	private String title;

	private String language;

	// Required by Jackson when Deserializing the JSON Object
	public Tutorial() {}

	@JsonCreator
	public Tutorial(@JsonProperty("id") Long id, @JsonProperty("special_title") String title, @JsonProperty("language") String language) {
		this.id = id;
		this.title = title;
		this.language = language;
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

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", language=" + language + "]";
	}

}
