package com.sudhir.json.matchers.path;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.sudhir.json.matchers.path.JsonPathWalker;

public class JsonPathWalkerTest {

	@Test
	public void testJsonPathWalker() {
		JsonPathWalker pathWalker = JsonPathWalker.forPath("json.json1.json3.[0].key");
		System.out.println(pathWalker.print());
		JsonPathNode<?> node = pathWalker.topNode();
		assertNotNull(node);
		assertThat(node.key(), is("json"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("json1"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("json3"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("[0]"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("key"));
	}

	@Test
	public void testJsonPathWalkerArrayOfArrays() {
		JsonPathWalker pathWalker = JsonPathWalker.forPath("json.json1[0].json3.[0][1].key");
		System.out.println(pathWalker.print());
		JsonPathNode<?> node = pathWalker.topNode();
		assertNotNull(node);
		assertThat(node.key(), is("json"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("json1"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("[0]"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("json3"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("[0]"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("[1]"));
		node = node.nextInChain();
		assertNotNull(node); assertThat(node.key(), is("key"));
	}
	
	private static final String JSON_FOR_TEST = 
		"{" +
			"json1: {" +
				"json2: {" +
					"json3: [" +
						"{" +
							"field:value" + 
						"}" +
					"]" +
				"}" + 
			"}" + 
		"}";
	
	@SuppressWarnings("unchecked")
	@Test
	public void testChainIsAbleToRetrieve() throws JSONException {
		JsonPathWalker pathWalker = JsonPathWalker.forPath("json1.json2.json3[0]");
		System.out.println(pathWalker.print());
		JsonPathNode<JSONObject> node = (JsonPathNode<JSONObject>) pathWalker.topNode();
		JsonTypeHolder returned = node.process(JsonTypeHolder.of(new JSONObject(JSON_FOR_TEST)));
		System.out.println(returned);
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected=JSONException.class)
	public void testChainIsAbleToRetrieve1() throws JSONException {
		JsonPathWalker pathWalker = JsonPathWalker.forPath("json1.json2.json56[0]");
		System.out.println(pathWalker.print());
		JsonPathNode<JSONObject> node = (JsonPathNode<JSONObject>) pathWalker.topNode();
		JsonTypeHolder returned = node.process(JsonTypeHolder.of(new JSONObject(JSON_FOR_TEST)));
		System.out.println(returned);
	}
}
