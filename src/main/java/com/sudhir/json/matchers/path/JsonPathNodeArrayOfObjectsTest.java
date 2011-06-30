package com.sudhir.json.matchers.path;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonPathNodeArrayOfObjectsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

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
	public void verifyThatTestClassWorksWithRightArguments() {
		JsonPathNodeArrayOfObjects pathObject = new JsonPathNodeArrayOfObjects("[0]");
		assertThat(pathObject, notNullValue(JsonPathNodeArrayOfObjects.class));
		assertThat(pathObject.key(), is(equalTo("[0]")));
		assertThat(pathObject.index(), is(0));
	}
}
