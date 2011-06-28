/**
 * 
 */
package com.sudhir.json.matchers.path;

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