package com.sudhir.json.matchers;

import org.hamcrest.Matcher;
import org.json.JSONObject;

import static org.hamcrest.core.AllOf.allOf;

/**
 * Builds Matcher&lt;JSONObject&gt; instances for JSON key/value 
 * inspections. 
 * 
 * @author Sudhir
 * @since 1.0
 */
public final class JsonMatchers {

	private JsonMatchers() {
		// do not initialize. This is a matcher factory.
	}

	/**
	 * Builds a Matcher&lt;JSONObject&gt; instance that can match 
	 * if a key with name specified is contained in the JSONObject.
	 *  
	 * @param key key name
	 * @return Matcher&lt;JSONObject&gt;
	 * @since 1.0
	 */
	public static Matcher<JSONObject> hasJsonKey(String key) {
		return HasJsonKeyMatcher.hasJsonKey(key);
	}

	/**
	 * Builds a Matcher&lt;JSONObject&gt; instance that can match 
	 * if a key (String) that can be matched by the Matcher&lt;String&gt; which
	 * is provided as the parameter. 
	 * 
	 * @param keyMatcher Matcher&lt;String&gt;
	 * @return Matcher&lt;JSONObject&gt;
	 * @since 1.0
	 */
	public static Matcher<JSONObject> hasJsonKey(Matcher<String> keyMatcher) {
		return HasJsonKeyMatcher.hasJsonKey(keyMatcher);
	}

	/**
	 * Builds a Matcher&lt;JSONObject&gt; instance that can match 
	 * if a key (String) that can be matched by all Matcher&lt;String&gt;s that 
	 * are provided as the parameter.
	 * @param keyMatchers Matcher&lt;String&gt;...
	 * @return Matcher&lt;JSONObject&gt;
	 * @since 1.0
	 */
	public static Matcher<JSONObject> hasJsonKey(Matcher<String>... keyMatchers) {
		return HasJsonKeyMatcher.hasJsonKey(allOf(keyMatchers));
	}

	/**
	 * Builds a Matcher&lt;JSONObject&gt; instance that can match if the JSON
	 * path specified is contained. 
	 * 
	 * <p>
	 * Sample formats: 
	 * <ol>
	 * <li>level1.level2.level3[1].level3.value</li>
	 * <li>level1.level2.level3.[1].level3.value</li>
	 * <li>level1.level2.level3[1][1].level3.value</li> 
	 * <li>level1.level2.level3.[1][1].level3.value</li>
	 * </p> 
	 * @param path JSON path
	 * @return Matcher&lt;JSONObject&gt;
	 * @since 1.0
	 */
	public static Matcher<JSONObject> hasJsonPath(String path) {
		return HasJsonPathMatcher.hasJsonPath(path);
	}
}
