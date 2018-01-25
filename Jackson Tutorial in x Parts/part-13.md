[markdown]
# 13 - Ignoring Properties in the JSON with Jackson

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
- **[Part 13 - Ignoring Properties in JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-ignore-fields-to-json)**
- [Part 14 - Serializing Enum Objects into a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-enum-to-json)
- [Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)
- [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to **Ignore Properties** in the generated JSON using Jackson!

Sometimes you want to ignore a few Properties to not being serialized by Jackson into a JSON.

Let's create a class **Course**:

<pre class="lang:java">
public class Course {

	private Long id;

	private String title;

	private String language;

	private String category;

	private LocalDate date;

	public Course(Long id, String title, String language, String category, LocalDate date) {
		this.id = id;
		this.title = title;
		this.language = language;
		this.category = category;
		this.date = date;
	}

    //getters and setters as usual
}
</pre>

Now, we have the test to serialize a **Course** object into a JSON object:

<pre class="lang:java">
@Test
public void shouldIgnorePropertiesWithJacksonWhenSerializingToJSON() throws Exception {
	Course course = new Course(5L, "Spring 5 - REST API and Security", "Framework", "Java", LocalDate.of(2018, 02, 20));

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(course);

	System.out.println(prettyJson);
}
</pre>

The result will be the following JSON:

<pre class="lang:json">
{
  "id" : 5,
  "title" : "Spring 5 - REST API and Security",
  "language" : "Framework",
  "category" : "Java",
  "date" : {
    "year" : 2018,
    "month" : "FEBRUARY",
    "chronology" : {
      "id" : "ISO",
      "calendarType" : "iso8601"
    },
    "era" : "CE",
    "monthValue" : 2,
    "dayOfMonth" : 20,
    "dayOfYear" : 51,
    "dayOfWeek" : "TUESDAY",
    "leapYear" : false
  }
}
</pre>

But now we'd like to ignore the **Category** and **Date** properties. Let's use the **@JsonIgnoreProperties** on the class to indicate this properties:

<pre class="lang:java">
@JsonIgnoreProperties({"category", "date"})
public class Course {
}
</pre>

If you run the test again, you're going to see the following result:

<pre class="lang:json">
{
  "id" : 5,
  "title" : "Spring 5 - REST API and Security",
  "language" : "Framework"
}
</pre>

[Remember]() that we could also use the **@JsonIgnore** on the property directly:

<pre class="lang:java">
@JsonIgnore
private String language;
</pre>

That's it for today folks!

Let's learn about how to **Serialize Enum** objects in the next part: [Part 14 - Serializing Enum Objects into a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-enum-to-json)

Follow us to keep up to date! \o/
[/markdown]
