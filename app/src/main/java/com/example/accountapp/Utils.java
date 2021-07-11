package com.example.accountapp;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void toast(Context context, String info){
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
