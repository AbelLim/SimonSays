package com.example.arx8l.simonsays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private Button btnStartGame;
    private Button btnHighScore;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initView();
    }

    //Initialize all UI elements
    private void initView()
    {
        btnStartGame = findViewById(R.id.btn_startgame);
        btnHighScore = findViewById(R.id.btn_highscore);
        btnSettings = findViewById(R.id.btn_settings);

        btnStartGame.setOnClickListener(this);
        btnHighScore.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
    }

    //Handle onClick events
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_startgame:
                startActivity(new Intent(this, Game.class));
                break;
            case R.id.btn_highscore:
                startActivity(new Intent(this, HighScore.class));
                break;
            case R.id.btn_settings:
                startActivity(new Intent(this, Settings.class));
                break;
            default:
                break;
        }
    }
}
