package com.example.csh.data_fetch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.csh.data_fetch.RESTcalls.RestCallImplementation;
import com.example.csh.data_fetch.constants.Constants;
import com.example.csh.data_fetch.entities.LoginEntity;
import com.example.csh.data_fetch.functions.CommonFunctions;
import com.example.csh.data_fetch.functions.StartIntent;
import com.example.csh.data_fetch.functions.UserDetails;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String userName = "dsreenevasan@gmail.com";
        String password = "123456789";
        LoginEntity loginEntity = new LoginEntity(userName, password);
        RestCallImplementation.onLogin(loginEntity, new LoginEntity.RestCallInterface() {

            @Override
            public void onLogin(LoginEntity loginEntity, VolleyError volleyError) {
               // CommonFunctions.dismissCustomDialog();
                if (volleyError == null) {
                    Log.d("TAG = ", loginEntity.getUserName().toString());
                    TextView textView = (TextView)findViewById(R.id.textView);
                    textView.setText("Hi " + loginEntity.getAuroraUser().getUserName().toString() + "!\n" + "Your email id is " + loginEntity.getAuroraUser().getEmailId().toString());

                } else if (volleyError != null) {
                    if (loginEntity.getCode() == 401) {
                        CommonFunctions.toastString(loginEntity.getMessage(), MainActivity.this);
                    }else{
                        CommonFunctions.toastString(Constants.NO_INTERNET_CONNECTION, MainActivity.this);
                    }
                }

            }
        }, MainActivity.this);
    }
}
