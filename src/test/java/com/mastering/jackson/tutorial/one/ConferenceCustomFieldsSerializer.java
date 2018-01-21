package com.mastering.jackson.tutorial.one;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mastering.jackson.tutorial.model.Conference;

public class ConferenceCustomFieldsSerializer extends StdSerializer<Conference> {

	//This Default constructor is required by Jackson
	public ConferenceCustomFieldsSerializer() {
		this(null);
	}
	
	protected ConferenceCustomFieldsSerializer(Class<Conference> conference) {
		super(conference);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Conference value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		gen.writeEndObject();
	}

}
