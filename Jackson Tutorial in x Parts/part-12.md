[markdown]
# 12 - Serializing Map into JSON Objects with Jackson

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
- **[Part 12 - Serializing Map into JSON Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-map-to-json)**
- [Part 13 - Ignoring Properties in JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-ignore-fields-to-json)
- [Part 14 - Serializing Enum Objects into a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-enum-to-json)
- [Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)
- [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to serialize a **Java Map** object into a JSON using Jackson!

As you have seen in the [previous tutorial](), let's use the **Conference** class again, but now with the attribute **presentations**, that is a **HashMap**:

<pre class="lang:java">
public class Conference {

	private Long id;

	private String name;

	private Map<String, String> presentations = new HashMap<>();

}
</pre>

The simple test to serialize a **Conference** object into a JSON could be:

<pre class="lang:java">
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
</pre>

And the result will be the following JSON being generated:

<pre class="lang:json">
{
  "id" : 10,
  "name" : "JavaOne",
  "presentations" : {
    "JVM" : "How to use GC in a smart way",
    "Java Framework" : "JUnit 5 - New Architecture",
    "Java Language" : "Java 9 - Working with Modules"
  },
  "date" : "2018-11-20"
}
</pre>

Jackson will serialize a Java Map by default :)

But if we want to have **each key and value as a line** in the main JSON, like this?

<pre class="lang:json">
{
  "id" : 10,
  "name" : "JavaOne",
  "date" : "2018-11-20",
  "JVM" : "How to use GC in a smart way",
  "Java Framework" : "JUnit 5 - New Architecture",
  "Java Language" : "Java 9 - Working with Modules"
}
</pre>

Then, we just need to use the **@JsonAnyGetter** annotation in the **Map** attribute:

<pre class="lang:java">
@JsonAnyGetter
public Map<String, String> getPresentations() {
	return presentations;
}
</pre>

And the result will be the JSON object with each key and value being a new line.

That's it for today folks!

Let's learn about how to **Ignore JSON Properties** in the next part: [Part 13 - Ignoring Properties in JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-ignore-fields-to-json)

Follow us to keep up to date! \o/
[/markdown]
