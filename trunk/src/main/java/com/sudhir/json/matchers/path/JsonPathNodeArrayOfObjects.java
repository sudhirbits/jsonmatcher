package com.sudhir.json.matchers.path;

import static com.google.common.base.Preconditions.checkArgument;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Represents a array object in the JSON path. 
 * 
 * This object is initialized with and stores the array index. It can be called 
 * upon to process on JSONArray. The array index will be used for JSONArray 
 * processing.
 * 
 * @author Sudhir
 * @since 1.0
 */
public class JsonPathNodeArrayOfObjects extends AbstractJsonPathNode<JSONArray> {

	public JsonPathNodeArrayOfObjects(String arrayIndexBetweenBraces) {
		super(arrayIndexBetweenBraces);
		verifyThatArrayIndexIsSpecifiedCorrectly(arrayIndexBetweenBraces);
	}

	private void verifyThatArrayIndexIsSpecifiedCorrectly(
			String arrayIndexBetweenBraces) {
		checkArgument(
		// start with [ contains a number in between and ends with ]
				arrayIndexBetweenBraces.matches("^\\[\\d+\\]$") &&
				// starts with "[0" but is not "[0]" e.g. 0123 is not valid.
						// TODO collapse these two conditions into one with a
						// neatly compiled REGEX.
						(!arrayIndexBetweenBraces.startsWith("[0") || arrayIndexBetweenBraces
								.equals("[0]")),
				// message for the exception.
				arrayIndexBetweenBraces
						+ " is not a valid array index for a JSON array.");
	}

	@Override
	public JsonTypeHolder<?> process(JsonTypeHolder<JSONArray> holder)
			throws JSONException {
		return fwdToChain(JsonTypeHolder.of(elementAtArrayIndex(holder)));
	}

	private Object elementAtArrayIndex(JsonTypeHolder<JSONArray> holder)
			throws JSONException {
		// holder.get is guaranteed to return a JSONArray. But the subsequent
		// get with arrayIndex could return a Object/Array. 
		return holder.get().get(arrayIndex());
	}

	protected int arrayIndex() {
		// The argument check as precondition ensures that there will be a
		// valid number contained in []
		return Integer.valueOf(nodeKey.replaceAll("\\[", "").replaceAll("\\]",
				""));
	}
}