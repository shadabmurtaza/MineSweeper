package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    TextView smiley, timer, flag, flagsCount;
    CountDownTimer countDownTimer;
    int secondsElapsed;
    boolean timerStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag = findViewById(R.id.activity_main_flag);
        flagsCount = findViewById(R.id.activity_main_flagsleft);
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.toggleMode();
                if(game.isFlagMode()){
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    border.setStroke(1,0xFFFFFFFF);
                    flag.setBackground(border);
                }else{
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    flag.setBackground(border);
                }
            }
        });

        smiley = findViewById(R.id.activity_main_smiley);
        smiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game = new MinesweeperGame(10, 10);
                mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
                timerStarted = false;
                countDownTimer.cancel();
                secondsElapsed = 0;
                timer.setText(R.string.default_count);
                flagsCount.setText(String.format("%03d", game.getNumberBombs() - game.getFlagCount()));

            }
        });

        timer = findViewById(R.id.activity_main_timer);
        timerStarted = false;
        countDownTimer = new CountDownTimer(100000L, 1000) {
            @Override
            public void onTick(long millisUnitFinished) {
                secondsElapsed += 1;
                timer.setText(String.format("%03d", secondsElapsed));
            }

            @Override
            public void onFinish() {
                game.outOfTime();
                Toast.makeText(getApplicationContext(),  "GameOver : Timer Expired", Toast.LENGTH_SHORT).show();
                game.getMineGrid().revealAllBombs();
                mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
            }
        };

        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        game = new MinesweeperGame(10 , 10);
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(), this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
        flagsCount.setText(String.format("%03d", game.getNumberBombs() - game.getFlagCount()));
    }

    @Override
    public void onCellClick(Cell cell) {
        game.handleCellClick(cell);

        flagsCount.setText(String.format("%03d", game.getNumberBombs() - game.getFlagCount()));

        if(!timerStarted){
            countDownTimer.start();
            timerStarted = true;
        }

        if(game.isGameOver()){
            Toast.makeText(getApplicationContext(),"GAME OVER", Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
        }
        if(game.isGameWon()){
            Toast.makeText(getApplicationContext(), "GAME WON", Toast.LENGTH_SHORT).show();
        }

        mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());

    }
}