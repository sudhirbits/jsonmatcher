package com.sudhir.json.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONException;
import org.json.JSONObject;

import com.sudhir.json.matchers.path.JsonPathWalker;

public class HasPathMatcher extends TypeSafeMatcher<JSONObject> {

	private String path;

	public HasPathMatcher(String path) {
		this.path = path;
	}

	public static Matcher<JSONObject> hasPath(String path) {
		return new HasPathMatcher(path);
	}

	@Override
	public boolean matchesSafely(JSONObject item) {
		try {
			return JsonPathWalker.forPath(path).walk(item).get() != null;
		} catch (JSONException e) {
			return false;
		}
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("hasPath that matches: " + path);
	}

}
