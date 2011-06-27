package com.sudhir.json.matchers;

import java.util.Iterator;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONObject;

import static com.google.common.base.Preconditions.*;

public class HasKeyMatcher extends TypeSafeMatcher<JSONObject> {
	private Matcher<String> keyMatcher;	
	
	public HasKeyMatcher(Matcher<String> keyMatcher) {
		super();
		this.keyMatcher = keyMatcher;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean matchesSafely(JSONObject item) {
		Iterator<String> iterator = item.keys();
		while(iterator.hasNext()) {
			String next = iterator.next();
			if(keyMatcher.matches(next)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("hasKey that matches: ");
		keyMatcher.describeTo(description);
	}
	
	public static Matcher<JSONObject> hasKey(String key) {
		checkNotNull(key, "Cannot initialize with a NULL matcher");
		return hasKey(equalTo(key));
	}
	
	public static Matcher<JSONObject> hasKey(Matcher<String> keyMatcher) {
		checkNotNull(keyMatcher, "Cannot initialize with a NULL matcher");
		return new HasKeyMatcher(keyMatcher);
	}
}
