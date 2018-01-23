## 8 - Serializing the Raw Content in the JSON with Jackson

Sometimes we have a content that should be serialized with its **raw value**. Imagine that in an object you have a **JavaScript** code that should be serialized with its **original content**.

Jackson allows us to keep this original content by using the ```@JsonRawValue``` annotation:

To represent our JavaScript code, it's sound good to create a class called...**JavaScript**:

```java
public class JavaScript {

	private String name;

	private String content;

	@JsonRawValue
	private String rawContent;

}
```

Notice that we're using the ```@JsonRawContent``` in the **rawContent** attribute to keep its original data in the generated JSON.

The complete test could be:

```java
@Test
public void shouldSerializeThePropertyExactlyAsItIs() throws Exception {
	JavaScript javascript = new JavaScript("JavaScript",
			"function name() {return \"JavaScript Jasmine Framework\"}",
			"function name() {return \"JavaScript Jasmine Framework\"}");

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(javascript);

	System.out.println(prettyJson);
}
```

And the output will be:

```json
{
  "name" : "JavaScript",
  "content" : "function name() {return \"JavaScript Jasmine Framework\"}",
  "rawContent" : function name() {return "JavaScript Jasmine Framework"}
}
```

Notice that the **raw value** was kept as the **original value**. Keep in mind that this annotation could generate an invalid JSON, because it will keep the original content, that could have a not valid JSON.
