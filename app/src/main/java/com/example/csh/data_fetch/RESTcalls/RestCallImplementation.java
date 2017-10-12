package com.example.csh.data_fetch.RESTcalls;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.csh.data_fetch.constants.Constants;
import com.example.csh.data_fetch.entities.InitApiEntity;
import com.example.csh.data_fetch.entities.LoginEntity;
import com.google.gson.Gson;

import org.json.JSONObject;

public class RestCallImplementation {

    static RequestQueue queue;
    private static final String BASE_URL = "https://api.myjson.com/bins/krdwt";

    private static String getAbsoluteURL(String relativeURL){

        return (BASE_URL + relativeURL);
    }

    public static void onLogin(final LoginEntity loginEntity, final LoginEntity.RestCallInterface restCallInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = BASE_URL;
        JSONObject postParams = loginEntity.getJSONObjectAsParams();
        Log.d("","" + postParams);
        JSONBaseRequest postRequest = new JSONBaseRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("",""+ response);
                            Gson gson = new Gson();
                            LoginEntity newSuccessLoginEntity = gson.fromJson(response.toString(),LoginEntity.class);
                            loginEntity.setAuroraUser(newSuccessLoginEntity.getAuroraUser());
                            restCallInterface.onLogin(loginEntity,null);

                        } catch (Exception e) {
                            restCallInterface.onLogin(loginEntity,new VolleyError());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(error.networkResponse != null && error.networkResponse.data != null){
                    Gson gson = new Gson();
                    LoginEntity newErrorLoginEntity = gson.fromJson(new String(error.networkResponse.data),LoginEntity.class);
                    Log.d("TAG=", newErrorLoginEntity.toString());
                    if(newErrorLoginEntity.getMessage() != null){
                        loginEntity.setMessage(newErrorLoginEntity.getMessage());
                        loginEntity.setCode(newErrorLoginEntity.getCode());
                    }
                }
                restCallInterface.onLogin(loginEntity,new VolleyError());
            }
        },30000,0){

        };
        queue.add(postRequest);
    }

    public static void initAPIEntity(InitApiEntity initApiEntity,final InitApiEntity.RestCallInterface restCallInterface,final Context context){

        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = "http://myjson.com/w91vx";
        //JSONObject postParams = initApiEntity.getInitAPIParams();
        JSONBaseRequest postRequest = new JSONBaseRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new Gson();
                        InitApiEntity newInitSuccessEntity = gson.fromJson(response.toString(),InitApiEntity.class);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null && error.networkResponse.data != null){
                    Gson gson = new Gson();
                }
            }
        },3000,0){

        };

    }

}
