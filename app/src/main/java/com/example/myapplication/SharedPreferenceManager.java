package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private SharedPreferences sharedPreferences;
    private Context context;


    public SharedPreferenceManager (Context context){
        this.context=context;
       this.sharedPreferences=context.getSharedPreferences(this.context.getString(R.string.SharedPref),context.MODE_PRIVATE); // opening the file and returning a shared preference object
   }

public String getName(String a){
    return this.sharedPreferences.getString(a,null);
}

public int getInt(String a){
        return this.sharedPreferences.getInt(a,0);
}

public void SaveName(String a, String name){
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    editor.putString(a,name);
    editor.apply();
}

public void SaveCount(String a, int Input){
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    editor.putInt(a,Input);
    editor.apply();
}




}
