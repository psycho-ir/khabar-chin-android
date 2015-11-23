package com.example.ssarabadani.khabarchin_prototype.client;

import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ssarabadani.khabarchin_prototype.Constants.Constants;
import com.example.ssarabadani.khabarchin_prototype.dto.LoginData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SOROOSH on 7/15/15.
 */
public final class LoginAPI {

    public LoginAPI() {

    }

    public void login(final LoginData loginData, final Handler successHandler, final Handler errorHandler) {

        StringRequest request = new StringRequest(Request.Method.POST, Constants.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("INFO", "Got " + response + " from server");
                        if (response.equals("OK")) {
                            successHandler.handle();
                        } else {
                            errorHandler.handle();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", "Error in Login " + error);
                errorHandler.handle();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", loginData.getUsername());
                params.put("password", loginData.getPassword());
                return params;
            }
        };

        QueueInstance.getQueue().add(request);


    }

}
