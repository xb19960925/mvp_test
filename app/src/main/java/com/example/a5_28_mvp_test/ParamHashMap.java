package com.example.a5_28_mvp_test;

import java.util.HashMap;

public class ParamHashMap extends HashMap<String,Object> {
    public ParamHashMap add(String key,Object value){
        this.put(key,value);
        return this;
    }
}
