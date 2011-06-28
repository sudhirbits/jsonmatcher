package com.sudhir.json.matchers;

import static org.hamcrest.Matchers.allOf;

import org.hamcrest.Matcher;
import org.json.JSONObject;


public final class JsonMatchers {

	private JsonMatchers() {
		// do not initialize. This is a matcher factory.
	}
	
	public static Matcher<JSONObject> hasKey(String key) {
		return HasKeyMatcher.hasKey(key);
	}
	
	public static Matcher<JSONObject> hasKey(Matcher<String> keyMatcher) {
		return HasKeyMatcher.hasKey(keyMatcher);
	}
	
	public static Matcher<JSONObject> hasKey(Matcher<String>... keyMatchers) {
		return HasKeyMatcher.hasKey(allOf(keyMatchers));
	}

	public static Matcher<JSONObject> hasPath(String path) {
		return HasPathMatcher.hasPath(path);
	}
}
