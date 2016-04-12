package com.pinterest.hello.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    // declare views here
    TextView _scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // hook up views here
        _scoreView = (TextView) findViewById(R.id.score);

        // get the passed in "score" and set to the view
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int score = extras.getInt("score");
            _scoreView.setText(String.valueOf(score));
        }
    }
}
