## 6 - Serializing the Java Object into JSON using Attributes with Jackson

Jackson uses **getter** methods by default to serialize objects into JSON! To change this default behavior we just need to use the ```@JsonProperty``` annotation:

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

#### A little tricky code with Jackson here!

Jackson serializes the attribute **and** the **getter** method because we had a **custom getter method**. Do you remember the **getUsedLanguage()** method?

If you change the name of the getter method to the **original**, in this case ```getLanguage()```, then Jackson will just serialize the attribute!

Another tip here is that you can use the ```@JsonIgnore``` in the getter method and in the attribute:

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
