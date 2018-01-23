package com.mastering.jackson.tutorial.one;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mastering.jackson.model.Article;
import com.mastering.jackson.model.Status;
import com.mastering.jackson.tutorial.model.Category;
import com.mastering.jackson.tutorial.model.Conference;
import com.mastering.jackson.tutorial.model.Course;
import com.mastering.jackson.tutorial.model.Customer;
import com.mastering.jackson.tutorial.model.Framework;
import com.mastering.jackson.tutorial.model.JavaScript;
import com.mastering.jackson.tutorial.model.Language;
import com.mastering.jackson.tutorial.model.Tutorial;
import com.mastering.jackson.tutorial.model.VideoCourse;

public class SimpleSerializationTest {

	@Test
	public void shouldSerializeAnObjectIntoAJSONWithJackson() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(tutorial);

		System.out.println(json);

		assertEquals("{\"id\":1,\"title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}", json);
	}

	@Test
	public void shouldSerializeWithAPrettyJsonBeingCreatedWithJackson() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldPrintAPrettyJsonAtRuntime() throws Exception {
		boolean shouldPrintPretty = true;
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

		ObjectMapper mapper = new ObjectMapper();
		if (shouldPrintPretty) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		String prettyJson = mapper.writeValueAsString(tutorial);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeComposeObjectsWithJackson() throws Exception {
		Language java = new Language(1, "Java");
		Framework spring = new Framework(1L, "Spring", java);

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(spring);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeGetterMethodsByDefaultWithJackson() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldOrderJSONPropertyAccordingToTheJacksonConfiguration() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeJustFieldsWithJackson() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeThePropertyExactlyAsItIs() throws Exception {
		JavaScript javascript = new JavaScript("JavaScript",
				"function name() {return \"JavaScript Jasmine Framework\"}",
				"function name() {return \"JavaScript Jasmine Framework\"}");

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(javascript);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeTheJavaObjectWithACustomJSONContent() throws Exception {
		Customer customer = new Customer(1l, "Alexandre Gama");

		ObjectMapper mapper = new ObjectMapper();
		String prettyJson = mapper.writeValueAsString(customer);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeTheJSONObjectWithARootName() throws Exception {
		VideoCourse videoCourse = new VideoCourse(1L, "Java Jackson Framework", new Category("Java"));

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String prettyJson = mapper.writeValueAsString(videoCourse);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeWithACustomDatePattern() throws Exception {
		Conference conference = new Conference(10l, "JavaOne", LocalDate.of(2018, 11, 20));

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(conference);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeAHashMapObjectIntoAJson() throws Exception {
		Map<String, String> presentations = new HashMap<>();
		presentations.put("Java Framework", "JUnit 5 - New Architecture");
		presentations.put("Java Language", "Java 9 - Working with Modules");
		presentations.put("JVM", "How to use GC in a smart way");

		Conference conference = new Conference(10l, "JavaOne", LocalDate.of(2018, 11, 20));
		conference.setPresentations(presentations);

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(conference);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldIgnorePropertiesWithJacksonWhenSerializingToJSON() throws Exception {
		Course course = new Course(5L, "Spring 5 - REST API and Security", "Framework", "Java", LocalDate.of(2018, 02, 20));

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(course);

		System.out.println(prettyJson);
	}

	@Test
	public void shouldSerializeAnEnumObjectIntoJsonWithJackson() throws Exception {
		Article article = new Article(10L, "Java 8 - The Complete Tutorial", Status.FINISHED);

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(article);

		System.out.println(prettyJson);
	}

}