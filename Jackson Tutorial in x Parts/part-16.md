# 16 - Jackson Deserialization with @JsonSetter Annotation

Sometimes your JSON doesn't match with the **Fields** on the class.

Imagine the following JSON:

```json
{
 "special_id":1,
 "special_title":"CDI - How to use Decorators",
 "language":"Java"
}
```

The **Tutorial** class will be used to deserialize this JSON object:

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
```

As you can see, we have 2 attributes that doesn't match with the fields in the class:

- special_id
- special_title

Let's try to run the unit test to deserialize the JSON object:

```java
@Test
public void shouldDeserializeCustomJsonObjectIntoAJavaObject() throws Exception {
	String json = "{\"special_id\":1,\"special_title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}";

	Tutorial tutorial = new ObjectMapper().readValue(json, Tutorial.class);

	assertEquals(tutorial.getId(), 1L, 0);
	assertEquals(tutorial.getTitle(), "CDI - How to use Decorators");
	assertEquals(tutorial.getLanguage(), "Java");
}
```

If you run this code, you'll get the error:

```bash
com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "special_id" (class com.mastering.jackson.tutorial.model.Tutorial), not marked as ignorable (3 known properties: "title", "id", "language"])
 at [Source: {"special_id":1,"special_title":"CDI - How to use Decorators","language":"Java"}; line: 1, column: 16] (through reference chain: com.mastering.jackson.tutorial.model.Tutorial["special_id"])
```

We need to teach Jackson how it can deserialize the **special_id** in the **id** for example. To do that, we just need to use the ```@JsonSetter``` annotation in the **setter** method:

```java
@JsonSetter("special_id")
public void setId(Long id) {
	this.id = id;
}

@JsonSetter("special_title")
public void setTitle(String title) {
	this.title = title;
}
```

If you run the code again, everything works fine! :)
