package com.example.sads.honeycontrol.Utils;

import android.content.SharedPreferences;

/**
 * Created by sads on 26/03/17.
 */
public class Util {

    public static boolean isValidText(String text){
        return text.length()> 0;
    }

    public static  String getUserPrefs(SharedPreferences preferences){
        return preferences.getString("user","");
    }

    public static String getPassPrefs(SharedPreferences preferences){
        return preferences.getString("password","");
    }

    public static void removeSharedPreferences(SharedPreferences preferences){
        SharedPreferences.Editor editor= preferences.edit();
        editor.remove("user");
        editor.remove("password");
        editor.apply();

    }
}
