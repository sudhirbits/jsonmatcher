package com.sudhir.json.matchers.path;

/**
 * Holds JSONObject/JSONArray and provides the held instance to the get call. 
 * 
 * org.json.JSONObject and JSONArray do not extend a common interface/class. 
 * Hence the APIs to deal with both together have to deal with raw Objects. 
 * JsonTypeHolder is a slightly better abstraction to give some type checks. 
 * @author Sudhir
 * @since 1.0
 *
 * @param <W> org.json.JSONObject/org.json.JSONArray
 */
public class JsonTypeHolder<W> {
	W jsonTypeInstance;
	public JsonTypeHolder(W jsonTypeInstance) {
		this.jsonTypeInstance = jsonTypeInstance;
	}
	
	public W get() {
		return jsonTypeInstance;
	}

	public static <T> JsonTypeHolder<T> of(T instance) {
		return new JsonTypeHolder<T>(instance);
	}

	public String toString() {
		return jsonTypeInstance.toString();
	}
	
	public Class<?> heldClass() {
		return jsonTypeInstance.getClass();
	}
}