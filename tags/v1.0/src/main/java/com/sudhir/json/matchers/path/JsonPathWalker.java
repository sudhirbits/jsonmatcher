package com.sudhir.json.matchers.path;

import static com.google.common.base.Preconditions.checkArgument;
import static com.sudhir.json.matchers.path.JsonPathWalker.JsonPathWalkerCache.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	
	public JsonPathNode<JSONObject> topNode() {
		return topNodeInChain;
	}	

	public static JsonPathWalker forPath(String path) {
		checkArgument(path != null && path.contains("."), 
				"Cannot walk a Json path that does not contain '.' seperated key names");
		if(containedInCache(path)) {
			return getFromCache(path);
		} else {
			return storeInCache(path, new JsonPathWalker(path));
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
	
	protected static class JsonPathWalkerCache {
		private static final Map<String, JsonPathWalker> cache = new ConcurrentHashMap<String, JsonPathWalker>();

		public static boolean containedInCache(String path) {
			return cache.containsKey(path);
		}

		public static JsonPathWalker getFromCache(String path) {
			return cache.get(path);
		}

		public static JsonPathWalker storeInCache(String path, JsonPathWalker jsonPathWalker) {
			cache.put(path, jsonPathWalker); return jsonPathWalker;
		}
	}
}
