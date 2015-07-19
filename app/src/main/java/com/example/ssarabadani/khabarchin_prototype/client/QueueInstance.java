package com.example.ssarabadani.khabarchin_prototype.client;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by SOROOSH on 7/19/15.
 */
public class QueueInstance {
    private static RequestQueue requestQueue;
    public static void prepare(Context ctx){

        if (requestQueue == null ){
            requestQueue = Volley.newRequestQueue(ctx);
        }
    }

    public static RequestQueue getQueue(){
        return requestQueue;
    }
}
