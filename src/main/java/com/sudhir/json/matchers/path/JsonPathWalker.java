package com.sudhir.json.matchers.path;

import static com.google.common.base.Preconditions.checkArgument;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Splitter;

public class JsonPathWalker {
	JsonPathNode<JSONObject> topNodeInChain;
	
	
	// Not meant to be initialized, use the forPath method.
	private JsonPathWalker(String path) {
		for(String node: Splitter.on(".").split(path)) {
			chainToPath(JsonPathNodeBuilder.forNode(node));
		}
	}
	
	JsonPathNode<JSONObject> topNode() {
		return topNodeInChain;
	}	

	public static JsonPathWalker forPath(String path) {
		checkArgument(path != null && path.contains("."), 
				"Cannot walk a Json path that does not contain '.' seperated key names");
		if(JsonPathWalkerCache.contains(path)) {
			return JsonPathWalkerCache.get(path);
		} else {
			return JsonPathWalkerCache.put(path, new JsonPathWalker(path));
		}
	}
	
	public String print() {
		return topNodeInChain.print("");
	}
	
	@SuppressWarnings("unchecked")
	private void chainToPath(JsonPathNode<?> jsonNodeInPath) {
		if(topNodeInChain == null) 
			topNodeInChain = (JsonPathNode<JSONObject>) jsonNodeInPath;
		else 
			topNodeInChain.chainToPath(jsonNodeInPath);
	}

	public JsonTypeHolder<?> walk(JSONObject item) throws JSONException {
		return topNode().process(JsonTypeHolder.of(item));
	}
}
