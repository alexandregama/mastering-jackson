# 13 - Ignoring Properties in the JSON with Jackson

Sometimes you want to ignore a few Properties to not being serialized by Jackson into a JSON.

Let's create a class **Course**:

```java
public class Course {

	private Long id;

	private String title;

	private String language;

	private String category;

	private LocalDate date;

	public Course(Long id, String title, String language, String category, LocalDate date) {
		this.id = id;
		this.title = title;
		this.language = language;
		this.category = category;
		this.date = date;
	}

    //getters and setters as usual
}
```

Now, we have the test to serialize a **Course** object into a JSON object:

```java
@Test
public void shouldIgnorePropertiesWithJacksonWhenSerializingToJSON() throws Exception {
	Course course = new Course(5L, "Spring 5 - REST API and Security", "Framework", "Java", LocalDate.of(2018, 02, 20));

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(course);

	System.out.println(prettyJson);
}
```

The result will be the following JSON:

```json
{
  "id" : 5,
  "title" : "Spring 5 - REST API and Security",
  "language" : "Framework",
  "category" : "Java",
  "date" : {
    "year" : 2018,
    "month" : "FEBRUARY",
    "chronology" : {
      "id" : "ISO",
      "calendarType" : "iso8601"
    },
    "era" : "CE",
    "monthValue" : 2,
    "dayOfMonth" : 20,
    "dayOfYear" : 51,
    "dayOfWeek" : "TUESDAY",
    "leapYear" : false
  }
}
```

But now we'd like to ignore the **Category** and **Date** properties. Let's use the ```@JsonIgnoreProperties``` on the class to indicate this properties:

```java
@JsonIgnoreProperties({"category", "date"})
public class Course {
}
```

If you run the test again, you're going to see the following result:

```json
{
  "id" : 5,
  "title" : "Spring 5 - REST API and Security",
  "language" : "Framework"
}
```

[Remember]() that we could also use the ```@JsonIgnore``` on the property directly:

```java
@JsonIgnore
private String language;
```

That's it!
