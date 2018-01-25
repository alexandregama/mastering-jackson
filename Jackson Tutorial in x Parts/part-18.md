[markdown]
# 18 - Jackson Deserialization with @JsonCreator Annotation

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
- **[Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)**
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to create a JSON **Custom Deserialization** with constructor using Jackson!

How does Jackson deal with a JSON deserialization when it doesn't match with the Java object?

Imagine the following JSON object:

<pre class="lang:json">
{
  "id" : 10,
  "special_title" : "Java 8 - The Complete Tutorial",
  "language" : "Java"
}
</pre>

Now we have the following Java class that will be used to deserialize the JSON object:

<pre class="lang:java">
public class Tutorial {

	private Long id;

	private String title;

	private String language;

	// Required by Jackson when Deserializing the JSON Object
	public Tutorial() {}

	public Tutorial(Long id, String title, String language) {
		this.id = id;
		this.title = title;
		this.language = language;
	}

  //getters and setters
}
</pre>

It's time to have a unit test to check if the deserialization is working:

<pre class="lang:java">
@Test
public void shouldDeserializeAJsonObjectThatDoesNotMatchWithTheJavaObject() throws Exception {
  String json = "{\"id\":1,\"special_title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

  Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

  assertEquals(tutorial.getId(), 1L, 0);
  assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
  assertEquals(tutorial.getLanguage(), "Java");
}
</pre>

If you run this code, you'll have the error:

</pre>
com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "special_title" (class com.mastering.jackson.tutorial.model.Tutorial), not marked as ignorable (3 known properties: "title", "id", "language"])
 at [Source: {"id":1,"special_title":"CDI - How to use Decorators","language":"Java"}; line: 1, column: 26] (through reference chain: com.mastering.jackson.tutorial.model.Tutorial["special_title"])
</pre>

The JSON object doesn't match with the Java class. Let's fix it!

## Customizing the JSON Deserialization with @JsonCreator

The **@JsonCreator** annotation allows us to customize a constructor to be used by Jackson when deserializing
a JSON object into a Java object

<pre class="lang:java">
public class Tutorial {

  @JsonCreator
	public Tutorial(@JsonProperty("id") Long id, @JsonProperty("special_title") String title, @JsonProperty("language") String language) {
		this.id = id;
		this.title = title;
		this.language = language;
	}

}
</pre>

As you can see,

- The **@JsonCreator** annotation is used in the class constructor
- The **@JsonProperty** annotation is used in the desired field, with the **same name** from JSON

That's it for today folks!

Let's learn about **Map JSON Deserialization** in the next part: [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)

Follow us to keep up to date! \o/
[/markdown]
