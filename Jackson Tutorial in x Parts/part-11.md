[markdown]
# 11 - JSON Custom Serializer with Jackson

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
- **[Part 11 - JSON Custom Serializer with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-custom-serialization-to-json)**
- [Part 12 - Serializing Map into JSON Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-map-to-json)
- [Part 13 - Ignoring Properties in JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-ignore-fields-to-json)
- [Part 14 - Serializing Enum Objects into a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-enum-to-json)
- [Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)
- [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to create a **Custom Serialization** for LocalDate using Jackson!

In this example, we're going to see the **Conference** class that has a **LocalDate**, from Java 8, to be serialized.

Let's create the class:

<pre class="lang:java">
public class Conference {

	private Long id;

	private String name;

	private LocalDate date;

	public Conference(Long id, String name, LocalDate date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}

    //getters and setters as usual
}
</pre>

It's time to create a method do **serialize** into JSON a **Conference** object:

<pre class="lang:java">
@Test
public void shouldSerializeWithACustomDatePattern() throws Exception {
	Conference conference = new Conference(10l, "JavaOne", LocalDate.of(2018, 11, 20));

	ObjectMapper mapper = new ObjectMapper();
	String prettyJson = mapper.writeValueAsString(conference);

	System.out.println(prettyJson);		
}
</pre>

The result will be:

<pre class="lang:json">
{
  "id" : 10,
  "name" : "JavaOne",
  "date" : {
    "year" : 2018,
    "month" : "NOVEMBER",
    "chronology" : {
      "id" : "ISO",
      "calendarType" : "iso8601"
    },
    "era" : "CE",
    "monthValue" : 11,
    "dayOfMonth" : 20,
    "dayOfYear" : 324,
    "dayOfWeek" : "TUESDAY",
    "leapYear" : false
  }
}
</pre>

So, as you can see, Jackson tries its best when serializing a LocalDate into a JSON object. But the result may not be as you was expecting:

<pre class="lang:json">
"monthValue" : 11,
"dayOfMonth" : 20,
"dayOfYear" : 324,
"dayOfWeek" : "TUESDAY",
"leapYear" : false
</pre>

In our case, we'd like to have a data being printed out like this:

<pre class="lang:json">
"date" : "2018-11-20"
</pre>

To reach this goal we can create a Custom Serializer, to teach to Jackson how a data should be serialized.

## Creating a CustomDateSerializer

It's time to create the **CustomDateSerializer**. This class should:

- Extends the abstract class **StdSerializer**
- Implements the **serialize** method, that will receive the **LocalDate** to be formatted
- Overrides 2 **constructors** from the abstract class

The abstract class **StdSerializer** is the recommend class to create Standard and Custom serializers. Many important Jackson's classes extends this class, like **JsonValueSerializer**, **ByteArraySerializer**, **RawSerializer** and much more!

Let's jump into code:

<pre class="lang:java">
public class CustomDateSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = 1L;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public CustomDateSerializer() {
		this(null);
	}

	protected CustomDateSerializer(Class<LocalDate> date) {
		super(date);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {		
		String formattedDate = formatter.format(value);

		gen.writeString(formattedDate);
	}

}
</pre>

The code is simple. As you can see, we just used the **DateTimeFormatter** to format the **LocalDate** object.

After that, we just need to call the **writeString()** method that will generate the JSON with the formatted date.

Now it's time to use the new Custom Serializer! To do that, we should use the **@JsonSerialize** annotation

<pre class="lang:java">
public class Conference {

	@JsonSerialize(using = CustomDateSerializer.class)
	private LocalDate date;

}
</pre>

The result will be:

<pre class="lang:json">
2018-11-20
{
  "id" : 10,
  "name" : "JavaOne",
  "date" : "2018-11-20"
}
</pre>

## Custom Serializer on the Class

You can create a Custom Serializer to be used by the class, not just by the attribute.

Now, we;d like to generate the following JSON object when serializing a **Conference** object:

<pre class="lang:json">
{
  "id" : 10,
  "name" : "JavaOne"
}
</pre>

The Custom Serializer will have the following code:

<pre class="lang:java">
public class ConferenceCustomFieldsSerializer extends StdSerializer<Conference> {

	//This Default constructor is required by Jackson
	public ConferenceCustomFieldsSerializer() {
		this(null);
	}

	protected ConferenceCustomFieldsSerializer(Class<Conference> conference) {
		super(conference);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Conference value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		gen.writeEndObject();
	}

}
</pre>

As you can see, we used a few methods from the **JsonGenerator** object.

Now we just need to use this custom serializer on the class, like this:

<pre class="lang:java">
@JsonSerialize(using = ConferenceCustomFieldsSerializer.class)
public class Conference {
}
</pre>

That's it for today folks!

Let's learn about **Map JSON Serialization** in the next part: [Part 12 - Serializing Map into JSON Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-map-to-json)

Follow us to keep up to date! \o/
[/markdown]
