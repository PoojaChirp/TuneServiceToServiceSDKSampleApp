package com.tune.pooja.simplecalculator.s2s;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Request {

    public boolean post(){

        return false;
    }


    public Map<String, String> getParams(){

        Map<String, String> rtnval = new HashMap<>();
        rtnval.put("action", "conversion");
                rtnval.put("action", "conversion");
                rtnval.put("advertiser_id", "advertiser_id");
                rtnval.put("device_ip", "199.75.210.200");
                rtnval.put("site_id", "142358");
                rtnval.put("site_event_name", "customPurchaseTest");
                rtnval.put("site_event_items", "[{\"name\":\"apples\",\"quantity\":\"3\",\"unit_price\":\".5\"},{\"name\":\"oranges\",\"quantity\",\"4\",\"value\":\"8\"}]");
                rtnval.put("ios_ifa", "JKI2JO-5958-8188-1BRLO-87322RYAN5693");
                rtnval.put("response_format", "json");
                rtnval.put("sdk", "server");
        return rtnval;

    }

    public Map<String, String> getHeaders(String consumerKey, String signature, String timestamp){

        Map<String, String> rtnval = new HashMap<>();
        rtnval.put("mat-consumer-key", consumerKey);
        rtnval.put("mat-signature", signature);
        rtnval.put("mat-timestamp",timestamp );
        rtnval.put("content-type","application/json");
        return rtnval;
    }

    public String generateSignature(String apiKey, String httpMethod, String host, String uri, String timestamp, String postParams) throws Exception {

        String stringToSign = httpMethod + "\n" + host + "\n" + uri + "\n" + timestamp + "\n" + postParams;

        return encode(apiKey,stringToSign );


    }

    public  String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
    }
}
