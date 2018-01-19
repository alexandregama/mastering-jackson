# Jackson - The Complete Guide

## Configuring Jackson with Maven

```xml 
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>${jackson-core-version}</version>
</dependency>
```

## 1 - Using a Simple Serialization with Jackson

Let's start by serializing a simple POJO object named **Tutorial**. With **serializing** I mean transform an object into a JSON using Jackson:

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

Our main goal is to **transform** a Tutorial object into a JSON like this:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
```

Great challenge, isn't it?

Do to that we should use a class from Jackson called **ObjectMapper**. As you can see from the name, this object will map an object in Java to something, in our case to a JSON object.

This class has a method called **writeValueAsString()** that can receives an object as an argument and generate a JSON object as a String.

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


## 2 - Printing a Pretty JSON with Jackson

To print a **pretty JSON** with Jackson we just need to use an object called **ObjectWriter** from Jackson.
To have this class we should call the method **writerWithDefaultPrettyPrinter()** from the **ObjectMapper** class.

The complete code will be:

```java
	@Test
	public void shouldSerializeWithAPrettyJsonBeingCreated() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");
		
		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);
		
		System.out.println(prettyJson);
	}
```

The output will be:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
```

Nice! 

### Choosing to Print a Pretty JSON at Runtime

Imagine that you have the situation:

- Depending on a condition, you should print a **pretty** JSON or not, at Runtime!

To reach this goal, we should **enable** the pretty printer in the **ObjectMapper** object by using the following code:

```java
mapper.enable(SerializationFeature.INDENT_OUTPUT);
```

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

## 3 - Serializing Composed Java Objects with Jackson

Java is an Object-Oriented Programming Language and one of the most used feature is **Object Composition**.

How does Jackson handle object composition when it is serializing an Object to JSON?

Let's create 2 classes to represent **Frameworks** that belongs to a **Language**:

The **Language** class:

```java
public class Language {

	private Integer id;
	
	private String name;
	
	public Language(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//getters and setters
}
```

And the **Framework** class, composed by the **Language** class:

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

And the output will be:

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

Awesome! Jackson can serialize a composed object by default!

## 4 - Serializing Getter Methods to JSON 

Let's change the **Tutorial** class and rename the getter method **getLanguage()** to **getUsedLanguage()**:

```java
public class Tutorial {

	public String getUsedLanguage() {
		return language;
	}
	
}
```

Let's generate the JSON object with Jackson again:

```java
	@Test
	public void shouldSerializeGetterMethodsByDefault() throws Exception {
		Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");
		
		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(tutorial);
		
		System.out.println(prettyJson);
	}
```

The output will be:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
```

As you can see, **Jackson uses the getter method** to serialize the object into a JSON! Notice that the JSON attribute was named as **usedLanguage**.

We can change this JSON attribute name by configuring a **custom name** with the ```@JsonGetter``` annotation:

```java
@JsonGetter(value = "getAwesomeUsedLanguage")
public String getUsedLanguage() {
	return language;
}
```

If you run the code, you'll have the new attribute name generated by Jackson:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "getAwesomeUsedLanguage" : "Java"
}
```

And if we **remove** the getter method? How does Jackson generate the JSON object?

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators"
}
```

As you can see, the output will not have the property in the JSON object because, again, Jackson uses **getter** methods to serialize objects to JSON.

But can we change this behavior? Of course!

## 5 - Serializing the Java Object using Attributes

Jackson uses **getter** methods by default to serialize objects into JSON! To change this default behavior we just need to use the ```@JsonProperty``` annotation:

Let;s configure the **language** attribute to be used in the serialization and let's give it a **custom name**:

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

Hmm, sounds weird. Jackson will serialize **either** the **attribute** and the **getter** method. We should **ignore** the getter method with the ```@JsonIgnore``` annotation:

```java
@JsonIgnore
public String getUsedLanguage() {
	return language;
}
```

Now the output will be:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
```

#### A little tricky code here!

Jackson serializes the attribute **and** the **getter** method because we had a **custom getter method**. If you change the name of the getter method to the original, in this case ```getLanguage()```, then Jackson will just serialize the attribute!

Another tip is that you can use the ```@JsonIgnore``` in the getter method and in the attribute:

```java
public class Tutorial {

	@JsonIgnore
	private String language;

}
```

#### Another Tricky Situation with @JsonIgnore

