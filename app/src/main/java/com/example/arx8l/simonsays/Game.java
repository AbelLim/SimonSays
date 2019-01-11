package com.example.arx8l.simonsays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences settings;
    private List<Integer> sequence;
    private boolean isPlayerTurn;
    private int currentKey;

    private int score;

    private Button btn_tl;
    private Button btn_tr;
    private Button btn_bl;
    private Button btn_br;
    private TextView game_status;
    private TextView game_score;

    private MediaPlayer sound1;
    private MediaPlayer sound2;
    private MediaPlayer sound3;
    private MediaPlayer sound4;
    private MediaPlayer failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initView();

        startGame();
    }

    //Initialize all UI elements
    private void initView()
    {
        score = 0;
        sequence = new ArrayList<>();
        settings = getSharedPreferences("PREFERENCES",MODE_PRIVATE);

        game_status = findViewById(R.id.game_status);
        game_score = findViewById(R.id.game_score);
        game_score.setText(String.format("%06d",score));

        btn_tl = findViewById(R.id.btn_tl);
        btn_tr = findViewById(R.id.btn_tr);
        btn_bl = findViewById(R.id.btn_bl);
        btn_br = findViewById(R.id.btn_br);

        btn_tl.setOnClickListener(this);
        btn_tr.setOnClickListener(this);
        btn_bl.setOnClickListener(this);
        btn_br.setOnClickListener(this);

        sound1 = MediaPlayer.create(this, R.raw.sound1);
        sound2 = MediaPlayer.create(this, R.raw.sound2);
        sound3 = MediaPlayer.create(this, R.raw.sound3);
        sound4 = MediaPlayer.create(this, R.raw.sound4);
        failure = MediaPlayer.create(this, R.raw.failure);

    }

    @Override
    public void onClick(View v)
    {
        if(isPlayerTurn)
        {
            switch (v.getId())
            {
                case R.id.btn_tl:
                    flashButton(btn_tl, getColor(R.color.tl_dark), getColor(R.color.tl_light));
                    compareSequence(0);
                    break;
                case R.id.btn_tr:
                    flashButton(btn_tr, getColor(R.color.tr_dark), getColor(R.color.tr_light));
                    compareSequence(1);
                    break;
                case R.id.btn_bl:
                    flashButton(btn_bl, getColor(R.color.bl_dark), getColor(R.color.bl_light));
                    compareSequence(2);
                    break;
                case R.id.btn_br:
                    flashButton(btn_br, getColor(R.color.br_dark), getColor(R.color.br_light));
                    compareSequence(3);
                    break;

                default:
                    break;
            }
        }
    }

    private void startGame()
    {
        game_status.setText(R.string.cpu_turn);
        isPlayerTurn=false;

        //Introduce a delay before starting each round.
        Handler gameStarter = new Handler();
        gameStarter.postDelayed(new Runnable() {
            @Override
            public void run() {
                extendSequence();
            }
        },3000);
    }

    private void endGame()
    {
        HighScoreManager hm = new HighScoreManager();
        hm.pushUserScore(readSetting("user"),score);
        startActivity(new Intent(this, HighScore.class));
        finish();
    }

    //Turns on and off the button's light
    private void flashButton(final Button btn, final int dark, int light)
    {
        btn.setBackgroundColor(light);

        if(readSetting("sound")=="true")
        {
            switch (btn.getId())
            {
                case R.id.btn_tl:
                    sound1.start();
                    break;
                case R.id.btn_tr:
                    sound2.start();
                    break;
                case R.id.btn_bl:
                    sound3.start();
                    break;
                case R.id.btn_br:
                    sound4.start();
                    break;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn.setBackgroundColor(dark);
            }
        }, 500);
    }

    //Activate button based on key from CPU
    private void triggerButton(int i)
    {
        switch (i)
        {
            case 0:
                flashButton(btn_tl, getColor(R.color.tl_dark), getColor(R.color.tl_light));
                break;
            case 1:
                flashButton(btn_tr, getColor(R.color.tr_dark), getColor(R.color.tr_light));
                break;
            case 2:
                flashButton(btn_bl, getColor(R.color.bl_dark), getColor(R.color.bl_light));
                break;
            case 3:
                flashButton(btn_br, getColor(R.color.br_dark), getColor(R.color.br_light));
                break;

            default:
                break;
        }
    }

    //Adds one additional key to the sequence.
    private void extendSequence()
    {
        Random r = new Random();
        int key = r.nextInt(4);
        sequence.add(key);
        demoSequence(0);
    }

    //The sequence is played for the player
    private void demoSequence(final int i)
    {
        if(i<sequence.size())
        {
            triggerButton(sequence.get(i));

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    demoSequence(i+1);
                }
            }, 800);
        }

        else
        {
            game_status.setText(R.string.player_turn);
            isPlayerTurn=true;
            currentKey=0;
        }
    }

    private void compareSequence(int i)
    {
        if(i==sequence.get(currentKey))
        {
            currentKey++;
        }

        else
        {
            if(readSetting("sound")=="true")
                failure.start();
            endGame();
        }

        if(currentKey>=sequence.size())
        {
            score += currentKey*currentKey*100;
            game_score.setText(String.format("%06d",score));
            startGame();
        }
    }

    //Get settings from preference.
    private String readSetting(String key)
    {
        String value;
        value = settings.getString(key, "Error");
        return value;
    }

}
