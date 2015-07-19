package com.example.ssarabadani.khabarchin_prototype;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.ssarabadani.khabarchin_prototype.client.Handler;
import com.example.ssarabadani.khabarchin_prototype.client.LoginAPI;
import com.example.ssarabadani.khabarchin_prototype.dto.LoginData;

/**
 * Created by SOROOSH on 7/19/15.
 */
public class LoginFragment extends Fragment {
    private LoginAPI loginAPI;


    public LoginFragment(){
        this.loginAPI = new LoginAPI();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view =  inflater.inflate(R.layout.fragment_login_page, container, false);
        Button btnLogin = (Button)view.findViewById(R.id.btnLogin);
        final EditText txtEmail = (EditText)view.findViewById(R.id.txtEmail);
        final EditText txtPass = (EditText)view.findViewById(R.id.txtPass);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginData loginData = new LoginData(txtEmail.getText().toString(), txtPass.getText().toString(), LoginFragment.this.getActivity());

                loginAPI.login(loginData, new Handler() {
                            @Override
                            public void handle() {
                                Toast.makeText(LoginFragment.this.getActivity(), "Hoora", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Handler() {
                            @Override
                            public void handle() {
                                Toast.makeText(LoginFragment.this.getActivity(), "Fuck", Toast.LENGTH_LONG).show();
                            }
                        }
                );
            }
        });
        return view;
    }
}
