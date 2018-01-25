package com.mastering.jackson.tutorial.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "video_course")
public class VideoCourse {

	private Long id;
	
	private String title;
	
	private Category category;

	public VideoCourse(Long id, String title, Category category) {
		this.id = id;
		this.title = title;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
