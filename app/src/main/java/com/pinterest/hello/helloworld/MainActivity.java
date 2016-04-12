package com.pinterest.hello.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declare views here
    Button _up;
    Button _left;
    Button _right;
    Button _down;
    Button _restart;
    TextView _textView;
    TextView _scoreView;
    TextView _timerView;

    // declare other variables here
    int _score;
    String[] _msgArray = new String[]{"up", "down", "left", "right"};

    OnClickListener _onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isCorrect(v)) {
                nextMsg();
            } else {
                _timer.cancel();
                gameOver();
            }
        }
    };

    private boolean isCorrect(View v) {
        // return whether or not user clicks the correct button (i.e. the given view)
        String expected;
        if (v == _up) {
            expected = "down";
        } else if (v == _down) {
            expected = "up";
        } else if (v == _left) {
            expected = "right";
        } else {
            expected = "left";
        }
        return _textView.getText().equals(expected);
    }

    CountDownTimer _timer = new CountDownTimer(5000, 1000) {

        public void onTick(long millisUntilFinished) {
            _timerView.setText(String.valueOf(millisUntilFinished / 1000));
        }

        public void onFinish() {
            gameOver();
        }
    };

    private void generateMsg() {
        // generate one of the "up", "down", "left", or "right"
        int index = (int) (Math.random() * 4);
        _textView.setText(_msgArray[index]);
    }

    private void start() {
        // start the game
        _score = 0;
        _scoreView.setText("score: " + _score);
        generateMsg();
        _timer.cancel();
        _timer.start();
    }

    private void nextMsg() {
        // show next message
        _score++;
        _scoreView.setText("score: " + _score);
        generateMsg();
    }

    private void gameOver() {
        // end the game
        _textView.setText("Game over");
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("score", _score);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hook up views here
        _up = (Button) findViewById(R.id.up);
        _left = (Button) findViewById(R.id.left);
        _right = (Button) findViewById(R.id.right);
        _down = (Button) findViewById(R.id.down);
        _restart = (Button) findViewById(R.id.restart);
        _textView = (TextView) findViewById(R.id.msg);
        _scoreView = (TextView) findViewById(R.id.score);
        _timerView = (TextView) findViewById(R.id.timer);

        // set click listeners here
        _up.setOnClickListener(_onClickListener);
        _left.setOnClickListener(_onClickListener);
        _right.setOnClickListener(_onClickListener);
        _down.setOnClickListener(_onClickListener);
        _restart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }
}
