## Configuring with Maven

```xml 
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>${jackson-core-version}</version>
</dependency>
```

## Using a Simple Serialization with Jackson

```java
	@Test
	public void shouldSerializeInASimpleWay() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(tutorial);
		
		System.out.println(json);
		
		assertEquals("{\"id\":1,\"title\":\"CDI - How to use Decorators\",\"language\":\"Java\"}", json);
	}
```

## Printing a Pretty JSON with Jackson

```java
	@Test
	public void shouldSerializeWithAPrettyJsonBeingCreated() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");
		
		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);
		
		System.out.println(prettyJson);
	}
```

## Printing a Pretty JSON at Runtime

Just need to use:

```java
mapper.enable(SerializationFeature.INDENT_OUTPUT);
```

```java
	@Test
	public void shouldPrintAPrettyJsonAtRuntime() throws Exception {
		boolean shouldPrintPretty = true;
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");
		
		ObjectMapper mapper = new ObjectMapper();
		if (shouldPrintPretty) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		String prettyJson = mapper.writeValueAsString(tutorial);
		
		System.out.println(prettyJson);
	}
```

## Serializing Composed Java Objects with Jackson

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

The output:

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

## Serializing Getter Methods to JSON 

Let's change the Tutorial class and rename the getter method getLanguage()

```java
public class Tutorial {

	public String getUsedLanguage() {
		return language;
	}
	
}
```

Let's print again:

```java
	@Test
	public void shouldSerializeGetterMethodsByDefault() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");
		
		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);
		
		System.out.println(prettyJson);
	}
```

The output:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
```

We can change the getter method:

```java
@JsonGetter(value = "getAwesomeUsedLanguage")
public String getUsedLanguage() {
	return language;
}
```

And the output:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "getAwesomeUsedLanguage" : "Java"
}
```

And if we remove the getter method?

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators"
}
```

The output will not have the property in the JSON object.

## Serializing the Java Object using Attributes

We can achieve this goal by using the ```@JsonProperty```

```java
public class Tutorial {

	@JsonProperty("best_Language")
	private String language;

	public String getUsedLanguage() {
		return language;
	}
}
```

The output will be:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java",
  "best_Language" : "Java"
}
```

Hmm, sounds weird. Jackson will serialize either the Attribute and the getter method. We should **ignore** the getter method

```java
	@JsonIgnore
	public String getUsedLanguage() {
		return language;
	}
```

Now the output:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
```

Notice that you can use the @JsonIgnore in the getter method but also in the attribute

```java
public class Tutorial {

	@JsonIgnore
	private String language;

}
```

## Trick Situation with @JsonIgnore

```java
public class Tutorial {

	public String getLanguage() {
		return language;
	}

	@JsonIgnore
	public void setLanguage(String language) {
		this.language = language;
	}

}
```

The output will be:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators"
}
```

But if we change the getter method:

```java
public class Tutorial {

	public String getUsedLanguage() {
		return language;
	}

	@JsonIgnore
	public void setLanguage(String language) {
		this.language = language;
	}

}
```

Everything is fine:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
```

## Ordering Properties in JSON with Jackson Serialization

