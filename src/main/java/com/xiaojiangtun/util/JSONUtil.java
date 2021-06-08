package com.xiaojiangtun.util;

import com.google.gson.*;

import java.lang.reflect.Type;


public class JSONUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Gson gson = null;
    public static Gson gson2 = null;
    static {
        if (gson == null) {
            gson = new GsonBuilder().serializeNulls()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setDateFormat(DATE_FORMAT)
                    .create();
        }
        if (gson2 == null) {
            gson2 = new GsonBuilder().serializeNulls()
                    .setDateFormat(DATE_FORMAT)
                    .create();
        }
    }


    // 对象转json
    public static String objectToJson(Object object) {
        if (object == null) {
            return null;
        }
        return gson.toJson(object);
    }


    public static String toJSON(Object m) {
        return gson.toJson(m);
    }

    public static String toJSON2(Object object) {
        return gson2.toJson(object);
    }

    public static <T> T toObject(String s, Class<T> clzz) {
        return gson.fromJson(s, clzz);
    }

    public static <T> T toObject(String s, Type type) {
        return gson.fromJson(s, type);
    }
    
    public static <T> T jsonToObject(JsonArray s, Type type) {
        return gson.fromJson(s, type);
    }

    public static <T> T jsonToObject(JsonObject s, Class<T> clzz) {
        return gson.fromJson(s, clzz);
    }


}
