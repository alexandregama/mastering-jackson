package com.mastering.jackson.tutorial.model;

public class Book {

	private String LanguageCode;

	public Book() {}

	public Book(String languageCode) {
		this.LanguageCode = languageCode;
	}

	public String getLanguageCode() {
		return LanguageCode;
	}

	public void setLanguageCode(String languageCode) {
		LanguageCode = languageCode;
	}

}
