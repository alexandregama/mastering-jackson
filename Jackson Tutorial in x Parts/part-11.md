## 11 - JSON Custom Serializer with Jackson

In this example, we're going to see the **Conference** class that has a **LocalDate**, from Java 8, to be serialized.

Let's create the class:

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

    //getters and setters as usual
}
```

It's time to create a method do **serialize** into JSON a **Conference** object:

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

So, as you can see, Jackson tries its best when serializing a LocalDate into a JSON object. But the result may not be as you was expecting:

```json
"monthValue" : 11,
"dayOfMonth" : 20,
"dayOfYear" : 324,
"dayOfWeek" : "TUESDAY",
"leapYear" : false
```

In our case, we'd like to have a data being printed out like this:

```json
"date" : "2018-11-20"
```

To reach this goal we can create a Custom Serializer, to teach to Jackson how a data should be serialized.

#### Creating a CustomDateSerializer

It's time to create the **CustomDateSerializer**. This class should:

- Extends the abstract class **StdSerializer**
- Implements the **serialize** method, that will receive the **LocalDate** to be formatted
- Overrides 2 **constructors** from the abstract class

The abstract class **StdSerializer** is the recommend class to create Standard and Custom serializers. Many important Jackson's classes extends this class, like **JsonValueSerializer**, **ByteArraySerializer**, **RawSerializer** and much more!

Let's jump into code:

```java
public class CustomDateSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = 1L;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public CustomDateSerializer() {
		this(null);
	}

	protected CustomDateSerializer(Class<LocalDate> date) {
		super(date);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {		
		String formattedDate = formatter.format(value);

		gen.writeString(formattedDate);
	}

}
```

The code is simple. As you can see, we just used the **DateTimeFormatter** to format the **LocalDate** object.

After that, we just need to call the **writeString()** method that will generate the JSON with the formatted date.

Now it's time to use the new Custom Serializer! To do that, we should use the ```@JsonSerialize``` annotation

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

#### Custom Serializer on the Class

You can create a Custom Serializer to be used by the class, not just by the attribute.

Now, we;d like to generate the following JSON object when serializing a **Conference** object:

```json
{
  "id" : 10,
  "name" : "JavaOne"
}
```

The Custom Serializer will have the following code:

```java
public class ConferenceCustomFieldsSerializer extends StdSerializer<Conference> {

	//This Default constructor is required by Jackson
	public ConferenceCustomFieldsSerializer() {
		this(null);
	}

	protected ConferenceCustomFieldsSerializer(Class<Conference> conference) {
		super(conference);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Conference value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		gen.writeEndObject();
	}

}
```

As you can see, we used a few methods from the **JsonGenerator** object.

Now we just need to use this custom serializer on the class, like this:

```java
@JsonSerialize(using = ConferenceCustomFieldsSerializer.class)
public class Conference {
}
```

That's it!
