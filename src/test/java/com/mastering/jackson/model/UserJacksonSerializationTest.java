package com.mastering.jackson.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserJacksonSerializationTest {

	@Test
	public void shouldDeserializeAJsonToUserObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper(); //from DataBinding dependency
		
		String json = "{\"name\":\"Alexandre Gama\", \"age\":29}";
		
		User user = mapper.readValue(json, User.class);
		
		assertThat(user.getName(), is(equalTo("Alexandre Gama")));
		assertThat(user.getAge(), is(equalTo(29)));
	}
	
	@Test
	public void shouldSerializaAUserObjectIntoJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		User user = new User("Alexandre Gama", 29);
		
		String json = mapper.writeValueAsString(user);
		
		assertThat(json, is(equalTo("{\"name\":\"Alexandre Gama\",\"age\":29}")));
	}
	
}
