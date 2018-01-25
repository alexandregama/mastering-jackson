[markdown]
# 16 - Jackson Deserialization with @JsonSetter Annotation

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
- **[Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)**
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to create a JSON **Custom Deserialization** with **setter methods** using Jackson!

Sometimes your JSON doesn't match with the **Fields** on the class.

Imagine the following JSON:

<pre class="lang:json">
{
 "special_id":1,
 "special_title":"CDI - How to use Decorators",
 "language":"Java"
}
</pre>

The **Tutorial** class will be used to deserialize this JSON object:

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
</pre>

As you can see, we have 2 attributes that doesn't match with the fields in the class:

- special_id
- special_title

Let's try to run the unit test to deserialize the JSON object:

<pre class="lang:java">
@Test
public void shouldDeserializeCustomJsonObjectIntoAJavaObject() throws Exception {
	String json = "{\"special_id\":1,\"special_title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

	Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

	assertEquals(tutorial.getId(), 1L, 0);
	assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
	assertEquals(tutorial.getLanguage(), "Java");
}
</pre>

If you run this code, you'll get the error:

<pre highlight="false">int
com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "special_id" (class com.mastering.jackson.tutorial.model.Tutorial), not marked as ignorable (3 known properties: "title", "id", "language"])
 at [Source: {"special_id":1,"special_title":"CDI - How to use Decorators","language":"Java"}; line: 1, column: 16] (through reference chain: com.mastering.jackson.tutorial.model.Tutorial["special_id"])
</pre>

We need to teach Jackson how it can deserialize the **special_id** in the **id** for example. To do that, we just need to use the **@JsonSetter** annotation in the **setter** method:

<pre class="lang:java">
@JsonSetter("special_id")
public void setId(Long id) {
	this.id = id;
}

@JsonSetter("special_title")
public void setTitle(String title) {
	this.title = title;
}
</pre>

If you run the code again, everything works fine! :)

That's it for today folks!

Let's learn about **Custom JSON Deserialization** with @JsonInject in the next part: [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)

Follow us to keep up to date! \o/
[/markdown]
