package com.mastering.jackson.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GuestSerializationTest {

	@Test
	public void shouldDeserializaAJsonIntoGuestObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "{\"first_name\":\"Alexandre Gama\", \"age\":29}";
		
		Guest guest = mapper.readValue(json, Guest.class);
		
		assertThat(guest.getFirstName(), is(equalTo("Alexandre Gama")));
	}
	
	@Test
	public void shouldSerializaAGuestObjectIntoJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		Guest guest = new Guest("Alexandre Gama", 29);
		
		String json = mapper.writeValueAsString(guest);
		
		assertThat(json, is(equalTo("{\"first_name\":\"Alexandre Gama\",\"age\":29}")));
	}
	
}
