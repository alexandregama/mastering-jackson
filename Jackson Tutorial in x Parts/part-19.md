[markdown]
# 19 - Deserializing Map from a JSON with Jackson

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
- [Part 14 - Serializing Enum Objects into a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialize-enum-to-json)
- [Part 15 - Jackson Deserialization with ObjectMapper](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-object-mapper-from-json)
- [Part 16 - Jackson Custom Deserialization with @JsonSetter](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-field)
- [Part 17 - Jackson Custom Deserialization with @JacksonInject](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-injected-value)
- [Part 18 - Jackson Custom Deserialization with @JsonCreator](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-custom-java-constructor)
- **[Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)**
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see how to deserialize a **JSON with a Map** into a Java object using Jackson!

Imagine the following JSON object with a **Map** that should be deserialized:

<pre class="lang:json">
{
   "id" : 10,
   "name" : "JavaOne",
   "presentations" : {
    "JVM" : "How to use GC in a smart way",
    "Java Framework" : "JUnit 5 - New Architecture",
    "Java Language" : "Java 9 - Working with Modules"
   }
}
</pre>

This JSON should be deserialized to a **JavaConference** object. Let's see this class:

<pre class="lang:java">
class JavaConference {

   private Long id;

   private String name;

   private Map<String, String> presentations = new HashMap<>();

   public JavaConference() {}

   public JavaConference(Long id, String name, Map<String, String> presentations) {
   	this.id = id;
   	this.name = name;
   	this.presentations = presentations;
   }

   //getters and setters
}
</pre>

Great!

It's tome to deserialize the JSON object! We could have the following test code:

<pre class="lang:java">
@Test
public void shouldDeserializeAMapWithTheDefaultBehaviour() throws Exception {
   String json = "{\"id\":10,\"name\":\"JavaOne\",\"presentations\":"
      + "{\"JVM\":\"How to use GC in a smart way\",\"Java Framework\":\"JUnit 5 - New Architecture\","
      + "\"Java Language\":\"Java 9 - Working with Modules\"}}";

   ObjectMapper mapper = new ObjectMapper();
   JavaConference conference = mapper.readValue(json, JavaConference.class);

   Map<String, String> presentations = new HashMap<>();
   presentations.put("Java Framework", "JUnit 5 - New Architecture");
   presentations.put("Java Language", "Java 9 - Working with Modules");
   presentations.put("JVM", "How to use GC in a smart way");

   assertEquals(conference.getPresentations(), presentations);
}
</pre>

Just a tip: As [we have already seen](), we can load a JSON file in the **readValue()** method. Terrible
way to write the **json** variable in the test, since we need to scape characters.

Everything will work as expected if you run the unit test. The **attribute** **presentations** from the JSON
object will be deserialized into a **Map**.

But if we have a JSON object that doesn't match with the Java Map?

Let's see an example:

<pre class="lang:json">
{
   "id" : 10,
   "name" : "JavaOne",
   "JVM" : "How to use GC in a smart way",
   "Java Framework" : "JUnit 5 - New Architecture",
   "Java Language" : "Java 9 - Working with Modules"
}
</pre>

This is almost the same JSON object, but we don't have a an explicit **presentations** attribute.

In this situation, we can teach Jackson to deserialize into a Map!

## Deserializing a JSON into a Map using @JsonAnySetter annotation

In this case, we just need to use the **@JsonAnySetter** in the **JavaConferece** class:

<pre class="lang:java">
class JavaConference {

   @JsonAnySetter
   public void add(String key, String value) {
      presentations.put(key, value);
   }

}
</pre>

In the deserialization process, Jackson will use the **add()** method to create the **Map**.

If you run the test again, everything is working again! \o/

That's it for today folks!

Let's learn about **Custom JSON Deserialization** with **@JsonDeserialize** in the next part: [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

Follow us to keep up to date! \o/
[/markdown]
