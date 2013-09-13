package com.sudhir.json.matchers;

import org.hamcrest.Matcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static com.sudhir.json.matchers.JsonMatchers.hasJsonKey;
import static com.sudhir.json.matchers.JsonMatchers.hasJsonPath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JsonMatchersTest {
	private static final String JSON_STRING_FOR_TEST = "{" +
			"key:value" +
		"}";

	@Test 
	public void verifyThatMatcherIsAbleToMatchKey() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_FOR_TEST);
		assertThat(json, hasJsonKey("key"));
	}
	
	private static final String JSON_STRING_FOR_COMBINATION_TEST = "{" +
				"startsWithStartContainsAndEndsWithend:value" + 
			"}";

	@SuppressWarnings("unchecked")
	@Test
	public void verifyThatMatcherIsAbleToMatchCombinationOfCriteria() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_FOR_COMBINATION_TEST);
		assertThat(json, 
				hasJsonKey(startsWith("start"),endsWith("end"),containsString("Contains")));		
	}
	
	private static final String JSON_STRING_EMPTY_KEY = "{" +
				"\"\":\"value\"" +
			"}";
	@Test
	public void verifyThatMatcherIsAbleToMatchAnEmptyKey() throws JSONException {		
		JSONObject json = new JSONObject(JSON_STRING_EMPTY_KEY);
		assertThat(json, hasJsonKey(""));
	}
	
	@Test(expected = NullPointerException.class) 
	public void verifyThatPreconditionsAreCheckedForHasKey() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_EMPTY_KEY);
		assertThat(json, hasJsonKey((String) null));
	}
	
	@Test(expected = NullPointerException.class) 
	public void verifyThatPreconditionsAreCheckedForHasKey2() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_EMPTY_KEY);
		assertThat(json, hasJsonKey((Matcher<String>) null));
	}
	
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
				hasJsonPath("json1.json2.json3[0].field"), 
				hasJsonPath("json1.json2.json4.field4")));
	}
		
	@Test
	public void verifyThatMatcherReturnsFalseWhenPathIsNotFound() throws JSONException {		
		JSONObject json = new JSONObject(JSON_STRING_FOR_PATH_TEST);
		assertThat(hasJsonPath("json1.json2.json3[8].field").matches(json), is(false));
	}

    @Test
    public void verifyThatMatcherIsAbleToLocatePathWithStarExpression() throws JSONException {
        JSONObject json = new JSONObject(JSON_STRING_FOR_PATH_TEST);
        assertThat(json, hasJsonPath("json1.json2.json3[*].field"));
    }

    @Test
    public void verifyThatMatcherIsAbleToLocatePathWithStarWithDotExpression() throws JSONException {
        JSONObject json = new JSONObject(JSON_STRING_FOR_PATH_TEST);
        assertThat(json, hasJsonPath("json1.json2.json3.[*].field"));
    }

    @Test
    public void verifyThatMatcherIsAbleFailToLocatePathWithStarExpression_whenThePathDoesNotExist() throws JSONException {
        JSONObject json = new JSONObject(JSON_STRING_FOR_PATH_TEST);
        assertThat(json, not(hasJsonPath("json1.json2.json3[*].field1")));
    }
}
