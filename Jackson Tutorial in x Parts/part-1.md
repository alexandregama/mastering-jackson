[markdown]
# Jackson - The Complete Guide in 20 Parts

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts](https://blog.hackingcode.io/jackson-java-tutorial-news-posts-videos)

- **[Part 1 - Intro to Jackson and Configuration with Maven and Gradle](https://blog.hackingcode.io/jackson-java-tutorial-serialize-json-config-maven)**
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
- [Part 19 - Deserializing Map from a JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-to-map)
- [Part 20 - Jackson Custom Deserialization with @JsonDeserialize](https://blog.hackingcode.io/jackson-java-tutorial-deserialize-json-with-custom-deserializer)

In the today's post we're going to see an Introduction to Jackson Java Library and how to configure it with Maven and Gradle!

## 1 - Intro to Jackson and Configuration with Maven

**Jackson** in one of the most used **Library in Java** to parse **Java Objects into JSON**. Actually Jackson ca be used to do really more than that as:

- Data processing with many types such as Avro, XML and CSV
- Data formatting
- Data binding
- Data streaming

Each of these components has its own project. For example, to work with Jackson YAML, XML and Avro, you will find the following great projects:

- Jackson Data Format for XML in [GitHub](https://github.com/FasterXML/jackson-dataformat-xml)
- Jackson Data Format Binary in [GitHub](https://github.com/FasterXML/jackson-dataformats-binary)
- Jackson Data Format Text in [GitHub](https://github.com/FasterXML/jackson-dataformats-text)

We have **3 main packages** in Jackson, that have its own projects also:

- [Jackson Core](https://github.com/FasterXML/jackson-core)

This project defines the **Streaming API** with Parser and Generator abstractions used by **Jackson Processor**

This Jackson Core can be configured with Maven as below:

<pre class="lang:xml">
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>${jackson-core-version}</version>
</dependency>
</pre>

If you prefer Gradle:

<pre class="lang:groovy">
compile group: 'com.fasterxml.jackson.core', name: 'jackson-core', version: '2.9.3'
</pre>

- [Jackson Annotations](https://github.com/FasterXML/jackson-annotations)

Jackson has a special project to deal with **Annotations**. This project depends on Jackson Core project.

In this project we have the basics and more advanced annotations. To configure the Jackson Annotations project
we just need to use the following Maven dependency:

<pre class="lang:xml">
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-annotations</artifactId>
  <version>${jackson-annotations-version}</version>
</dependency>
</pre>

If you prefer Gradle:

<pre class="lang:groovy">
compile group: 'com.fasterxml.jackson.core', name: 'jackson-annotations', version: '2.9.3'
</pre>

- [Jackson Data Binding](https://github.com/FasterXML/jackson-databind)

This is a project that has a general **Data Binding** for Jackson and uses the project **Jackson Core**

Notice that Jackson Data Binding can work with other types rather than just JSON.

To use the Data Binding project we can just configure the Maven Dependency as below:

<pre class="lang:xml">
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>${jackson.version}</version>
</dependency>
</pre>

If you prefer Gradle:

<pre class="lang:groovy">
compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.3'
</pre>

This project uses the **Jackson Core** and **Jackson Annotation** projects as dependency but you don't need to worry about this dependencies, since Maven will download them for us : )

That's it for today folks!

Let's learn about **JSON Serialization** in the next part: [Part 2 - Simple Serialization into JSON with Jackson](https://blog.hackingcode.io/jackson-java-tutorial-serialization-to-json)

Follow us to keep up to date! \o/
[/markdown]
