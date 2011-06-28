package com.sudhir.json.matchers;

import static com.sudhir.json.matchers.JsonMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonMatchersTest {
	@Before
	public void setUp() throws Exception {		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private static final String JSON_STRING_FOR_TEST = "{" +
			"key:value" +
		"}";	
	@Test 
	public void verifyThatMatcherIsAbleToMatchKey() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_FOR_TEST);
		assertThat(json, hasKey("key"));
	}
	
	private static final String JSON_STRING_FOR_COMBINATION_TEST = "{" +
				"startsWithStartContainsAndEndsWithend:value" + 
			"}";
	@SuppressWarnings("unchecked")
	@Test
	public void verifyThatMatcherIsAbleToMatchCombinationOfCriteria() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_FOR_COMBINATION_TEST);
		assertThat(json, 
				hasKey(startsWith("start"),endsWith("end"),containsString("Contains")));		
	}
	
	private static final String JSON_STRING_EMPTY_KEY = "{" +
				"\"\":\"value\"" +
			"}";
	@Test
	public void verifyThatMatcherIsAbleToMatchAnEmptyKey() throws JSONException {		
		JSONObject json = new JSONObject(JSON_STRING_EMPTY_KEY);
		assertThat(json, hasKey(""));
	}
	
	@Test(expected = NullPointerException.class) 
	public void verifyThatPreconditionsAreCheckedForHasKey() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_EMPTY_KEY);
		assertThat(json, hasKey((String) null));
	}
	
	@Test(expected = NullPointerException.class) 
	public void verifyThatPreconditionsAreCheckedForHasKey2() throws JSONException {
		JSONObject json = new JSONObject(JSON_STRING_EMPTY_KEY);
		assertThat(json, hasKey((Matcher<String>) null));
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
				hasPath("json1.json2.json3[0].field"), 
				hasPath("json1.json2.json4.field4")));
	}
		
	@Test
	public void verifyThatMatcherReturnsFalseWhenPathIsNotFound() throws JSONException {		
		JSONObject json = new JSONObject(JSON_STRING_FOR_PATH_TEST);
		assertThat(hasPath("json1.json2.json3[8].field").matches(json), is(false));
	}
}
