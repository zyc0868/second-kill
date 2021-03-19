package com.example.demo.util;
import com.example.demo.domain.entity.UserDO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author: flyboy
 * @Date: 14/03/2021 20:19
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */


public class JsonUtil {

    private static Gson gson = null;

    static{
        gson  = new Gson();
    }

    public static synchronized Gson newInstance(){
        if(gson == null){
            gson =  new Gson();
        }
        return gson;
    }

    public static String getJsonString(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T toBean(String json,Class<T> clz){

        return gson.fromJson(json, clz);
    }

    public static <T> Map<String, T> readJson2MapObj(String json,Class<T> clz){
        Map<String, JsonObject> map = gson.fromJson(json, new TypeToken<Map<String,JsonObject>>(){}.getType());
        Map<String, T> result = new HashMap<>();
        for(String key:map.keySet()){
            result.put(key,gson.fromJson(map.get(key),clz) );
        }
        return result;
    }

    public static <T> T json2Obj(String json,Class<T> clz){
        return gson.fromJson(json,clz);
    }

    public static Map<String, Object> toMap(String json){
        Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String,Object>>(){}.getType());
        return map;
    }

    public static Map<String,String> readJsonStrMap(String json) {
        Map<String, JsonObject> map = gson.fromJson(json, new TypeToken<Map<String,JsonObject>>(){}.getType());
        Map<String,String> result = new HashMap<>();
        for(String key:map.keySet()){
            result.put(key,gson.fromJson(map.get(key),String.class) );
        }
        return result;
    }

    public static  Map<byte[], byte[]> readJsonByteMap(String json) {
        Map<String, JsonPrimitive> map = gson.fromJson(json, new TypeToken<Map<String,JsonPrimitive>>(){}.getType());
        Map<byte[], byte[]> vmap = new HashMap<>();
        for(String key:map.keySet()){
            vmap.put(key.getBytes(),gson.fromJson(map.get(key),String.class).getBytes() );
        }
        return vmap;

    }


    public static <T> List<T> readJson2Array(String json, Class<T> clz){
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        List<T> list  = new ArrayList<>();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, (Type)clz));
        }
        return list;
    }

    public static void main(String[] args) {
        Test test = new Test(10,"zyc");
        String json = getJsonString(test);
        System.out.println(json);

        Test test1 = toBean(json,Test.class);
        System.out.println(test1.age);
    }

}

class Test{
    public int age;
    public String name;
    public Test(int age,String name){
        this.age = age;
        this.name = name;
    }

}