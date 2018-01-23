# Jackson - The Complete Guide in 15 Parts

Hello!

This is a post from the Jackson Series: [Jackson - The Complete Guide in 15 Parts]()

- [Part 1 - ]()
- [Part 2 - ]()
- [Part 3 - ]()
- [Part 4 - ]()
- [Part 5 - ]()
- [Part 6 - ]()
- [Part 7 - ]()
- [Part 8 - ]()
- [Part 9 - ]()
- [Part 10 - ]()
- [Part 11 - ]()
- [Part 12 - ]()
- [Part 13 - ]()
- [Part 14 - ]()
- [Part 15 - ]()
- [Part 16 - ]()
- [Part 17 - ]()
- [Part 18 - ]()
- [Part 19 - ]()
- [Part 20 - ]()

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

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>${jackson-core-version}</version>
</dependency>
```

If you prefer Gradle:

```groovy
compile group: 'com.fasterxml.jackson.core', name: 'jackson-core', version: '2.9.3'
```

- [Jackson Annotations](https://github.com/FasterXML/jackson-annotations)

Jackson has a special project to deal with **Annotations**. This project depends on Jackson Core project.

In this project we have the basics and more advanced annotations. To configure the Jackson Annotations project
we just need to use the following Maven dependency:

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-annotations</artifactId>
  <version>${jackson-annotations-version}</version>
</dependency>
```

If you prefer Gradle:

```groovy
compile group: 'com.fasterxml.jackson.core', name: 'jackson-annotations', version: '2.9.3'
```

- [Jackson Data Binding](https://github.com/FasterXML/jackson-databind)

This is a project that has a general **Data Binding** for Jackson and uses the project **Jackson Core**

Notice that Jackson Data Binding can work with other types rather than just JSON.

To use the Data Binding project we can just configure the Maven Dependency as below:

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>${jackson.version}</version>
</dependency>
```

If you prefer Gradle:

```groovy
compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.3'
```

This project uses the **Jackson Core** and **Jackson Annotation** projects as dependency but you don't need to worry about this dependencies, since Maven will download them for us : )
