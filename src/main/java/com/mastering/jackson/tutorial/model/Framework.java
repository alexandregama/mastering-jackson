package com.mastering.jackson.tutorial.model;

public class Framework {

	private Long id;
	
	private String name;
	
	private Language language;

	public Framework(Long id, String name, Language language) {
		this.id = id;
		this.name = name;
		this.language = language;
	}
	
	public Framework() {}

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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
