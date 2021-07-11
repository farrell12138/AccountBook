package com.example.accountapp;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void toast(Context context, String info){
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static final Boolean equals(String p, String t){
        for(int i = 0; i < p.length(); i ++){
            if(p.charAt(i) != t.charAt(i) && Math.abs(p.charAt(i) - t.charAt(i)) != 32){
                return false;
            }
        }
        return true;
    }
}
