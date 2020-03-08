package com.tirtho.diu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static String mySharedPreference="myPrep";
    public static String isLogin="isLogin";

    private Button btnLogin, register;
    private TextView forgotpass;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        preferences =getSharedPreferences(mySharedPreference,Context.MODE_PRIVATE);

        btnLogin= findViewById(R.id.btn_login);
        forgotpass = findViewById(R.id.link_forgotten);
        register = findViewById(R.id.btn_register);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,BrowserActivity.class));
            }
        });


        btnLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                SharedPreferences.Editor editor= preferences.edit();
                editor.putBoolean(isLogin,true);
                editor.commit();
                this.finish();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            break;
        }
    }
}
