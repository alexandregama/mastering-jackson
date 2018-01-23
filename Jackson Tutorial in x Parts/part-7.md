# 7 - Ordering Properties in JSON with Jackson Serialization

Do you remember our [JSON object generated]() by Jackson serialization? Let's see it again:

```json
{
  "id" : 1,
  "title" : "CDI - How to use Decorators",
  "language" : "Java"
}
```

But imagine that for some reason you need this generated JSON **ordered** in this way:

```json
{
  "title" : "CDI - How to use Decorators",
  "id" : 1,
  "usedLanguage" : "Java"
}
```

The attribute **title** is coming before the attribute **id**

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

Pretty easy, isn't it?
