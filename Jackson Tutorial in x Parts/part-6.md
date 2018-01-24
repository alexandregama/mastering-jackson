[markdown]
# 6 - Serializing Java Object into JSON using Fields with Jackson

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts](https://blog.hackingcode.io/jackson-java-tutorial-news-posts-videos)

- [Part 1 - Intro to Jackson and Configuration with Maven and Gradle](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-config-maven)
- [Part 2 - Simple Serialization into JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json)
- [Part 3 - Printing a Pretty JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-pretty-json)
- [Part 4 - Serializing Composed Java Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-composed-java-object-to-json)
- [Part 5 - Serializing Getter Methods to JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-getter-methods-to-json)
- **[Part 6 - Serializing Java Object into JSON using Fields with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-fields-to-json)**
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

In the today's post we're going how to serialize **Fields** into a JSON using Jackson!

As we've seen in the [previous Jackson tutorial](https://blog.hackingcode.io/jackson-java-tutorial-serialize-getter-methods-to-json), Jackson uses **getter** methods by default to serialize objects into JSON!

To change this default behavior we just need to use the **@JsonProperty** annotation:

Let's configure the **language** attribute to be used in the serialization and let's give it a **custom name**:

<pre class="lang:java">
public class Tutorial {

	@JsonProperty("best_Language")
	private String language;

	public String getUsedLanguage() {
		return language;
	}
}
</pre>

If you run the code, the output will be:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java",
  "best_Language" : "Java"
}
</pre>

Hmm, sounds weird. Jackson will serialize **either** the **attribute** and the **getter** method.

We should **ignore** the getter method with the **@JsonIgnore** annotation:

<pre class="lang:java">
@JsonIgnore
public String getUsedLanguage() {
	return language;
}
</pre>

Now the output will be:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
</pre>

Great! But let's understand what just happened here!

## A little tricky code with Jackson!

Jackson serializes the attribute **and** the **getter** method because we have a **custom getter method**. Do you remember the **getUsedLanguage()** method?

If you change the name of the getter method to the **original** name, in this case **getLanguage()**, then Jackson will just serialize the attribute!

Another tip here is that you can use the **@JsonIgnore** in the getter method and in the attribute:

<pre class="lang:java">
public class Tutorial {

	@JsonIgnore
	private String language;

}
</pre>

## Another Tricky Situation with @JsonIgnore

If we have the **getter** and **setter** methods following the correct name of the attribute and use the **@JsonIgnore** in the **setter** method like this:

<pre class="lang:java">
public class Tutorial {

	public String getLanguage() {
		return language;
	}

	@JsonIgnore
	public void setLanguage(String language) {
		this.language = language;
	}

}
</pre>

The output will be:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators"
}
</pre>

Notice that Jackson does not use the **getLanguage()** method to serialize the attribute to a JSON. Sounds obvious, since we're ignoring the **setLanguage()** method!

But if we change the getter method to a **custom name**:

<pre class="lang:java">
public class Tutorial {

	public String getUsedLanguage() {
		return language;
	}

	@JsonIgnore
	public void setLanguage(String language) {
		this.language = language;
	}

}
</pre>

Then Jackson will serialize the getter method, even if we're ignoring the **setLanguage()** method:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
</pre>

## Configuring Class to Serialize Just Fields with Jackson

[As you know](), Jackson uses **getter** methods to serialize Java objects into a JSON. Did you try to remove all getter methods?

Let's try it out:

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

}
</pre>

The unit test to serialize a Tutorial object into a JSON:

<pre class="lang:java">
@Test
public void shouldSerializeJustFieldsWithJackson() throws Exception {
	Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(tutorial);

	System.out.println(prettyJson);
}
</pre>

If you execute this code, an error will be thrown in our face:

</pre>
com.fasterxml.jackson.databind.JsonMappingException: No serializer found for class com.mastering.jackson.tutorial.model.Tutorial and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )
</pre>

Sounds obvious, since we removed all the **getter** methods!

To serialize the object into a JSON using just the **fields** we can use the annotation **@JsonAutoDetect** on the class. This annotation allows us to configure which **Visibility** should be used by Jackson.

There is the Enum **Visibility** with a few visibility options:

- ANY
- NON_PRIVATE
- PROTECTED_AND_PUBLIC
- PUBLIC_ONLY
- NONE
- DEFAULT

We're going to use the **ANY** and **NONE** options for **fieldVisibility** and **getterVisibility** respectively:

<pre class="lang:java">
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE)
public class Tutorial {
}
</pre>

If you run the code again:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
</pre>

Great! Everything is working again and you can use just **Fields** in the Jackson serialization!

That's it for today folks!

Let's learn about how to use **Property Ordering** in the next part: [Part 7 - Ordering Properties in JSON with Jackson Serialization](https://blog.hackingcode.io/jackson-java-tutorial-serialization-order-fields-to-json)

Follow us to keep up to date! \o/
[/markdown]
