# 4 - Serializing Composed Java Objects with Jackson

Java is an Object-Oriented Programming Language and one of the most used feature is **Object Composition**.

How does Jackson **handle object composition** when it is serializing an Object to JSON?

Let's create 2 classes to represent **Frameworks** that belongs to a **Language**:

The **Language** class could be:

```java
public class Language {

	private Integer id;

	private String name;

	public Language(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	//famous getters and setters
}
```

And the **Framework** class, composed by the **Language** class could be as follows:

```java
public class Framework {

	private Long id;

	private String name;

	private Language language;

	public Framework(Long id, String name, Language language) {
		this.id = id;
		this.name = name;
		this.language = language;
	}

	//as usual, getters and setters here
}
```

Great! It's time to create the code to serialize a **Framework** object with Jackson to see the result:

```java
@Test
public void shouldSerializeComposeObjectsWithJackson() throws Exception {
	Language java = new Language(1, "Java");
	Framework spring = new Framework(1L, "Spring", java);

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(spring);

	System.out.println(prettyJson);
}
```

If you run this code, the output will be:

```json
{
  "id" : 1,
  "name" : "Spring",
  "language" : {
    "id" : 1,
    "name" : "Java"
  }
}
```

Awesome!

Jackson can serialize a composed object by default! Let's see how to change a few default behaviours in the next posts!
