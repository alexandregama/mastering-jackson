# 14 - Serializing Enum Objects into a JSON with Jackson

Jackson serializes **Enum** objects into a **JSON** by default. Let's check it out!

First of all, we should create the **Enum**:

```java
public enum Status {

	STARTED(1, "Started"), IN_PROGRESS(2, "In Progress"), FINISHED(2, "Finished");

	private Integer code;

	private String value;

	private Status(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
```

Now it's time to use this Enum in the **Article** class:

```java
public class Article {

	private Long id;

	private String title;

	private Status status;

	public Article(Long id, String title, Status status) {
		this.id = id;
		this.title = title;
		this.status = status;
	}

	//getters and setters as usual
}
```

And then let's create the unit test to serialize an **Article** object into a **JSON** object with Jackson:

```java
@Test
public void shouldSerializeAnEnumObjectIntoJsonWithJackson() throws Exception {
	Article article = new Article(10L, "Java 8 - The Complete Tutorial", Status.FINISHED);

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(article);

	System.out.println(prettyJson);
}
```

If you run this code, you'll get the following result:

```json
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : "FINISHED"
}
```

Great!

But wait! Our **Enum** has a **code** and a **value**:

```java
STARTED(1, "Started"), IN_PROGRESS(2, "In Progress"), FINISHED(2, "Finished");
```

But it could be really great if we generate the JSON as the following, with the code and status from the Enum!

```json
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : {
    "code" : 2,
    "value" : "Finished"
  }
}
```

Let's see how!

## Serializing Enum Objects with @JsonFormat

If you're using Jackson 2.1.2 or above you can use the great annotation ```@JsonFormat``` on the Enum:

```java
@JsonFormat(shape = Shape.OBJECT)
public enum Status {
}
```

With just this configuration, if you run the code again you'll have the expected result :)

```json
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : {
    "code" : 2,
    "value" : "Finished"
  }
}
```

## Serializing Enum Objects with @JsonValue

You can serialize an Enum object with the ```@JsonValue``` annotation. But as [we've already studied]() this annotation, you can use it to serialize a custom value to the entire Class, or in this case, an Enum.

So, you can just use this annotation in 1 **getter** method in the Enum.

Obviously we're curious, so let's use this annotation in both **getter** method on the Enum:

```java
public enum Status {

	@JsonValue
	public Integer getCode() {
		return code;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

}
```

if you run the unit test again, an error will be thrown in our face:

```bash
com.fasterxml.jackson.databind.JsonMappingException: Problem with definition of [AnnotedClass com.mastering.jackson.model.Status]: Multiple value properties defined ([method com.mastering.jackson.model.Status#getValue(0 params)] vs [method com.mastering.jackson.model.Status#getCode(0 params)])
```

So, let's change our code to just serialize a JSON object with the **status** from Enum:

```java
public enum Status {

	public Integer getCode() {
		return code;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

}
```

if you run the code again, you will have the **status** being printed out:

```json
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : "Finished"
}
```

## Ignoring Fields in the Enum Serialization into a JSON with Jackson

We can ignore field and methods in the Enum object by just using the ```@JsonIgnore``` annotation:

```java
@JsonFormat(shape = Shape.OBJECT)
public enum Status {

	@JsonIgnore
	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
```

If you run the unit test, you'll have the generated JSON:

```json
{
  "id" : 10,
  "title" : "Java 8 - The Complete Tutorial",
  "status" : {
    "value" : "Finished"
  }
}
```
