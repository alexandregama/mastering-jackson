[markdown]
# 7 - Ordering Properties in JSON with Jackson Serialization

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts](https://blog.hackingcode.io/jackson-java-tutorial-news-posts-videos)

- [Part 1 - Intro to Jackson and Configuration with Maven and Gradle](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-config-maven)
- [Part 2 - Simple Serialization into JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json)
- [Part 3 - Printing a Pretty JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-pretty-json)
- [Part 4 - Serializing Composed Java Objects with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-composed-java-object-to-json)
- [Part 5 - Serializing Getter Methods to JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-getter-methods-to-json)
- [Part 6 - Serializing Java Object into JSON using Fields with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-fields-to-json)
- **[Part 7 - Ordering Properties in JSON with Jackson Serialization](https://blog.hackingcode.io/jackson-java-tutorial-serialization-order-fields-to-json)**
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

In the today's post we're going how to serialize a **Order Properties** into a JSON using Jackson!

Do you remember our [JSON Tutorial object generated](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json) by Jackson serialization? Let's see it again:

<pre class="lang:json">
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
</pre>

But imagine that for some reason you need this generated JSON **ordered** in this way:

<pre class="lang:json">
{
  "title" : "CDI - How to use Decorators",
  "id" : 1,
  "usedLanguage" : "Java"
}
</pre>

The attribute **title** is coming before the attribute **id**

To achieve this goal we should use the **@JsonPropertyOrder** annotation, passing the desired order for the attributes:

<pre class="lang:java">
@JsonPropertyOrder({"title", "id", "language"})
public class Tutorial {

}
</pre>

The result will be as we expected!

<pre class="lang:json">
{
  "title" : "CDI - How to use Decorators",
  "id" : 1,
  "usedLanguage" : "Java"
}
</pre>

Pretty easy, isn't it?

That's it for today folks!

Let's learn about how to **Serialize Raw Content** in the next part: [Part 8 - Serializing the Raw Content in the JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-raw-content-to-json)

Follow us to keep up to date! \o/
[/markdown]
