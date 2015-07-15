package com.example.ssarabadani.khabarchin_prototype.client;

import android.os.AsyncTask;
import android.util.Log;



import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SOROOSH on 7/15/15.
 */
public final class LoginAPI extends AsyncTask<String,Void,String> {
    public static volatile LoginAPI instance = new LoginAPI();

    private LoginAPI() {
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return this.login("","");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String login(String username, String password) throws IOException {
//        URL url = null;
//        try {
//            url = new URL("http://khabar-chin.com/login");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance();
//
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        CookieManager cm = new CookieManager();
//
//        cookieManager.setCookie(url.toString(),con.getHeaderField("Set-Cookie"));
//        for (String item : con.getHeaderFields().keySet()) {
//            Log.i("INFO", item + ":" + con.getHeaderField(item));
//
//        }
//        con.disconnect();
//        URL login = new URL("http://khabar-chin.com/login");
//        login
        return "";
    }

}
