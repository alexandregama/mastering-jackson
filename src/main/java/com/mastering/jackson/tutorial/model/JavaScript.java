package com.mastering.jackson.tutorial.model;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class JavaScript {

	private String name;
	
	private String content;
	
	@JsonRawValue
	private String rawContent;

	public JavaScript(String name, String content, String rawContent) {
		this.name = name;
		this.content = content;
		this.rawContent = rawContent;
	}
	
	public JavaScript() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRawContent() {
		return rawContent;
	}
	
	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}
	
}
