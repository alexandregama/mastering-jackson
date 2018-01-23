# 6 - Serializing Java Object into JSON using Fields with Jackson

As we've seen in the [previous Jackson tutorial](), Jackson uses **getter** methods by default to serialize objects into JSON!

To change this default behavior we just need to use the ```@JsonProperty``` annotation:

Let's configure the **language** attribute to be used in the serialization and let's give it a **custom name**:

```java
public class Tutorial {

	@JsonProperty("best_Language")
	private String language;

	public String getUsedLanguage() {
		return language;
	}
}
```

If you run the code, the output will be:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java",
  "best_Language" : "Java"
}
```

Hmm, sounds weird. Jackson will serialize **either** the **attribute** and the **getter** method.

We should **ignore** the getter method with the ```@JsonIgnore``` annotation:

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

Great! But let's understand what just happened here!

## A little tricky code with Jackson!

Jackson serializes the attribute **and** the **getter** method because we have a **custom getter method**. Do you remember the **getUsedLanguage()** method?

If you change the name of the getter method to the **original** name, in this case ```getLanguage()```, then Jackson will just serialize the attribute!

Another tip here is that you can use the ```@JsonIgnore``` in the getter method and in the attribute:

```java
public class Tutorial {

	@JsonIgnore
	private String language;

}
```

## Another Tricky Situation with @JsonIgnore

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

Notice that Jackson does not use the ```getLanguage()``` method to serialize the attribute to a JSON. Sounds obvious, since we're ignoring the **setLanguage()** method!

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

Then Jackson will serialize the getter method, even if we're ignoring the **setLanguage()** method:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "usedLanguage" : "Java"
}
```

## Configuring Class to Serialize Just Fields with Jackson

[As you know](), Jackson uses **getter** methods to serialize Java objects into a JSON. Did you try to remove all getter methods?

Let's try it out:

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

}
```

The unit test to serialize a Tutorial object into a JSON:

```java
@Test
public void shouldSerializeJustFieldsWithJackson() throws Exception {
	Tutorial tutorial = new Tutorial(1L, "CDI - How to use Decorators", "Java");

	ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	String prettyJson = prettyPrinter.writeValueAsString(tutorial);

	System.out.println(prettyJson);
}
```

If you execute this code, an error will be thrown in our face:

```
com.fasterxml.jackson.databind.JsonMappingException: No serializer found for class com.mastering.jackson.tutorial.model.Tutorial and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )
```

Sounds obvious, since we removed all the **getter** methods!

To serialize the object into a JSON using just the **fields** we can use the annotation ```@JsonAutoDetect``` on the class. This annotation allows us to configure which **Visibility** should be used by Jackson.

There is the Enum **Visibility** with a few visibility options:

- ANY
- NON_PRIVATE
- PROTECTED_AND_PUBLIC
- PUBLIC_ONLY
- NONE
- DEFAULT

We're going to use the **ANY** and **NONE** options for **fieldVisibility** and **getterVisibility** respectively:

```java
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE)
public class Tutorial {
}
```

If you run the code again:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
```

Great! Everything is working again and you can use just **Fields** in the Jackson serialization!
