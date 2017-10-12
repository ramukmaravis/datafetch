package com.example.csh.data_fetch.functions;

import android.app.Activity;
import android.content.Intent;

import com.example.csh.data_fetch.MainActivity;


public class StartIntent {

    public static void startLoginActivity(Activity activity){

        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

}
