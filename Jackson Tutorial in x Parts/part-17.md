[markdown]
# 17 - Jackson Deserialization with @JacksonInject Annotation

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
- **[Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)**
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to create a JSON **Custom Injected Deserialization** using Jackson!

Imagine the situation where you should deserialize a JSON object into a Java object but there is a few informations that you don't have from JSON.

To illustrate this challenge, let's use the class **Tutorial**:

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

And then we should use the following JSON object:

<pre class="lang:json">
{"id":1,"language":"Java"}
</pre>

As you can see, we **don't have** the field **language** in the JSON object.

If you run the following code, you'll have an error:

<pre class="lang:java">
@Test
public void shouldDeserializeAJsonObjectWithoutTitleIntoAJavaObject() throws Exception {
  String json = "{\"id\":1,\"language\":\"Java\"}";

  Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

  assertEquals(tutorial.getId(), 1L, 0);
  assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
  assertEquals(tutorial.getLanguage(), "Java");
}
</pre>

The error indicates that we don't have the **title** field with the value.

## Deserializing a JSON Object with @JacksonInject

In this case we can use the **@JacksonInject** annotation, that allows us to **insert** a value
in the Java object, even if the JSON object doesn't have the value.

To create a Injected Value we should:

- Use the class abstract class **InjectableValues**
- Add the desired value to be injected in the deserialization
- Read the json to deserialize into a Java object

The complete code will be:

<pre class="lang:java">
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
</pre>

Examing the code:

<pre class="lang:java">
InjectableValues injectedValue = new InjectableValues.Std().addValue(String.class, "Jackson Complete Tutorial");
</pre>

This line **adds** a value typed as **String** to be used as a injected value.

That's it for today folks!

Let's learn about **Custom JSON Deserialization** with **@JsonCreator** in the next part: [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)

Follow us to keep up to date! \o/
[/markdown]
