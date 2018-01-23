## 12 - Serializing HashMap into JSON Objects with Jackson

As you have seen in the previous example, let's use the **Conference** class again, but now with the attribute **presentations**, that is a **HashMap**:

```java
public class Conference {

	private Long id;

	private String name;

	private Map<String, String> presentations = new HashMap<>();

}
```

The simple test to serialize a **Conference** object into a JSON could be:

```java
	@Test
	public void shouldSerializeAHashMapObjectIntoAJson() throws Exception {
		Map<String, String> presentations = new HashMap<>();
		presentations.put("Java Framework", "JUnit 5 - New Architecture");
		presentations.put("Java Language", "Java 9 - Working with Modules");
		presentations.put("JVM", "How to use GC in a smart way");

		Conference conference = new Conference(10l, "JavaOne", LocalDate.of(2018, 11, 20));
		conference.setPresentations(presentations);

		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(conference);

		System.out.println(prettyJson);		
	}
```

And the result will be the following JSON being generated:

```json
{
  "id" : 10,
  "name" : "JavaOne",
  "presentations" : {
    "JVM" : "How to use GC in a smart way",
    "Java Framework" : "JUnit 5 - New Architecture",
    "Java Language" : "Java 9 - Working with Modules"
  },
  "date" : "2018-11-20"
}
```

Jackson will serialize a Java Map by default :)

But if we want to have each key and value as a line in the main JSON, like this?

```json
{
  "id" : 10,
  "name" : "JavaOne",
  "date" : "2018-11-20",
  "JVM" : "How to use GC in a smart way",
  "Java Framework" : "JUnit 5 - New Architecture",
  "Java Language" : "Java 9 - Working with Modules"
}
```

Then, we just need to use the ```@JsonAnyGetter``` annotation in the **Map** attribute:

```java
@JsonAnyGetter
public Map<String, String> getPresentations() {
	return presentations;
}
```

And the result will be the JSON object with each key and value being a new line.

That's it!
