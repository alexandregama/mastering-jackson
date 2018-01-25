[markdown]
# 15 - Jackson Simple Deserialization with ObjectMapper

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
- **[Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)**
- [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to deserialize a **JSON object** into a Java object using Jackson!

With Jackson we can **deserialize** a JSON object into a **Java** object with the class **ObjectMapper**, the same class that [we used]() to **serialize** Java objects into a JSON.

## Deserializing a JSON String Object into a Java Object

We're going to use the **Tutorial** class again:

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

}
</pre>

Notice that we have a **constructor** receiving values for the fields. To deserialize a Tutorial object we **should have** a **default constructor**.

Now it's time to create the unit test to deserialize a JSON object:

<pre class="lang:java">
@Test
public void shouldDeserializeAJsonObjectIntoAJavaObject() throws Exception {
	String json = "{\"id\":1,\"title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

	Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

	assertEquals(tutorial.getId(), 1L, 0);
	assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
	assertEquals(tutorial.getLanguage(), "Java");
}
</pre>

As you can see we can use the **readValue()** method, passing a JSON string as an argument.

## Deserializing a JSON File into a Java Object

To deserialize a JSON file we just need to use the same **readValue()** method, but this time receiving a **File** object.

Let's create the file **tutorial.json**:

<pre class="lang:json">
{"id":1,"title":"CDI - How to use Decorators","language":"Java"}
</pre>

The unit test to deserialize this file could be:

<pre class="lang:java">
@Test
public void shouldDeserializeAJsonFileIntoAJavaObject() throws Exception {
	File file = new File("tutorial.json");

	Tutorial tutorial = new ObjectMapper().readValue(file, Tutorial.class);

	assertEquals(tutorial.getId(), 1L, 0);
	assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
	assertEquals(tutorial.getLanguage(), "Java");
}
</pre>

That's it for today folks!

Let's learn about **Custom JSON Deserialization** with **@JsonSetter** in the next part: [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)

Follow us to keep up to date! \o/
[/markdown]
