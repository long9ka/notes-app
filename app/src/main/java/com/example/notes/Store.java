package com.example.notes;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Store extends AppCompatActivity {

    public List<Model> modelList;

    private SharedPreferences sharedPreferences;
    private Gson gson;

    Store(Context context) {
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("data", MODE_PRIVATE);
    }

    public void getStore() {
        String json = sharedPreferences.getString("list-items", null);
        Type type = new TypeToken<List<Model>>(){}.getType();
        modelList = gson.fromJson(json, type);
    }

    public void setStore() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(modelList);
        editor.putString("list-items", json).apply();
    }
}
