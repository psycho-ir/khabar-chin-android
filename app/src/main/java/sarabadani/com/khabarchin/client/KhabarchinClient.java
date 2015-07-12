package sarabadani.com.khabarchin.client;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.List;

import sarabadani.com.khabarchin.model.NewsCategory;

/**
 * Created by SOROOSH on 7/12/15.
 */
public class KhabarchinClient implements Response.Listener<JSONArray> , Response.ErrorListener{

    public KhabarchinClient() {

    }

    public List<NewsCategory> getAllCategories(Context ctx){


        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonRequest jsonRequest = new JsonArrayRequest(Request.Method.GET,"",new JSONArray(),this,this) ;
        Request req = queue.add(jsonRequest);

        return null;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(JSONArray jsonArray) {

    }
}
