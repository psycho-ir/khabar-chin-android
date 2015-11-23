package com.example.ssarabadani.khabarchin_prototype.client;

import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.ssarabadani.khabarchin_prototype.dto.AcceptedData;
import com.example.ssarabadani.khabarchin_prototype.dto.LoginData;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SOROOSH on 7/15/15.
 */
public final class UserAPI {

    public UserAPI() {

    }

    public void isUsernameAccepted(String username, final Handler successHandler, final Handler errorHandler) {
        AcceptedData data =  new AcceptedData();
        data.Username = username;
        final String requestBody = new Gson().toJson(data);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://phoneservice.fanavard.com/Service.svc/IsAccepted", requestBody,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
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
        }

        );

        QueueInstance.getQueue().add(request);
    }
}

