package com.manqaro.mindmaster.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manqaro.mindmaster.R;
import com.manqaro.mindmaster.adapters.GridViewTableAdapter;

import java.util.ArrayList;

public class MemorizeGameActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    int cellsNumber, step, timeAmount, delta;
    ArrayList<Integer> displayedCellsList, selectedCellsList;
    RelativeLayout activityMemorizeGameRules, timeOutLayout, resumeExitLayout, pauseMenuLayout, memorizeContainer, memorizeCover;
    GridView memorizeContainerGridView;
    ImageView imageViewMemorizeRulesStart, imageViewResumeIcon, imageViewExitIcon, imageViewResume, imageViewExit,
            imageViewBackFromGame, imageViewPauseGame, imageViewActionBarTimer,
            imageViewResumeGameIcon, imageViewRestartGame, imageViewMusic, imageViewSound, imageViewExitGame,
            imageViewRestartIconTimeOut, imageViewExitIconTimeOut, imageViewRestartIconTimeOutCombo, imageViewExitIconTimeOutCombo,
            imageViewMemorizeResult;
    int[] colorsDrawables, gridArray;
    TextView textViewMemorizeTimer;
    Intent intent0;
    Handler handler;
    Boolean isCombo;
    Thread timeThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent0 = getIntent();
        isCombo = intent0.getBooleanExtra("combo", false);
        setContentView(R.layout.activity_memorize_game);
        if (isCombo)
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayoutCombo);
        else
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayout);
        activityMemorizeGameRules = (RelativeLayout) findViewById(R.id.activityMemorizeGameRules);
        resumeExitLayout = (RelativeLayout) findViewById(R.id.resumeExitLayout);
        pauseMenuLayout = (RelativeLayout) findViewById(R.id.pauseMenuLayout);
        memorizeContainer = (RelativeLayout) findViewById(R.id.memorizeContainer);
        memorizeCover = (RelativeLayout) findViewById(R.id.memorizeCover);
        imageViewMemorizeRulesStart = (ImageView) findViewById(R.id.imageViewMemorizeRulesStart);
        imageViewMemorizeResult = (ImageView) findViewById(R.id.imageViewMemorizeResult);
        imageViewBackFromGame = (ImageView) findViewById(R.id.imageViewBackFromGame);
        imageViewPauseGame = (ImageView) findViewById(R.id.imageViewPauseGame);
        imageViewResumeIcon = (ImageView) findViewById(R.id.imageViewResumeIcon);
        imageViewExitIcon = (ImageView) findViewById(R.id.imageViewExitIcon);
        imageViewResume = (ImageView) findViewById(R.id.imageViewResume);
        imageViewExit = (ImageView) findViewById(R.id.imageViewExit);
        imageViewActionBarTimer = (ImageView) findViewById(R.id.imageViewActionBarTimer);
        imageViewResumeGameIcon = (ImageView) findViewById(R.id.imageViewResumeGameIcon);
        imageViewRestartGame = (ImageView) findViewById(R.id.imageViewRestartGame);
        imageViewMusic = (ImageView) findViewById(R.id.imageViewMusic);
        imageViewSound = (ImageView) findViewById(R.id.imageViewSound);
        imageViewExitGame = (ImageView) findViewById(R.id.imageViewExitGame);
        imageViewRestartIconTimeOut = (ImageView) findViewById(R.id.imageViewRestartIconTimeOut);
        imageViewExitIconTimeOut = (ImageView) findViewById(R.id.imageViewExitIconTimeOut);
        imageViewRestartIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewRestartIconTimeOutCombo);
        imageViewExitIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewExitIconTimeOutCombo);
        textViewMemorizeTimer = (TextView) findViewById(R.id.textViewTimer);
        colorsDrawables = new int[]{R.drawable.squares_0009_orange, R.drawable.squares_0002_green, R.drawable.squares_0003_red};
        memorizeContainerGridView = (GridView) findViewById(R.id.memorizeContainerGridView);
        memorizeContainerGridView.setOnItemClickListener(this);
        displayedCellsList = new ArrayList<>(12);
        selectedCellsList = new ArrayList<>(12);
        handler = new Handler();
        timeThread = new Thread(new CheckTime());
        cellsNumber = 3;
        step = 0;
        gridArray = new int[25];

        imageViewResumeIcon.setOnClickListener(this);
        imageViewExitIcon.setOnClickListener(this);
        imageViewResume.setOnClickListener(this);
        imageViewExit.setOnClickListener(this);
        imageViewPauseGame.setOnClickListener(this);
        imageViewBackFromGame.setOnClickListener(this);
        imageViewMemorizeRulesStart.setOnClickListener(this);
        imageViewResumeGameIcon.setOnClickListener(this);
        imageViewRestartGame.setOnClickListener(this);
        imageViewMusic.setOnClickListener(this);
        imageViewSound.setOnClickListener(this);
        imageViewExitGame.setOnClickListener(this);
        imageViewRestartIconTimeOut.setOnClickListener(this);
        imageViewExitIconTimeOut.setOnClickListener(this);
        imageViewRestartIconTimeOutCombo.setOnClickListener(this);
        imageViewExitIconTimeOutCombo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.imageViewMemorizeRulesStart:
                //Animation
                startPlay();
                break;
            case R.id.imageViewRestartGame:
                startActivity(new Intent(this, MemorizeGameActivity.class));
                finish();
                break;
            case R.id.imageViewMusic:
                //Animation
                music(false);
                break;
            case R.id.imageViewSound:
                //Animation
                sound(false);
                break;
            case R.id.imageViewResumeGameIcon:
                pauseMenuLayout.setVisibility(View.INVISIBLE);
                restartPlay();
                break;
            case R.id.imageViewResumeIcon:
            case R.id.imageViewResume:
                //Animation
                resumeExitLayout.setVisibility(View.INVISIBLE);
                restartPlay();
                break;
            case R.id.imageViewExitGame:
            case R.id.imageViewExitIcon:
            case R.id.imageViewExit:
                //Animation
                intent.putExtra("status", "exit");
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewPauseGame:
                //Animation
                pauseMenuLayout.setVisibility(View.VISIBLE);
                pausePlay();
                break;
            case R.id.imageViewBackFromGame:
                //Animation
                intent.putExtra("status", "exit");
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewRestartIconTimeOut:
            case R.id.imageViewRestartIconTimeOutCombo:
                //Animation
                startActivity(new Intent(this, MemorizeGameActivity.class));
                finish();
                break;
            case R.id.imageViewExitIconTimeOut:
            case R.id.imageViewExitIconTimeOutCombo:
                //Animation
                intent.putExtra("status", "exit");
                startActivity(intent);
                finish();
                break;
        }
    }

    private void startPlay() {
        timeAmount = 60000;
        activityMemorizeGameRules.setVisibility(View.GONE);
        displayCells(cellsNumber);
        new Thread(new CheckTime()).start();
    }

    private void displayCells(final int cellsNumber) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                delta = 0;
                memorizeContainerGridView.setEnabled(true);
                memorizeContainerGridView.setOnItemClickListener(null);
                for (int i = 0; i < 25; i++)
                    gridArray[i] = R.drawable.memorize_cell;
                memorizeContainerGridView.setAdapter(new GridViewTableAdapter(getApplicationContext(), gridArray));
                for (int i = 0; i < cellsNumber; i++) {
                    int number = (int) (Math.random() * 25);
                    while (displayedCellsList.contains(number))
                        number = (int) (Math.random() * 25);
                    displayedCellsList.add(number);
                }
                for (int k = 0; k < displayedCellsList.size(); k++)
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < cellsNumber; i++)
                            gridArray[displayedCellsList.get(i)] = R.drawable.squares_0009_orange;
                        memorizeContainerGridView.setAdapter(new GridViewTableAdapter(getApplicationContext(), gridArray));
                    }
                }, 700);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 25; i++)
                            gridArray[i] = R.drawable.memorize_cell;
                        memorizeContainerGridView.setAdapter(new GridViewTableAdapter(getApplicationContext(), gridArray));
                        delta = 100;
                        memorizeContainerGridView.setOnItemClickListener(MemorizeGameActivity.this);
                    }
                }, 1800);
            }
        });
    }

    private void restartPlay() {
        //game code
    }

    private void pausePlay() {
        //game code
    }

    @Override
    public void onBackPressed() {
        resumeExitLayout.setVisibility(View.VISIBLE);
        pausePlay();
    }

    private void music(boolean isPlaying) {
        //game code
    }

    private void sound(boolean isPlaying) {
        //game code
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int equality = 0;
        for (int i = 0; i < cellsNumber; i++)
            if (position == displayedCellsList.get(i))
                equality++;
        if (equality == 0) {
            gridArray[position] = R.drawable.squares_0003_red;
            memorizeContainerGridView.setAdapter(new GridViewTableAdapter(getApplicationContext(), gridArray));
            memorizeContainerGridView.setEnabled(false);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    lost();
                }
            }, 700);
        } else {
            gridArray[position] = R.drawable.squares_0002_green;
            memorizeContainerGridView.setAdapter(new GridViewTableAdapter(getApplicationContext(), gridArray));
            selectedCellsList.add(position);
        }
        if (selectedCellsList.size() == cellsNumber) {
            memorizeContainerGridView.setEnabled(false);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    won();
                }
            }, 700);
        }
    }

    private class CheckTime implements Runnable {

        @Override
        public void run() {
            while (timeAmount > 0) {
                try {
                    Thread.sleep(100);
                    timeAmount = timeAmount - delta;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (delta > 0) {
                            if (timeAmount < 10000)
                                textViewMemorizeTimer.setText("0:0" + timeAmount / 1000);
                            else
                                textViewMemorizeTimer.setText("0:" + timeAmount / 1000);
                        }
                    }
                });
            }
            if (timeAmount <= 0)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeOutLayout.setVisibility(View.VISIBLE);
                    }
                });
        }
    }

    private void won() {
        delta = 0;
        memorizeContainerGridView.setOnItemClickListener(null);
        step++;
        memorizeCover.setVisibility(View.VISIBLE);
        imageViewMemorizeResult.setImageDrawable(getResources().getDrawable(R.drawable.correct));
        displayedCellsList.clear();
        selectedCellsList.clear();
        if (step > 0 && step % 5 == 0)
            cellsNumber++;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayCells(cellsNumber);
                memorizeCover.setVisibility(View.INVISIBLE);
            }
        }, 500);
    }

    private void lost() {
        delta = 0;
        memorizeContainerGridView.setOnItemClickListener(null);
        step++;
        memorizeCover.setVisibility(View.VISIBLE);
        imageViewMemorizeResult.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
        displayedCellsList.clear();
        selectedCellsList.clear();
        if (step > 0 && step % 5 == 0)
            cellsNumber++;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayCells(cellsNumber);
                memorizeCover.setVisibility(View.INVISIBLE);
            }
        }, 500);
    }
}