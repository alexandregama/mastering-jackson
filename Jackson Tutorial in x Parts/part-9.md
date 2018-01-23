# 9 - Serializing The Entire JSON Object with a Custom Value with Jackson

Sometimes we have the following challenge:

> The object being serialized by Jackson should generate a **custom JSON**, defined **previously**

To get this challenge clear, let's start by creating a **Customer** class:

```java
public class Customer {

	private Long id;

	private String name;

	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	//getters and setters as usual
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
