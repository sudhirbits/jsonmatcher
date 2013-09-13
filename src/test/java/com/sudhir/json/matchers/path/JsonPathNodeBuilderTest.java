package com.sudhir.json.matchers.path;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JsonPathNodeBuilderTest {
    @Test(expected = IllegalArgumentException.class)
    public void testForNode_whenNullIsPassedShouldRaiseException() throws Exception {
        JsonPathNodeBuilder.forNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForNode_whenEmptyIsPassedShouldRaiseException() throws Exception {
        JsonPathNodeBuilder.forNode("");
    }

    @Test
    public void testForNode_whenAnObjectSpecificationIsPassedShouldReturnAnObjectTypeOfNode() throws Exception {
        assertThat(JsonPathNodeBuilder.forNode("test"), instanceOf(JsonPathNodeObject.class));
    }

    @Test
    public void testForNode_whenAnArraySpecificationIsPassedShouldReturnAnArrayTypeOfNode() throws Exception {
        JsonPathNode<?> pathNode = JsonPathNodeBuilder.forNode("test[0]");
        assertThat(pathNode, instanceOf(JsonPathNodeObject.class));
        assertThat(pathNode.doesChainEndHere(), is(false));
        assertThat(pathNode.nextInChain(), instanceOf(JsonPathNodeArrayOfObjects.class));
        assertThat(pathNode.nextInChain().doesChainEndHere(), is(true));
    }

    @Test
    public void testForNode_complexArraySpecificationWithStarNotation() throws Exception {
        JsonPathNode<?> pathNode = JsonPathNodeBuilder.forNode("test[*][0]");
        assertThat(pathNode, instanceOf(JsonPathNodeObject.class));
        assertThat(pathNode.doesChainEndHere(), is(false));
        assertThat(pathNode.nextInChain(), instanceOf(JsonPathNodeWildCardedArrayOfObjects.class));
        assertThat(pathNode.nextInChain().doesChainEndHere(), is(false));
        assertThat(pathNode.nextInChain().nextInChain(), instanceOf(JsonPathNodeArrayOfObjects.class));
        assertThat(pathNode.nextInChain().nextInChain().doesChainEndHere(), is(true));
    }
}
