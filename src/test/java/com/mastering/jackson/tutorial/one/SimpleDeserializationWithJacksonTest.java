package com.mastering.jackson.tutorial.one;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
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

	@Test
	public void shouldDeserializeAJsonObjectWithoutTitleIntoAJavaObject() throws Exception {
		String json = "{\"id\":1,\"language\":\"Java\"}";

		InjectableValues injectedValue = new InjectableValues.Std().addValue(String.class, "Jackson Complete Tutorial");
		Tutorial tutorial = new ObjectMapper()
			.reader(injectedValue)
			.forType(Tutorial.class)
			.readValue(json);

		assertEquals(tutorial.getId(), 1L, 0);
		assertEquals(tutorial.getTitle(), "Jackson Complete Tutorial");
		assertEquals(tutorial.getLanguage(), "Java");
	}

	@Test
	public void shouldDeserializeAJsonObjectThatDoesNotMatchWithTheJavaObject() throws Exception {
		String json = "{\"id\":1,\"special_title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

		Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

		assertEquals(tutorial.getId(), 1L, 0);
		assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
		assertEquals(tutorial.getLanguage(), "Java");
	}

	@Test
	public void testName() throws Exception {
		Map<String, String> presentations = new HashMap<>();
		presentations.put("Java Framework", "JUnit 5 - New Architecture");
		presentations.put("Java Language", "Java 9 - Working with Modules");
		presentations.put("JVM", "How to use GC in a smart way");

		JavaConference conference = new JavaConference(10L, "JavaOne", presentations);

		ObjectMapper objectMapper = new ObjectMapper();
		String prettyJson = objectMapper.writeValueAsString(conference);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldDeserializeAMapWithTheDefaultBehaviour() throws Exception {
		String json = "{\"id\":10,\"name\":\"JavaOne\",\"presentations\":"
				+ "{\"JVM\":\"How to use GC in a smart way\",\"Java Framework\":\"JUnit 5 - New Architecture\","
				+ "\"Java Language\":\"Java 9 - Working with Modules\"}}";

		ObjectMapper mapper = new ObjectMapper();
		JavaConference conference = mapper.readValue(json, JavaConference.class);

		Map<String, String> presentations = new HashMap<>();
		presentations.put("Java Framework", "JUnit 5 - New Architecture");
		presentations.put("Java Language", "Java 9 - Working with Modules");
		presentations.put("JVM", "How to use GC in a smart way");

		assertEquals(conference.getPresentations(), presentations);
	}

	@Test
	public void shouldDeserializeAJsonThatDoesNotMatchWithTheMap() throws Exception {
		String json = "{\"id\":10,\"name\":\"JavaOne\","
				+ "\"JVM\":\"How to use GC in a smart way\",\"Java Framework\":\"JUnit 5 - New Architecture\","
				+ "\"Java Language\":\"Java 9 - Working with Modules\"}";

		ObjectMapper mapper = new ObjectMapper();
		JavaConference conference = mapper.readValue(json, JavaConference.class);

		Map<String, String> presentations = new HashMap<>();
		presentations.put("Java Framework", "JUnit 5 - New Architecture");
		presentations.put("Java Language", "Java 9 - Working with Modules");
		presentations.put("JVM", "How to use GC in a smart way");

		assertEquals(conference.getPresentations(), presentations);
	}

	@Test
	public void shouldDeserializeADateFromJsonToAJavaLocalDate() throws Exception {
		String json = "{\"id\": 10, \"date\": \"2018-11-20\"}";

		ObjectMapper mapper = new ObjectMapper();
		DevOpsConference conference = mapper.readValue(json, DevOpsConference.class);

		LocalDate date = LocalDate.of(2018, 11, 20);

		assertTrue(conference.getDate().equals(date));
	}

}

class CustomLocalDateDeserialization extends StdDeserializer<LocalDate> {

	public CustomLocalDateDeserialization() {
		this(null);
	}

	protected CustomLocalDateDeserialization(Class<LocalDate> date) {
		super(date);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String stringDate = jsonParser.getText();

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(stringDate, pattern);
		return date;
	}

}

class DevOpsConference {

	private Long id;

	@JsonDeserialize(using = CustomLocalDateDeserialization.class)
	private LocalDate date;

	// Required by Jackson deserialization
	public DevOpsConference() {}

	public DevOpsConference(Long id, LocalDate date) {
		this.id = id;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}

class JavaConference {

	private Long id;

	private String name;

	private Map<String, String> presentations = new HashMap<>();

	public JavaConference() {}

	public JavaConference(Long id, String name, Map<String, String> presentations) {
		this.id = id;
		this.name = name;
		this.presentations = presentations;
	}

	@JsonAnySetter
	public void add(String key, String value) {
		presentations.put(key, value);
	}

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

	public Map<String, String> getPresentations() {
		return presentations;
	}

	public void setPresentations(Map<String, String> presentations) {
		this.presentations = presentations;
	}

	@Override
	public String toString() {
		return "JavaConference [id=" + id + ", name=" + name + ", presentations=" + presentations + "]";
	}

}
