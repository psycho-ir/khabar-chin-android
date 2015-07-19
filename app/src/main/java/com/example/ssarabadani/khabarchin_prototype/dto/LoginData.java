package com.example.ssarabadani.khabarchin_prototype.dto;

import android.content.Context;

import com.example.ssarabadani.khabarchin_prototype.client.LoginAPI;

/**
 * Created by SOROOSH on 7/19/15.
 */
public class LoginData {
    private final String username;
    private final String password;
    private final Context ctx;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Context getCtx() {
        return ctx;
    }

    public LoginData(String username,String password,Context ctx){

        this.username = username;
        this.password = password;
        this.ctx = ctx;
    }
}
