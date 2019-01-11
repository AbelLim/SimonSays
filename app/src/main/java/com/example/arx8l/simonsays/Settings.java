package com.example.arx8l.simonsays;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {

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

    }

    //Read setting
    private String readSetting(String key)
    {
        String value;
        SharedPreferences settings = getPreferences(MODE_PRIVATE);
        value = settings.getString(key, "Error");
        return value;
    }

    //Edit setting
    private void editSetting(String key, String value)
    {
        editor.putString(key,value);
        editor.commit();
    }

    //Set default settings
    private void defaultSetting()
    {
        editSetting("user", "Player");
        editSetting("sound", "true");
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
