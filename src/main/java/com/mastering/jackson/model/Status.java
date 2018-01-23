package com.mastering.jackson.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFormat(shape = Shape.OBJECT)
public enum Status {

	STARTED(1, "Started"), IN_PROGRESS(2, "In Progress"), FINISHED(2, "Finished");

	private Integer code;

	private String value;

	private Status(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	@JsonIgnore
	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
