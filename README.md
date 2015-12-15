# JSON matchers based on hamcrest matcher framework (for org.json)

Hamcrest framework extension to add support for JSON. The target is to build a custom factory of Matcher<JSONObjects> to aid in unit tests and production code.

Introduction
Pages and pages of unit tests for verification of JSONObjects and inclination of using Hamcrest matchers have resulted in this.

## Details
1. hasKeyMatcher - returns a Matcher<JSONObject> which can be used for development/tests.

```java
JSONObject json = new JSONObject("{" +
  "key:value" +
"}");
assertThat(json, hasKey("key"));


JSONObject json = new JSONObject("{" +                              
  "startsWithStartContainsAndEndsWithend:value" + 
"}";
assertThat(json, hasKey( 
  startsWith("start"),
  endsWith("end"),   
  containsString("Contains")
));
```
2. hasPathMatcher - exposes a matcher API hasPath that takes in a JSON path (as string) and returns a Matcher<JSONObject>
The path is similar to bean path of java object graph.
Samples
  * root.node1.node2.node3 
  * root.node1[3].node2.node3[0][1].node4
  * root.node1.[3].node2[0].[6]
  * and so on.
```java 
public static final String JSON_STRING_FOR_PATH_TEST = 
  "{" +
    "json1: {" +
      "json2: {" +
        "json3: [" +
          "{" +
            "field:value" + 
          "}" +
        "], " +
      "json4: {" +
        "field4:value4" +
      "}" +
    "}" + 
  "}" + 
"}";

@SuppressWarnings("unchecked")
@Test
public void verifyThatMatcherIsAbleToLocatePath() throws JSONException {                
  JSONObject json = new JSONObject(JSON_STRING_FOR_PATH_TEST);
  assertThat(json, allOf(
    hasPath("json1.json2.json3[0].field"), 
    hasPath("json1.json2.json4.field4")));
}
                
@Test
public void verifyThatMatcherReturnsFalseWhenPathIsNotFound() throws JSONException {            
  JSONObject json = new JSONObject(JSON_STRING_FOR_PATH_TEST);
  assertThat(hasPath("json1.json2.json3[8].field").matches(json), is(false));
}
```

## Dependencies
1. hamcrest
```xml
    <dependency>
          <groupId>org.hamcrest</groupId>
          <artifactId>hamcrest-core</artifactId>
          <version>1.3</version>
    </dependency>
    <dependency>
          <groupId>org.hamcrest</groupId>
          <artifactId>hamcrest-library</artifactId>
          <version>1.3</version>
    </dependency>
```
2. google guava libraries
```xml 
    <dependency>
          <groupId>com.google.guava</groupId>
          <artifactId>guava</artifactId>
          <version>14.0.1</version>
    </dependency>
```
3. org.json 20090211
```xml 
    <dependency>
          <groupId>org.json</groupId>
          <artifactId>json</artifactId>
          <version>20090211</version>
    </dependency>
```
4. junit 4.11 (tests only)
```xml 
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
```

Source Code Quality on Codacy [![Codacy Badge](https://api.codacy.com/project/badge/grade/a72f0f7f33a34c7bb417941324b2fb34)](https://www.codacy.com/app/sudhir-ravindramohan/jsonmatcher)
