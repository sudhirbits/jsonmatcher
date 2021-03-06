package com.sudhir.json.matchers.path;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class JsonPathNodeArrayOfObjectsTest {

	@Test(expected = IllegalArgumentException.class)
	public void verifyThatTestClassFailsFastWhenInitializedWithInvalidNodeKey() {
		new JsonPathNodeArrayOfObjects("[invalid]");
		fail("Expected IllegalArgumentException but was not thrown");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verifyThatTestClassFailsFastWhenInitializedWithInvalidNodeKey2() {
		new JsonPathNodeArrayOfObjects("[0d]");
		fail("Expected IllegalArgumentException but was not thrown");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verifyThatTestClassFailsFastWhenInitializedWithInvalidNodeKey3() {
		new JsonPathNodeArrayOfObjects("[01]");
		fail("Expected IllegalArgumentException but was not thrown");
	}
	
	@Test 
	public void verifyThatTestClassWorksWithRightArgumentsZero() {
		JsonPathNodeArrayOfObjects pathObject = new JsonPathNodeArrayOfObjects("[0]");
		assertThat(pathObject, notNullValue(JsonPathNodeArrayOfObjects.class));
		assertThat(pathObject.key(), is(equalTo("[0]")));
		assertThat(pathObject.arrayIndex(), is(0));
	}
	
	@Test 
	public void verifyThatTestClassWorksWithRightArguments() {
		JsonPathNodeArrayOfObjects pathObject = new JsonPathNodeArrayOfObjects("[23]");
		assertThat(pathObject, notNullValue(JsonPathNodeArrayOfObjects.class));
		assertThat(pathObject.key(), is(equalTo("[23]")));
		assertThat(pathObject.arrayIndex(), is(23)); 
	}
}
