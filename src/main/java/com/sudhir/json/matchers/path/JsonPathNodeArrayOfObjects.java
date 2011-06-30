/**
 * 
 */
package com.sudhir.json.matchers.path;

import static com.google.common.base.Preconditions.checkArgument;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonPathNodeArrayOfObjects extends AbstractJsonPathNode<JSONArray> {

	public JsonPathNodeArrayOfObjects(String nodeKey) {
		super(nodeKey);
		verifyThatArrayIndexIsSpecifiedCorrectly(nodeKey);
	}

	private void verifyThatArrayIndexIsSpecifiedCorrectly(String nodeKey) {
		checkArgument(
				// start with [ contains a number in between and ends with ]
				nodeKey.matches("^\\[\\d+\\]$") &&
				// starts with "[0" but is not "[0]" e.g. 0123 is not valid.
				// TODO collapse these two conditions into one with a
				// neatly compiled REGEX.
				nodeKey.startsWith("[0") && nodeKey.equals("[0]"),
				// message for the exception.
				nodeKey + "is not a selector for a JSON array.");
	}

	@Override
	public JsonTypeHolder<?> process(JsonTypeHolder<JSONArray> holder)
			throws JSONException {
		int index = index();
		return fwdToChain(JsonTypeHolder.of(holder.get().get(index)));
	}

	protected int index() {
		// The argument check as precondition ensures that there will be a 
		// valid number contained in []
		return Integer.valueOf(nodeKey.replaceAll("\\[", "").replaceAll("\\]",
				""));
	}
}