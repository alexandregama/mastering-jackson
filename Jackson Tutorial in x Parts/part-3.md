[markdown]
# 3 - Printing a Pretty JSON with Jackson

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts](https://blog.hackingcode.io/jackson-java-tutorial-news-posts-videos)

- [Part 1 - Intro to Jackson and Configuration with Maven and Gradle](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-config-maven)
- [Part 2 - Simple Serialization into JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json)
- **[Part 3 - Printing a Pretty JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-pretty-json)**
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
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to print out a **pretty JSON** with Jackson!

As [we've seen in the previous post](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json), if we use a basic Jackson serialization, our JSON object will be printed out in just 1 line!

## Pretty JSON with ObjectWriter from Jackson

To print a **pretty JSON** with Jackson we just need to use an object called **ObjectWriter** from Jackson.

To have this class we should call the method **writerWithDefaultPrettyPrinter()** from the **ObjectMapper** class.

The complete code could be:

<pre class="lang:java">
@Test
public void shouldSerializeWithAPrettyJsonBeingCreatedWithJackson() throws Exception {
	Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(tutorial);

	System.out.println(prettyJson);
}
</pre>

If you run this code, the output will be:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
</pre>

Cool! Now we have a **pretty JSON** being printed out \o/

## Choosing to Print a Pretty JSON at Runtime

Imagine that you have the situation:

> Depending on a condition, you should print a **pretty** JSON or not, at Runtime!

To reach this goal, we should **enable** the pretty printer in the **ObjectMapper** object by using the following code:

<pre class="lang:java">
mapper.enable(SerializationFeature.INDENT_OUTPUT);
</pre>

The method **enable()** allows us to enable a few features when generating/parsing a JSON.

The complete code could be:

<pre class="lang:java">
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
</pre>

Just test the boolean variable **shouldPrintPretty** with true and false to see the result : )

That's it for today folks!

Let's learn how to **Serialize Composed Objects** in the next part: [Part 4 - Serializing Composed Java Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-composed-java-object-to-json)

Follow us to keep up to date! \o/
[/markdown]
