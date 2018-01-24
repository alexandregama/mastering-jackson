[markdown]
# 8 - Serializing the Raw Content in the JSON with Jackson

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts](https://blog.hackingcode.io/jackson-java-tutorial-news-posts-videos)

- [Part 1 - Intro to Jackson and Configuration with Maven and Gradle](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-config-maven)
- [Part 2 - Simple Serialization into JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json)
- [Part 3 - Printing a Pretty JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-pretty-json)
- [Part 4 - Serializing Composed Java Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-composed-java-object-to-json)
- [Part 5 - Serializing Getter Methods to JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-getter-methods-to-json)
- [Part 6 - Serializing Java Object into JSON using Fields with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-fields-to-json)
- [Part 7 - Ordering Properties in JSON with Jackson Serialization](https://blog.hackingcode.io/jackson-java-tutorial-serialization-order-fields-to-json)
- **[Part 8 - Serializing the Raw Content in the JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-raw-content-to-json)**
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
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going how to serialize a **Raw Content** into a JSON using Jackson!

Sometimes we have a content that should be serialized with its **raw value**. Imagine that in an object you have a **JavaScript** code that should be serialized with its **original content**.

Jackson allows us to keep this original content by using the **@JsonRawValue** annotation:

To represent our JavaScript code, it's sound good to create a class called...**JavaScript**:

<pre class="lang:java">
public class JavaScript {

	private String name;

	private String content;

	@JsonRawValue
	private String rawContent;

}
</pre>

Notice that we're using the **@JsonRawContent** in the **rawContent** attribute to keep its original data in the generated JSON.

The complete test could be:

<pre class="lang:java">
@Test
public void shouldSerializeThePropertyExactlyAsItIs() throws Exception {
	JavaScript javascript = new JavaScript("JavaScript",
			"function name() {return \"JavaScript Jasmine Framework\"}",
			"function name() {return \"JavaScript Jasmine Framework\"}");

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(javascript);

	System.out.println(prettyJson);
}
</pre>

And the output will be:

<pre class="lang:json">
{
  "name" : "JavaScript",
  "content" : "function name() {return \"JavaScript Jasmine Framework\"}",
  "rawContent" : function name() {return "JavaScript Jasmine Framework"}
}
</pre>

Notice that the **raw value** was kept as the **original value**. Keep in mind that this annotation could generate an invalid JSON, because it will keep the original content, that could have a not valid JSON.

Pretty easy, isn't it?

That's it for today folks!

Let's learn about **JSON Custom Serialization** with a custom value in the next part: [Part 9 - Serializing The Entire JSON Object with a Custom Value with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-custom-serialization-to-json)

Follow us to keep up to date! \o/
[/markdown]
