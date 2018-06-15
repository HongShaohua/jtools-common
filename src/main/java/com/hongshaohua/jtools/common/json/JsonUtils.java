package com.hongshaohua.jtools.common.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

	public static String obj2Json(Object obj) throws Exception {
		
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(obj);
	}
	
	public static <T> T json2Obj(String jsonStr, Class<T> cls) throws Exception {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.readValue(jsonStr, cls);
	}
	
	public static <T> T[] json2Array(String jsonStr, Class<T> cls) throws Exception {
		ObjectMapper objMapper = new ObjectMapper();
		JavaType type = objMapper.getTypeFactory().constructArrayType(cls);
		return objMapper.readValue(jsonStr, type);
	}
	
	public static <T> List<T> json2List(String jsonStr, Class<T> cls) throws Exception {
		ObjectMapper objMapper = new ObjectMapper();
		JavaType type = objMapper.getTypeFactory().constructCollectionType(ArrayList.class, cls);
		return objMapper.readValue(jsonStr, type);
	}

	public static <T> T json2ObjIgnorable(String jsonStr, Class<T> cls) throws Exception {
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
		return objMapper.readValue(jsonStr, cls);
	}

	public static <T> List<T> json2ListIgnorable(String jsonStr, Class<T> cls) throws Exception {
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
		JavaType type = objMapper.getTypeFactory().constructCollectionType(ArrayList.class, cls);
		return objMapper.readValue(jsonStr, type);
	}
}
