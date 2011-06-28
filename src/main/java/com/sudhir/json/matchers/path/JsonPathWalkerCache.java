package com.sudhir.json.matchers.path;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonPathWalkerCache {
	private static final Map<String, JsonPathWalker> cache = new ConcurrentHashMap<String, JsonPathWalker>();

	public static boolean contains(String path) {
		return cache.containsKey(path);
	}

	public static JsonPathWalker get(String path) {
		return cache.get(path);
	}

	public static JsonPathWalker put(String path, JsonPathWalker jsonPathWalker) {
		cache.put(path, jsonPathWalker); return jsonPathWalker;
	}
}
