# 3 - Printing a Pretty JSON with Jackson

As [we've seen in the previous post](), if we use a basic Jackson serialization, our JSON object will be printed out in just 1 line!

## Pretty JSON with ObjectWriter

To print a **pretty JSON** with Jackson we just need to use an object called **ObjectWriter** from Jackson.

To have this class we should call the method **writerWithDefaultPrettyPrinter()** from the **ObjectMapper** class.

The complete code could be:

```java
@Test
public void shouldSerializeWithAPrettyJsonBeingCreatedWithJackson() throws Exception {
	Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(tutorial);

	System.out.println(prettyJson);
}
```

If you run this code, the output will be:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
```

Cool! Now we have a **pretty JSON** being printed out \o/

## Choosing to Print a Pretty JSON at Runtime

Imagine that you have the situation:

> Depending on a condition, you should print a **pretty** JSON or not, at Runtime!

To reach this goal, we should **enable** the pretty printer in the **ObjectMapper** object by using the following code:

```java
mapper.enable(SerializationFeature.INDENT_OUTPUT);
```

The method **enable()** allows us to enable a few features when generating/parsing a JSON.

The complete code could be:

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

Just test the boolean variable **shouldPrintPretty** with true and false to see the result : )
