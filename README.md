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

We can achieve this by using the @JsonPropertyOrder

```java
@JsonPropertyOrder({"title", "id", "language"})
public class Tutorial {

}
```

The result will be:

```json
{
  "title" : "CDI - How to use Decorators",
  "id" : 1,
  "usedLanguage" : "Java"
}
```

## Serializing the Raw Content with Jackson

Sometimes we have a content that should be serialized with its raw value. Getting a JavaScript content as an example:

```java
public class JavaScript {

	private String name;
	
	private String content;
	
	@JsonRawValue
	private String rawContent;

}
```

The test will be:

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

Notice that the raw value was kept as the original value. Keep in mind that this annotation could generate an invalid JSON

## Serializing The Entire Object with a Custom Value with Jackson

```java
public class Customer {

	private Long id;
	
	private String name;

	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
```

The test:

```java
	@Test
	public void shouldSerializeTheJavaObjectWithACustomJSONContent() throws Exception {
		Customer customer = new Customer(1l, "Alexandre Gama");
		
		ObjectMapper mapper = new ObjectMapper();
		String prettyJson = mapper.writeValueAsString(customer);
		
		System.out.println(prettyJson);		
	}
```

The result will be:

```json
{"id":1,"name":"Alexandre Gama"}
```

But if we want to change the content that will be returned by Jackson. We should use the @JsonValue annotation:

```java
public class Customer {

	@JsonValue
	public String customContent() {
		return "{\"custom_id\":" + this.id +",\"custom_name\":" + this.id + " - " + this.name +"}";
	}

}
```

The output will print out the following result:

```json
"{'custom_id\":1,\"custom_name\":1 - Alexandre Gama}"
```

## Serializing a JSON Object with a Root

Let's create our scenario:

```java
public class VideoCourse {

	private Long id;
	
	private String title;
	
	private Category category;

	public VideoCourse(Long id, String title, Category category) {
		this.id = id;
		this.title = title;
		this.category = category;
	}

}
```

And the Category class:

```java
public class Category {
	
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
```

The simple test:

```java
	@Test
	public void shouldSerializeTheJSONObjectWithARootName() throws Exception {
		VideoCourse videoCourse = new VideoCourse(1L, "Java Jackson Framework", new Category("Java"));
		
		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(videoCourse);
		
		System.out.println(prettyJson);
	}
```

And the result:

```json
{
  "id" : 1,
  "title" : "Java Jackson Framework",
  "category" : {
    "name" : "Java"
  }
}
```

We should use the @JsonRootName annotation:

```java
@JsonRootName(value = "video_course")
public class VideoCourse {
}
```

But before print it, we must change our test:

```java
ObjectMapper mapper = new ObjectMapper();
mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
String prettyJson = mapper.writeValueAsString(videoCourse);
```

And the final result:

```json
{
  "video_course" : {
    "id" : 1,
    "title" : "Java Jackson Framework",
    "category" : {
      "name" : "Java"
    }
  }
}
```

## JSON Custom Serialization with Jackson

```java
public class Conference {

	private Long id;
	
	private String name;
	
	private LocalDate date;

	public Conference(Long id, String name, LocalDate date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}

}
```

A simple test:

```java
	@Test
	public void shouldSerializeWithACustomDatePattern() throws Exception {
		Conference conference = new Conference(10l, "JavaOne", LocalDate.of(2018, 11, 20));
		
		ObjectMapper mapper = new ObjectMapper();
		String prettyJson = mapper.writeValueAsString(conference);
		
		System.out.println(prettyJson);		
	}
```

The result will be:

```json
{
  "id" : 10,
  "name" : "JavaOne",
  "date" : {
    "year" : 2018,
    "month" : "NOVEMBER",
    "chronology" : {
      "id" : "ISO",
      "calendarType" : "iso8601"
    },
    "era" : "CE",
    "monthValue" : 11,
    "dayOfMonth" : 20,
    "dayOfYear" : 324,
    "dayOfWeek" : "TUESDAY",
    "leapYear" : false
  }
}
```

Creating the **CustomDateSerializer**

```java
public class CustomDateSerializer extends StdSerializer<LocalDate> {
	
	private static final long serialVersionUID = 1L;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public CustomDateSerializer() {
		this(null, false);
	}

	protected CustomDateSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		
		String formattedDate = formatter.format(value);
		
		gen.writeString(formattedDate);
	}

}
```

Let's use the new Custom Serializer

```java
public class Conference {

	@JsonSerialize(using = CustomDateSerializer.class)
	private LocalDate date;

}
```

The result will be:

```json
2018-11-20
{
  "id" : 10,
  "name" : "JavaOne",
  "date" : "2018-11-20"
}
```

## Serializing HashMap into JSON Objects with Jackson

```java
public class Conference {

	private Long id;
	
	private String name;
	
	private Map<String, String> presentations = new HashMap<>();
	
}
```

The simple test:

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

And the result:

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

But if we want to have each key and value as a line in the main JSON?

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

We just need to use the @JsonAnyGetter annotation

## Deserializing a JSON Object when it doesn't Match the Java Object

@JsonCreator