If we have the **getter** and **setter** methods following the correct name of the attribute and use the ```@JsonIgnore``` in the **setter** method like this:

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

Notice that Jackson does not use the ```getLanguage()``` method to serialize the attribute to a JSON.

But if we change the getter method to a **custom name**:

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

Then Jackson will serialize the getter method:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
```

## 6 - Ordering Properties in JSON with Jackson Serialization

Do you remember our JSON object generated by Jackson serialization?

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
```

But imagine that for some reason you need this generated JSON ordered in this way:

```json
{
  "title" : "CDI - How to use Decorators",
  "id" : 1,
  "usedLanguage" : "Java"
}
```

The attribute **title** coming before the attribute **id**

To achieve this goal we should use the ```@JsonPropertyOrder``` annotation, passing the desired order for the attributes:

```java
@JsonPropertyOrder({"title", "id", "language"})
public class Tutorial {

}
```

The result will be as we expected!

```json
{
  "title" : "CDI - How to use Decorators",
  "id" : 1,
  "usedLanguage" : "Java"
}
```

## 7 - Serializing the Raw Content with Jackson

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

Notice that we're using the ```@JsonRawContent``` in the **rawContent** attribute to keep its data original in the generated JSON.

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

Notice that the **raw value** was kept as the **original value**. Keep in mind that this annotation could generate an invalid JSON

## 8 - Serializing The Entire Object with a Custom Value with Jackson

Sometimes we have the following challenge:

> The object being serialized by Jackson should generate a **custom JSON**, defined previously

To get this challenge clear, let's start by creating a **Customer** class:

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

Now it's time to have a test to serialize a **customer** object into a JSON object:

```java
	@Test
	public void shouldSerializeTheJavaObjectWithACustomJSONContent() throws Exception {
		Customer customer = new Customer(1l, "Alexandre Gama");
		
		ObjectMapper mapper = new ObjectMapper();
		String prettyJson = mapper.writeValueAsString(customer);
		
		System.out.println(prettyJson);		
	}
```

Without any surprises, the result will be:

```json
{"id":1,"name":"Alexandre Gama"}
```

But if we want to change the content that will be returned by Jackson? If we want to generate a **custom JSON** object?

We should use the ```@JsonValue``` annotation in a method that has the custom logic to generate the JSON object:

```java
public class Customer {

	@JsonValue
	public String customContent() {
		return "{\"custom_id\":" + this.id +",\"custom_name\":" + this.id + " - " + this.name +"}";
	}

}
```

As you can see, the method **customContent()** took the values of the **id** and **name** to create a customized response when a **customer** object is serialized!

The result will be:

```json
"{'custom_id\":1,\"custom_name\":1 - Alexandre Gama}"
```

## 9 - Serializing a JSON Object with a Root

Imagine that we have the following JSON object:

```json
{
  "id" : 1,
  "title" : "Java Jackson Framework",
  "category" : {
    "name" : "Java"
  }
}
```

Now, imagine that we need to generate our JSON object with a **custom root element** called **video_course** as bellow:

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

This **custom root** can be generated by Jackson in a easy way:

Let's create our scenario, by creating the **VideoCourse** class:

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

	//getters and setters
}
```

We don't have the **Category** class yet, so let's create it:

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

To generate the JSON with Jackson, let's create the following test:

```java
	@Test
	public void shouldSerializeTheJSONObjectWithACustomRootName() throws Exception {
		VideoCourse videoCourse = new VideoCourse(1L, "Java Jackson Framework", new Category("Java"));
		
		ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String prettyJson = prettyPrinter.writeValueAsString(videoCourse);
		
		System.out.println(prettyJson);
	}
```

Without surprises, the result should be:

```json
{
  "id" : 1,
  "title" : "Java Jackson Framework",
  "category" : {
    "name" : "Java"
  }
}
```

To have the **custom root** we should use the ```@JsonRootName``` annotation in the class:

```java
@JsonRootName(value = "video_course")
public class VideoCourse {
}
```

But before print it, we must change our test to **enable** a custom root value by using the enum **SerializationFeature** with the option **WRAP_ROOT_VALUE** as bellow:

```java
ObjectMapper mapper = new ObjectMapper();
mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
String prettyJson = mapper.writeValueAsString(videoCourse);
```

And the final result will be:

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

Great!

## 10 - JSON Custom Serialization with Jackson



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