has Path matcher API

# Introduction #

JsonMatchers exposes a matcher API hasPath that takes in a JSON path (as string) and returns a `Matcher<JSONObject>`

The path is similar to bean path of java object graph.
```
Samples
  * root.node1.node2.node3 
  * root.node1[3].node2.node3[0][1].node4
  * root.node1.[3].node2[0].[6]
  * and so on.
```

# Details #
```
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