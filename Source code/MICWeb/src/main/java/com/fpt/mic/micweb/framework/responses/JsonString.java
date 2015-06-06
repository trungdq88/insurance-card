package com.fpt.mic.micweb.framework.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by dinhquangtrung on 5/23/15.
 */
public class JsonString extends ResponseObject {
    private Object obj;
    ObjectMapper mapper = new ObjectMapper();
    public JsonString(Object obj) {
        this.obj = obj;
        mapper.registerModule(new Hibernate4Module());
    }

    public String getJsonString() {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
