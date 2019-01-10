package com.example.arx8l.simonsays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Game extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();
    }

    //Initialize all UI elements
    private void initView()
    {

    }

    @Override
    public void onClick(View v) {

    }

    private void endGame()
    {
        startActivity(new Intent(this, HighScore.class));
        finish();
    }
}
