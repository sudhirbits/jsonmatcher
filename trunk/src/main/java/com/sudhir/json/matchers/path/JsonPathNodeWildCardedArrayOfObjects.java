package com.sudhir.json.matchers.path;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Represents a wild-card array specification in the JSON path.
 *
 * When called to process on JSONArray, will search each element of the
 * JSONArray till a match is found.
 *
 * @author Sudhir
 * @since 1.1
 */
public class JsonPathNodeWildCardedArrayOfObjects extends AbstractJsonPathNode<JSONArray> {
    public JsonPathNodeWildCardedArrayOfObjects() {
        super("[*]");
    }

    @Override
    public JsonTypeHolder<?> process(JsonTypeHolder<JSONArray> holder) throws JSONException {
        for(int i = 0; i < holder.get().length(); i++) {
            JsonTypeHolder<?> typeHolder = fwdToChain(JsonTypeHolder.of(holder.get().get(i)));
            if(typeHolder != null && typeHolder.get() != null) return typeHolder;
        }
        return JsonTypeHolder.of(null);
    }
}
