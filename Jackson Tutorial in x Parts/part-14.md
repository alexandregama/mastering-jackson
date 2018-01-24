[markdown]
# 14 - Serializing Enum Objects into a JSON with Jackson

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
- **[Part 14 - Serializing Enum Objects into a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-enum-to-json)**
- [Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)
- [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to serialize an **Enum** object into a JSON using Jackson!

Jackson serializes **Enum** objects into a **JSON** by default. Let's check it out!

First of all, we should create the **Enum**:

<pre class="lang:java">
public enum Status {

	STARTED(1, "Started"), IN_PROGRESS(2, "In Progress"), FINISHED(2, "Finished");

	private Integer code;

	private String value;

	private Status(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
</pre>

Now it's time to use this Enum in the **Article** class:

<pre class="lang:java">
public class Article {

	private Long id;

	private String title;

	private Status status;

	public Article(Long id, String title, Status status) {
		this.id = id;
		this.title = title;
		this.status = status;
	}

	//getters and setters as usual
}
</pre>

And then let's create the unit test to serialize an **Article** object into a **JSON** object with Jackson:

<pre class="lang:java">
@Test
public void shouldSerializeAnEnumObjectIntoJsonWithJackson() throws Exception {
	Article article = new Article(10L, "Java 8 - The Complete Tutorial", Status.FINISHED);

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(article);

	System.out.println(prettyJson);
}
</pre>

If you run this code, you'll get the following result:

<pre class="lang:json">
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : "FINISHED"
}
</pre>

Great!

But wait! Our **Enum** has a **code** and a **value**:

<pre class="lang:java">
STARTED(1, "Started"), IN_PROGRESS(2, "In Progress"), FINISHED(2, "Finished");
</pre>

But it could be really great if we generate the JSON as the following, with the code and status from the Enum!

<pre class="lang:json">
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : {
    "code" : 2,
    "value" : "Finished"
  }
}
</pre>

Let's see how!

## Serializing Enum Objects with @JsonFormat

If you're using Jackson 2.1.2 or above you can use the great annotation **@JsonFormat** on the Enum:

<pre class="lang:java">
@JsonFormat(shape = Shape.OBJECT)
public enum Status {
}
</pre>

With just this configuration, if you run the code again you'll have the expected result :)

<pre class="lang:json">
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : {
    "code" : 2,
    "value" : "Finished"
  }
}
</pre>

## Serializing Enum Objects with @JsonValue

You can serialize an Enum object with the **@JsonValue** annotation. But as [we've already studied]() this annotation, you can use it to serialize a custom value to the entire Class, or in this case, an Enum.

So, you can just use this annotation in 1 **getter** method in the Enum.

Obviously we're curious, so let's use this annotation in both **getter** method on the Enum:

<pre class="lang:java">
public enum Status {

	@JsonValue
	public Integer getCode() {
		return code;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

}
</pre>

if you run the unit test again, an error will be thrown in our face:

<pre highlight="false">int
com.fasterxml.jackson.databind.JsonMappingException: Problem with definition of [AnnotedClass com.mastering.jackson.model.Status]: Multiple value properties defined ([method com.mastering.jackson.model.Status#getValue(0 params)] vs [method com.mastering.jackson.model.Status#getCode(0 params)])
</pre>

So, let's change our code to just serialize a JSON object with the **status** from Enum:

<pre class="lang:java">
public enum Status {

	public Integer getCode() {
		return code;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

}
</pre>

if you run the code again, you will have the **status** being printed out:

<pre class="lang:json">
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : "Finished"
}
</pre>

## Ignoring Fields in the Enum Serialization into a JSON with Jackson

We can ignore field and methods in the Enum object by just using the **@JsonIgnore** annotation:

<pre class="lang:java">
@JsonFormat(shape = Shape.OBJECT)
public enum Status {

	@JsonIgnore
	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
</pre>

If you run the unit test, you'll have the generated JSON:

<pre class="lang:json">
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : {
    "value" : "Finished"
  }
}
</pre>

That's it for today folks!

Let's learn about **JSON Deserialization** in the next part: [Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)

Follow us to keep up to date! \o/
[/markdown]
