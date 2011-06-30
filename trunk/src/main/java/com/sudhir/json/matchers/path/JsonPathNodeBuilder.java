package com.sudhir.json.matchers.path;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.base.Splitter;

/**
 * Builds JSON path node implementations according to the type of node 
 * (key/array index) with which the builder is invoked. 
 * 
 * Supports specifying array index as node[7] or node.[7]
 * @author Sudhir
 * @since 1.0
 */
public class JsonPathNodeBuilder {
	private JsonPathNodeBuilder() {}
	/**
	 * Determine the node type (Object/Array) based on the node name pattern.
	 * 
	 * 
	 * Builds and returns JsonPathNode implementations accordingly. 
	 * @param node JSON path node
	 * @return JsonPathNode (of JSONObject/JSONArray)
	 */
	public static JsonPathNode<?> forNode(String node) {
		// pure arrays specification. They can operate only on JSON Array.
		// [1][2][3] or [1]. Note: [1].[2] will not fall through this loop together!
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
		} else if(node.contains("[")) { 
			// JSON Object containing an keyed array or list of arrays.
			// node[0][1][2] or node[1]. node[1].[2] will not reach here together.
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