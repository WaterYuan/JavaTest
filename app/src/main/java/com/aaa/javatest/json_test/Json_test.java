package com.aaa.javatest.json_test;

import org.json.JSONException;
import org.json.JSONObject;

public class Json_test {
    public static void main(String[] args) {
        new Json_test().testJson();
    }

    public void testJson() {
        System.out.println("********testJson***************");
//        JsonObject mJsonObject = new JsonObject(); // gson-2.2.4.jar
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("string", "string");
            jsonObject.put("int", 0);
            jsonObject.put("boolean", true);
            System.out.println(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*
2019-09-26 12:50:26.911 21672-21672/com.aaa.javatest I/System.out: ********testJson***************
2019-09-26 12:50:26.912 21672-21672/com.aaa.javatest I/System.out: {"string":"string","int":0,"boolean":true}
    * */
}
