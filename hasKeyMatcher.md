#Introduction to the hasKey matcher

# Introduction #
JsonMatchers hasKey API returns a `Matcher<JSONObject>` which can be used for development/tests.

# Details #
```
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