/*This class controls the high score board*/
package com.example.arx8l.simonsays;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.HighScoreViewHolder>
{
    private Context mCtx;
    private List<UserScore> highScores;

    public HighScoreAdapter(Context mCtx, List<UserScore> highScores) {
        this.mCtx = mCtx;
        this.highScores = highScores;
    }
    //Inflate layout in view holder
    @NonNull
    @Override
    public HighScoreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.high_score_entry, viewGroup, false);
        return new HighScoreViewHolder(view);
    }

    //Set display values of view objects.
    @Override
    public void onBindViewHolder(@NonNull HighScoreViewHolder holder, int i) {
        UserScore userScore = highScores.get(i);
        holder.rank.setText(String.format("%02d",i+1));
        holder.user_value.setText(userScore.getName());
        holder.score_value.setText(String.format("%06d",userScore.getScore()));
    }

    @Override
    public int getItemCount() {
        return highScores.size();
    }

    class HighScoreViewHolder extends RecyclerView.ViewHolder
    {
        private TextView rank;
        private TextView user_value;
        private TextView score_value;

        public HighScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            user_value = itemView.findViewById(R.id.user_value);
            score_value = itemView.findViewById(R.id.score_value);
        }
    }
}
