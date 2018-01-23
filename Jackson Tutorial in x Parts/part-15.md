# 15 - Jackson Simple Deserialization with ObjectMapper

With Jackson we can **deserialize** a JSON object into a **Java** object with the class **ObjectMapper**, the same class that [we used]() to **serialize** Java objects into a JSON.

## Deserializing a JSON String Object into a Java Object

We're going to use the **Tutorial** class again:

```java
public class Tutorial {

	private Long id;

	private String title;

	private String language;

	// Required by Jackson when Deserializing the JSON Object
	public Tutorial() {}

	public Tutorial(Long id, String title, String language) {
		this.id = id;
		this.title = title;
		this.language = language;
	}

}
```

Notice that we have a **constructor** receiving values for the fields. To deserialize a Tutorial object we **should have** a **default constructor**.

Now it's time to create the unit test to deserialize a JSON object:

```java
@Test
public void shouldDeserializeAJsonObjectIntoAJavaObject() throws Exception {
	String json = "{\"id\":1,\"title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

	Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

	assertEquals(tutorial.getId(), 1L, 0);
	assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
	assertEquals(tutorial.getLanguage(), "Java");
}
```

As you can see we can use the **readValue()** method, passing a JSON string as an argument.

## Deserializing a JSON File into a Java Object

To deserialize a JSON file we just need to use the same **readValue()** method, but this time receiving a **File** object.

Let's create the file **tutorial.json**:

```json
{"id":1,"title":"CDI - How to use Decorators","language":"Java"}
```

The unit test to deserialize this file could be:

```java
@Test
public void shouldDeserializeAJsonFileIntoAJavaObject() throws Exception {
	File file = new File("tutorial.json");

	Tutorial tutorial = new ObjectMapper().readValue(file, Tutorial.class);

	assertEquals(tutorial.getId(), 1L, 0);
	assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
	assertEquals(tutorial.getLanguage(), "Java");
}
```
