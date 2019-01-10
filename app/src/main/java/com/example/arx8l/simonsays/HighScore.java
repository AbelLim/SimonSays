package com.example.arx8l.simonsays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class HighScore extends AppCompatActivity implements View.OnClickListener{

    private HighScoreManager hm = new HighScoreManager();
    private RecyclerView recyclerView;
    private HighScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        initView();
    }

    //Initialize all UI elements
    private void initView()
    {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hm.getHighScore(new HighScoreManager.highScoreListener() {
            @Override
            public void onChange(List<UserScore> highScores) {
                displayHighScores(highScores);
            }

            @Override
            public void onError() {

            }
        });
    }

    //Display High Scores
    private void displayHighScores(List<UserScore> highScores)
    {
        adapter = new HighScoreAdapter(this, highScores);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
