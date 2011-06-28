/**
 * 
 */
package com.sudhir.json.matchers.path;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Splitter;

public class JsonPathNodeBuilder {
	private JsonPathNodeBuilder() {}
	public static JsonPathNode<?> forNode(String node) {
		// pure arrays specification. They can operate only on JSON Array.
		if(node.contains("[") && node.startsWith("[")) {
			JsonPathNode<JSONArray> firstInArray = null;
			for(String partsOfTheArray : Splitter.on("[").omitEmptyStrings().split(node)) {
				if(firstInArray == null) {
					firstInArray = new JsonPathNodeArrayOfObjects("[" + partsOfTheArray);
				} else {
					firstInArray.chainToPath(new JsonPathNodeArrayOfObjects("[" + partsOfTheArray));
				}
			}
			return firstInArray;
		} else if(node.contains("[")) { // JSON Object containing an keyed array or list of arrays.
			JsonPathNode<JSONObject> jsonThatContainsArray = null;
			for(String partsOfTheArray : Splitter.on("[").omitEmptyStrings().split(node)) {
				if(jsonThatContainsArray == null) {
					jsonThatContainsArray = new JsonPathNodeObject(partsOfTheArray);
				} else {
					jsonThatContainsArray.chainToPath(new JsonPathNodeArrayOfObjects("[" + partsOfTheArray));
				}
			}
			return jsonThatContainsArray;
		} else {
			return new JsonPathNodeObject(node);
		}
	}
}