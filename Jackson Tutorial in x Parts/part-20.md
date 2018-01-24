[markdown]
# 20 - Jackson Custom Deserialization with @JsonDeserialize

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts](https://blog.hackingcode.io/jackson-java-tutorial-news-posts-videos)

- [Part 1 - Intro to Jackson and Configuration with Maven and Gradle](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-config-maven)
- [Part 2 - Simple Serialization into JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json)
- [Part 3 - Printing a Pretty JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-pretty-json)
- [Part 4 - Serializing Composed Java Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-composed-java-object-to-json)
- [Part 5 - Serializing Getter Methods to JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-getter-methods-to-json)
- [Part 6 - Serializing Java Object into JSON using Fields with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-fields-to-json)
- [Part 7 - Ordering Properties in JSON with Jackson Serialization](https://blog.hackingcode.io/jackson-java-tutorial-serialization-order-fields-to-json)
- [Part 8 - Serializing the Raw Content in the JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-raw-content-to-json)
- [Part 9 - Serializing The Entire JSON Object with a Custom Value with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-custom-serialization-to-json)
- [Part 10 - Serializing a JSON Object with a Root with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-with-root)
- [Part 11 - JSON Custom Serializer with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-custom-serialization-to-json)
- [Part 12 - Serializing Map into JSON Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-map-to-json)
- [Part 13 - Ignoring Properties in JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-ignore-fields-to-json)
- [Part 14 - Serializing Enum Objects into a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-enum-to-json)
- [Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)
- [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- **[Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)**

In the today's post we're going to see how to create a JSON **Custom Deserialization** for a **LocalDate** using Jackson!

We have already seen a [Custom Serialization](https://blog.hackingcode.io/jackson-java-tutorial-custom-serialization-to-json) from a JSON to a Java object with Jackson.

Now it's time to see a Custom Deserialization. First of all, we should see the problem happening.

Imagine the following JSON object:

<pre class="lang:json">
{"id": 10, "date": "2018-11-20"}
</pre>

This JSON should be deserialized to a **DevOpsConference** object:

<pre class="lang:java">
class DevOpsConference {

	private Long id;

	private LocalDate date;

	// Required by Jackson deserialization
	public DevOpsConference() {}

	public DevOpsConference(Long id, LocalDate date) {
		this.id = id;
		this.date = date;
	}

   //getters and setters
}   
</pre>

To test the deserialization, let's create a simple unit test:

<pre class="lang:java">
@Test
public void shouldDeserializeADateFromJsonToAJavaLocalDate() throws Exception {
   String json = "{\"id\": 10, \"date\": \"2018-11-20\"}";

   ObjectMapper mapper = new ObjectMapper();
   DevOpsConference conference = mapper.readValue(json, DevOpsConference.class);

   LocalDate date = LocalDate.of(2018, 11, 20);

   assertTrue(conference.getDate().equals(date));
}
</pre>

But, if we run the test, we got the following error:

<pre highlight="false">int
com.fasterxml.jackson.databind.JsonMappingException: Can not instantiate value of type [simple type, class java.time.LocalDate] from String value ('2018-11-20'); no single-String constructor/factory method
 at [Source: {"id": 10, "date": "2018-11-20"}; line: 1, column: 20] (through reference chain: com.mastering.jackson.tutorial.one.DevOpsConference["date"])
</pre>

Jackson doesn't know how to deserialize the String **date** to a **LocalDate** because this is a complex object.

Now it's time to face the problem!

## Deserializing a JSON object with @JsonDeserialize Annotation

To have a custom deserialization we should use the **@JsonDeserialize** annotation. This annotation
receives a **Custom Deserializer**, that is a class that extends the **StdDeserializer**

So, let's create the custom class:

<pre class="lang:java">
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
</pre>

This is a simple class that:

- Extends the **StdDeserializer** class from Jackson
- Implements the method **deserialize()** that receives a JsonParser, containing our date in String
- Converts a String date into a LocalDate using **DateTimeFormatter** class

Now it's time to indicate to Jackson that this class should be used in the deserialization process:

<pre class="lang:java">
class DevOpsConference {

	@JsonDeserialize(using = CustomLocalDateDeserialization.class)
	private LocalDate date;

}   
</pre>

If you run the unit test again, everything is working as expected! \o/

That's it for today folks!

I really hope that this **Java Jackson Series** could be helpful to you! Thank you :)

Follow us to keep up to date! \o/
[/markdown]
