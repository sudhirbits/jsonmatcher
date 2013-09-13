package com.sudhir.json.matchers.path;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonPathNodeObject extends AbstractJsonPathNode<JSONObject> {

	public JsonPathNodeObject(String nodeKey) {
		super(nodeKey);
	}

	@Override
	public JsonTypeHolder<?> process(JsonTypeHolder<JSONObject> holder) throws JSONException {
		return super.fwdToChain(JsonTypeHolder.of(holder.get().get(nodeKey)));
	}	
}