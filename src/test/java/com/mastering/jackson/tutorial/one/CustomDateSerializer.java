package com.mastering.jackson.tutorial.one;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<LocalDate> {
	
	private static final long serialVersionUID = 1L;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	//This Default constructor is required by Jackson
	public CustomDateSerializer() {
		this(null);
	}

	protected CustomDateSerializer(Class<LocalDate> date) {
		super(date);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String formattedDate = formatter.format(value);
		
		gen.writeString(formattedDate);
	}

}
