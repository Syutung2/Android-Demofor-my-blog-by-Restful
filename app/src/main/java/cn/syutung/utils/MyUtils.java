package cn.syutung.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MyUtils {
    public static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : Objects.requireNonNull(requestDataMap.get(key)));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }

    public static RequestBody toRequestBody(String s)
    {
        return RequestBody.create(
                MediaType.parse("text/plain"),s);
    }



}
