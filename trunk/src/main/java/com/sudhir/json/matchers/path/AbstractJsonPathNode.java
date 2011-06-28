/**
 * 
 */
package com.sudhir.json.matchers.path;

import org.json.JSONException;


public abstract class AbstractJsonPathNode<U> implements JsonPathNode<U> {

	protected String nodeKey;
	protected JsonPathNode<?> next;
	public AbstractJsonPathNode(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	public void chainToPath(JsonPathNode<?> jsonNodeInPath) {
		if(next == null) 
			next = jsonNodeInPath;
		else 
			next.chainToPath(jsonNodeInPath);
	}
	
	public boolean doesChainEndHere() {
		return next == null;
	}

	public String print(String tabs) {
		String tabsWithAnotherAdded = tabs + "  ";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getSimpleName()).append(" {\n")
			.append(tabsWithAnotherAdded).append("'nodeKey' -> ").append("'").append(nodeKey).append("'\n");
		if(!doesChainEndHere()) {
			stringBuilder.append(tabsWithAnotherAdded).append("'next' -> ").append(next.print(tabsWithAnotherAdded)).append("\n");
		}
		return stringBuilder.append(tabs).append("}").toString();
	}
	
	@Override
	public String key() {
		return nodeKey;
	}
	
	@Override 
	public JsonPathNode<?> nextInChain() {
		return next;
	}

	@SuppressWarnings("unchecked")
	public <N> JsonTypeHolder<?> fwdToChain(JsonTypeHolder<N> jsonTypeHolder) throws JSONException {
		if(!doesChainEndHere()) {
			return next.process((JsonTypeHolder) jsonTypeHolder);
		} 
		return jsonTypeHolder;
	}
}