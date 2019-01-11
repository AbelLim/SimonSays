package com.example.arx8l.simonsays;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    private EditText setting_name;
    private Switch setting_sfx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getSharedPreferences("PREFERENCES",MODE_PRIVATE);
        editor = settings.edit();
        initView();
    }

    //Initialize all UI elements
    private void initView()
    {
        setting_name = findViewById(R.id.setting_name);
        setting_name.setText(readSetting("user"));
        setting_sfx = findViewById(R.id.setting_sfx);
        if(readSetting("sound")=="true")
        {
            setting_sfx.setChecked(true);
            setting_sfx.setText("On");
        }
        else
        {
            setting_sfx.setChecked(false);
            setting_sfx.setText("Off");
        }

        setting_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editSetting("user",s.toString());
            }
        });
        setting_sfx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    editSetting("sound", "true");
                    setting_sfx.setText("On");
                }
                else
                {
                    editSetting("sound", "false");
                    setting_sfx.setText("Off");
                }
            }
        });
    }

    //Read setting
    private String readSetting(String key)
    {
        String value;
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
