package com.mastering.jackson.tutorial.one;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastering.jackson.tutorial.model.Tutorial;

public class SimpleDeserializationWithJacksonTest {

	@Test
	public void shouldDeserializeAJsonObjectIntoAJavaObject() throws Exception {
		String json = "{\"id\":1,\"title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

		Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

		assertEquals(tutorial.getId(), 1L, 0);
		assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
		assertEquals(tutorial.getLanguage(), "Java");
	}

	@Test
	public void shouldDeserializeAJsonFileIntoAJavaObject() throws Exception {
		File file = new File("tutorial.json");

		Tutorial tutorial = new ObjectMapper().readValue(file, Tutorial.class);

		assertEquals(tutorial.getId(), 1L, 0);
		assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
		assertEquals(tutorial.getLanguage(), "Java");
	}

	@Test
	public void shouldDeserializeCustomJsonObjectIntoAJavaObject() throws Exception {
		String json = "{\"special_id\":1,\"special_title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

		Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

		assertEquals(tutorial.getId(), 1L, 0);
		assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
		assertEquals(tutorial.getLanguage(), "Java");
	}

}
