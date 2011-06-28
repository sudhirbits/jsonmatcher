/**
 * 
 */
package com.sudhir.json.matchers.path;

import org.json.JSONException;

public interface JsonPathNode<T> {
	void chainToPath(JsonPathNode<?> jsonNodeInPath);		
	String print(String tabs);
	boolean doesChainEndHere();
	JsonTypeHolder<?> process(JsonTypeHolder<T> json) throws JSONException;
	String key();
	JsonPathNode<?> nextInChain();
}