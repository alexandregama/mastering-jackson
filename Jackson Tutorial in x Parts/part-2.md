[markdown]
# 2 - Simple Serialization into JSON with Jackson

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts](https://blog.hackingcode.io/jackson-java-tutorial-news-posts-videos)

- [Part 1 - Intro to Jackson and Configuration with Maven and Gradle](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-config-maven)
- **[Part 2 - Simple Serialization into JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json)**
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
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to **Serialize** a simple POJO into a **JSON** using Jackson!

Let's start by serializing a simple **POJO** object in Java named **Tutorial**. With **serializing** I mean transform a Java **object** into a **JSON** using Jackson:

The **Tutorial** class:

<pre class="lang:java">
public class Tutorial {

	private Long id;

	private String title;

	private String language;

	public Tutorial(Long id, String title, String language) {
		this.id = id;
		this.title = title;
		this.language = language;
	}

	//the famous getters and setters here
}
</pre>

Our main goal is to **transform/parse/serialize** a Tutorial **object** into a **JSON** like this:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
</pre>

Great challenge, isn't it?

To do that we should use a class from Jackson called **ObjectMapper**. As you can guess from the name, this object will **map** an object in Java to something, in our case to a **JSON object**.

This class has a method called **writeValueAsString()** that can receives an object as an argument and generate a JSON object as a String.

![](maybe image here)

Let's see the complete code in a Unit Test with JUnit : )

<pre class="lang:java">
@Test
public void shouldSerializeAnObjectIntoAJSONWithJackson() throws Exception {
	Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

	ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(tutorial);

	System.out.println(json);

	assertEquals("{\"id\":1,\"title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}", json);
}
</pre>

Pretty simple, right?

Notice that I just **printed out** the JSON object to have the JSON being validated by you easier than asserting it with the assertEquals() method. In the following examples we're going to print out every result!

When you run the code, the output will be:

<pre class="lang:json">
{"id":1,"title":"CDI - How to use Decorators","language":"Java"}
</pre>

Great!

But notice that this JSON could be really huge and see it in just 1 line could be challenging. It's time to see a better way in the next post!

That's it for today folks!

Let's learn about how to **Print a pretty JSON** in the next part: [Part 3 - Printing a Pretty JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-pretty-json)

Follow us to keep up to date! \o/
[/markdown]
