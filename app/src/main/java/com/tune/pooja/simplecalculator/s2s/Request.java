package com.tune.pooja.simplecalculator.s2s;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Request {

    public AbstractMap.Entry<String, Map<String, String>> post(String event) throws Exception {


        String consumerKey = "";
        String apiKey = "";
//        String timestamp = String.valueOf(Instant.now().getEpochSecond());
        String timestamp = "1544306821";//String.valueOf(Instant.now().getEpochSecond());
        String host = "199221.measure.mobileapptracking.com";
        String httpMethod = "POST";
        Map<String, String> params = getParams(event);
        String path = "/serve"+ "?"+ urlEncodeUTF8(params);
        String url = "https://" + host + path;
        String signature = generateSignature(apiKey, httpMethod, host, path, timestamp, "");
        Map<String, String> headers = getHeaders(consumerKey, signature, timestamp);
        //new CallAPI().execute( headers, url);
//        sendRequest(headers,url);

        AbstractMap.Entry<String, Map<String, String>> rtnval=new AbstractMap.SimpleEntry<>(url, headers);

        return rtnval;
    }


    public StringRequest prepareRequest(final Map<String, String> headers, String urlString){

            StringRequest strRequest = new StringRequest(com.android.volley.Request.Method.POST, urlString,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                System.out.println(new String(error.networkResponse.data, "utf-8"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() {

                    return headers;
                }
            };
            return strRequest;

    }


    public Map<String, String> getParams(String event) {

        Map<String, String> rtnval = new HashMap<>();
        rtnval.put("site_event_name", event);
        rtnval.put("device_ip", "199.75.210.200");
        rtnval.put("site_id", "142455");
        rtnval.put("response_format", "json");
        rtnval.put("advertiser_id", "199221");
        rtnval.put("action", "conversion");
        // rtnval.put("site_event_items", "[{\"name\":\"apples\",\"quantity\":\"3\",\"unit_price\":\".5\"},{\"name\":\"oranges\",\"quantity\",\"4\",\"value\":\"8\"}]");
        //rtnval.put("ios_ifa", "JKI2JO-5958-8188-1BRLO-87322RYAN5693");
        rtnval.put("sdk", "server");
        return rtnval;

    }

    public Map<String, String> getHeaders(String consumerKey, String signature, String timestamp) {

        Map<String, String> rtnval = new HashMap<>();
        rtnval.put("mat-consumer-key", consumerKey);
        rtnval.put("mat-signature", signature);
        rtnval.put("mat-timestamp", timestamp);
        rtnval.put("content-type", "application/json");
        return rtnval;
    }

    public String generateSignature(String apiKey, String httpMethod, String host, String uri, String timestamp, String postParams) throws Exception {

        String stringToSign = httpMethod + "\n" + host + "\n" + uri + "\n" + timestamp + "\n" + postParams;

        return encode(apiKey, stringToSign).replace("=","");


    }

    public String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
    }

    private String urlEncodeUTF8(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }

    private String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
