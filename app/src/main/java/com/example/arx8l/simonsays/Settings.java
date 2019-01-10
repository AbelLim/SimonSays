package com.example.arx8l.simonsays;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getPreferences(MODE_PRIVATE);
        editor = settings.edit();

        initView();
    }

    //Initialize all UI elements
    private void initView()
    {
        textView = findViewById(R.id.textView4);
    }

    //Read setting
    private int readSetting(String key)
    {
        int value;
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        value = settings.getInt(key, -1);
        return value;
    }

    //Edit setting
    private void editSetting(String key, int value)
    {
        editor.putInt(key,value);
        editor.commit();
    }

    //Set default settings
    private void defaultSetting()
    {
        editSetting("difficulty", 3);
        editSetting("volume", 5);
        editSetting("sound", 1);
    }

    //Clear setting
    private void clearSetting()
    {
        editor.clear();
        editor.commit();
    }

    @Override
    public void onClick(View v) {

    }
}
