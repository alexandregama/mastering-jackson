## 2 - Using a Simple Serialization with Jackson

Let's start by serializing a simple POJO object in Java named **Tutorial**. With **serializing** I mean transform a Java **object** into a **JSON** using Jackson:

```java
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
```

Our main goal is to **transform/parse** a Tutorial **object** into a **JSON** like this:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
```

Great challenge, isn't it?

Do to that we should use a class from Jackson called **ObjectMapper**. As you can see from the name, this object will **map** an object in Java to something, in our case to a **JSON object**.

This class has a method called **writeValueAsString()** that can receives an object as an argument and generate a JSON object as a String.

![](maybe image here)

Let's see the complete code in a Unit Test with JUnit : )

```java
@Test
public void shouldSerializeAnObjectIntoAJSON() throws Exception {
	Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

	ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(tutorial);

	System.out.println(json);

	assertEquals("{\"id\":1,\"title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}", json);
}
```

Pretty simple, right? I just printed out the JSON object to have the JSON being validated easier than asserting it with the assertEquals() method.

The output will be:

```json
{"id":1,"title":"CDI - How to use Decorators","language":"Java"}
```

Great! But notice that this JSON could be really huge and see it in just 1 one could be challenge. It's time to see a better way!
