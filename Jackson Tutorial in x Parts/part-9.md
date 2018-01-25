[markdown]
# 9 - Serializing The Entire JSON Object with a Custom Value with Jackson

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
- **[Part 9 - Serializing The Entire JSON Object with a Custom Value with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-custom-serialization-to-json)**
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
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going how to create a **Custom Serialization** to a JSON using Jackson!

Sometimes we have the following challenge:

> The object being serialized by Jackson should generate a **custom JSON**, defined **previously**

To get this challenge clear, let's start by creating a **Customer** class:

<pre class="lang:java">
public class Customer {

	private Long id;

	private String name;

	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	//getters and setters as usual
}
</pre>

Now it's time to have a test to serialize a **customer** object into a JSON object:

<pre class="lang:java">
	@Test
	public void shouldSerializeTheJavaObjectWithACustomJSONContent() throws Exception {
		Customer customer = new Customer(1l, "Alexandre Gama");

		ObjectMapper mapper = new ObjectMapper();
		String prettyJson = mapper.writeValueAsString(customer);

		System.out.println(prettyJson);		
	}
</pre>

Without any surprises, the result will be:

<pre class="lang:json">
{"id":1,"name":"Alexandre Gama"}
</pre>

But if we want to change the content that will be returned by Jackson? If we want to generate a **custom JSON** object?

We should use the **@JsonValue** annotation in a method that has the custom logic to generate the JSON object:

<pre class="lang:java">
public class Customer {

	@JsonValue
	public String customContent() {
		return "{\"custom_id\":" + this.id +",\"custom_name\":" + this.id + " - " + this.name +"}";
	}

}
</pre>

As you can see, the method **customContent()** took the values of the **id** and **name** to create a customized response when a **customer** object is serialized!

The result will be:

<pre class="lang:json">
"{'custom_id\":1,\"custom_name\":1 - Alexandre Gama}"
</pre>

Pretty easy, isn't it?

That's it for today folks!

Let's learn about **JSON Root Serialization** in the next part: [Part 10 - Serializing a JSON Object with a Root with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-with-root)

Follow us to keep up to date! \o/
[/markdown]
