package com.manqaro.mindmaster.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manqaro.mindmaster.R;

import java.util.ArrayList;

public class MathGameActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout resumeExitLayout, pauseMenuLayout, mathGameBoard, timeOutLayout;
    FrameLayout mathGameRoot;
    RelativeLayout activityMathGameRules;
    ImageView imageViewMathRulesStart, imageViewResumeIcon, imageViewExitIcon, imageViewResume, imageViewExit,
            imageViewBackFromGame, imageViewPauseGame, imageViewActionBarTimer, imageViewMathOk,
            imageViewResumeGameIcon, imageViewRestartGame, imageViewMusic, imageViewSound, imageViewExitGame,
            imageViewRestartIconTimeOut, imageViewExitIconTimeOut, imageViewRestartIconTimeOutCombo, imageViewExitIconTimeOutCombo;
    int[] numbersDrawables, operationsDrawables;
    Bitmap[] numbersBitmaps;
    Bitmap[] operationsBitmaps;
    ImageView[] numbersBoxes;
    TextView textViewMathTimer, textViewMathAnswer, textViewCurrentScore, textViewHighscore;
    Boolean isCombo;
    Intent intent0;
    Handler handler;
    int numberNumbers;
    int step;
    int timeAmount;
    int gameBoardWidth, gameBoardHeight;
    ArrayList<Bitmap> numbersBitmapsList;
    ArrayList<Bitmap> operationsBitmapsList;
    Thread timeThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent0 = getIntent();
        isCombo = intent0.getBooleanExtra("combo", false);
        setContentView(R.layout.activity_math_game);
        if (isCombo)
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayoutCombo);
        else
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayout);
        activityMathGameRules = (RelativeLayout) findViewById(R.id.activityMathGameRules);
        mathGameRoot = (FrameLayout) findViewById(R.id.mathGameRoot);
        imageViewMathRulesStart = (ImageView) findViewById(R.id.imageViewMathRulesStart);
        resumeExitLayout = (RelativeLayout) findViewById(R.id.resumeExitLayout);
        pauseMenuLayout = (RelativeLayout) findViewById(R.id.pauseMenuLayout);
        imageViewBackFromGame = (ImageView) findViewById(R.id.imageViewBackFromGame);
        imageViewPauseGame = (ImageView) findViewById(R.id.imageViewPauseGame);
        mathGameBoard = (RelativeLayout) findViewById(R.id.mathGameBoard);
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
        imageViewMathOk = (ImageView) findViewById(R.id.imageViewMathOk);
        imageViewRestartIconTimeOut = (ImageView) findViewById(R.id.imageViewRestartIconTimeOut);
        imageViewExitIconTimeOut = (ImageView) findViewById(R.id.imageViewExitIconTimeOut);
        imageViewRestartIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewRestartIconTimeOutCombo);
        imageViewExitIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewExitIconTimeOutCombo);
        textViewMathTimer = (TextView) findViewById(R.id.textViewTimer);
        textViewMathAnswer = (TextView) findViewById(R.id.textViewMathAnswer);
        textViewCurrentScore = (TextView) findViewById(R.id.textViewCurrentScore);
        textViewHighscore = (TextView) findViewById(R.id.textViewHighscore);
        numbersBoxes = new ImageView[10];
        numbersBoxes[0] = (ImageView) findViewById(R.id.imageViewMathNumber1);
        numbersBoxes[1] = (ImageView) findViewById(R.id.imageViewMathNumber2);
        numbersBoxes[2] = (ImageView) findViewById(R.id.imageViewMathNumber3);
        numbersBoxes[3] = (ImageView) findViewById(R.id.imageViewMathNumber4);
        numbersBoxes[4] = (ImageView) findViewById(R.id.imageViewMathNumber5);
        numbersBoxes[5] = (ImageView) findViewById(R.id.imageViewMathNumber6);
        numbersBoxes[6] = (ImageView) findViewById(R.id.imageViewMathNumber7);
        numbersBoxes[7] = (ImageView) findViewById(R.id.imageViewMathNumber8);
        numbersBoxes[8] = (ImageView) findViewById(R.id.imageViewMathNumber9);
        numbersBoxes[9] = (ImageView) findViewById(R.id.imageViewMathNumber0);
        numbersDrawables = new int[]{R.drawable.math_1s,
                R.drawable.math_2s,
                R.drawable.math_3s,
                R.drawable.math_4s,
                R.drawable.math_5s,
                R.drawable.math_6s,
                R.drawable.math_7s,
                R.drawable.math_8s,
                R.drawable.math_9s,
                R.drawable.math_0s};
        operationsDrawables = new int[]{R.drawable.math_gumarac,
                R.drawable.math_hanac,
                R.drawable.math_bazmapatkac,
                R.drawable.math_bajanac};
        numbersBitmaps = new Bitmap[10];
        for (int i = 0; i < 10; i++)
            numbersBitmaps[i] = BitmapFactory.decodeResource(getResources(), numbersDrawables[i]);
        operationsBitmaps = new Bitmap[4];
        for (int i = 0; i < 4; i++)
            operationsBitmaps[i] = BitmapFactory.decodeResource(getResources(), operationsDrawables[i]);
        numbersBoxes = new ImageView[10];
        for (int i = 0; i < 10; i++)
            numbersBoxes[i] = (ImageView) findViewById(numbersDrawables[i]);
        numbersBitmapsList = new ArrayList<>();
        operationsBitmapsList = new ArrayList<>();
        handler = new Handler();
        timeThread = new Thread(new CheckTime());
        step = 0;
        numberNumbers = 3;

        imageViewResumeIcon.setOnClickListener(this);
        imageViewExitIcon.setOnClickListener(this);
        imageViewResume.setOnClickListener(this);
        imageViewExit.setOnClickListener(this);
        imageViewPauseGame.setOnClickListener(this);
        imageViewBackFromGame.setOnClickListener(this);
        imageViewMathRulesStart.setOnClickListener(this);
        imageViewResumeGameIcon.setOnClickListener(this);
        imageViewRestartGame.setOnClickListener(this);
        imageViewMusic.setOnClickListener(this);
        imageViewSound.setOnClickListener(this);
        imageViewExitGame.setOnClickListener(this);
        imageViewRestartIconTimeOut.setOnClickListener(this);
        imageViewExitIconTimeOut.setOnClickListener(this);

        mathGameBoard.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                mathGameBoard.getHitRect(rect);
                gameBoardWidth = rect.width();
                gameBoardHeight = rect.height();
                mathGameBoard.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.imageViewMathRulesStart:
                //Animation
                startPlay();
                break;
            case R.id.imageViewRestartGame:
                startActivity(new Intent(this, MathGameActivity.class));
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
                startActivity(new Intent(this, MathGameActivity.class));
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
        activityMathGameRules.setVisibility(View.GONE);
        displayNumbers(numberNumbers);
        mathGameBoard.setVisibility(View.VISIBLE);
        activityMathGameRules.setVisibility(View.GONE);
        //game code
    }

    private void displayNumbers(final int k) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (timeThread.isAlive())
                    timeThread.interrupt();
                step++;
                mathGameBoard.setVisibility(View.VISIBLE);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numbersBitmapsList.clear();
                operationsBitmapsList.clear();
                final ImageView[] displayingNumbers = new ImageView[k];
                final RelativeLayout.LayoutParams displayingNumbersLayoutParams[] = new RelativeLayout.LayoutParams[k];
                final ImageView[] displayingOperations = new ImageView[k - 1];
                final RelativeLayout.LayoutParams displayingOperationsLayoutParams[] = new RelativeLayout.LayoutParams[k];
                int diameter = getResources().getDimensionPixelSize(R.dimen._53sdp);
                int marginLeft = (int) (Math.random() * (gameBoardWidth - diameter));
                int marginTop = (int) (Math.random() * (gameBoardHeight - diameter));
                for (int count = 0; count < k; count++) {
                    displayingNumbers[count] = new ImageView(getApplicationContext());
                    displayingNumbersLayoutParams[count] = new RelativeLayout.LayoutParams(diameter, diameter);
                    displayingNumbersLayoutParams[count].setMargins(marginLeft, marginTop, 0, 0);
                    marginLeft = (int) (Math.random() * (gameBoardWidth - diameter));
                    marginTop = (int) (Math.random() * (gameBoardHeight - diameter));
                }
                for (int count = 0; count < k - 1; count++) {
                    displayingOperations[count] = new ImageView(getApplicationContext());
                    displayingOperationsLayoutParams[count] = new RelativeLayout.LayoutParams(diameter, diameter);
                    displayingOperationsLayoutParams[count].setMargins(marginLeft, marginTop, 0, 0);
                    marginLeft = (int) (Math.random() * (gameBoardWidth - diameter));
                    marginTop = (int) (Math.random() * (gameBoardHeight - diameter));
                }
                for (int count = 0; count < k; count++) {
                    final int index = count;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            displayingNumbers[index].setImageResource(numbersDrawables[index]);
                            numbersBitmapsList.add(numbersBitmaps[index]);
                            mathGameBoard.addView(displayingNumbers[index], displayingNumbersLayoutParams[index]);
                            if (index >= 1)
                                mathGameBoard.removeView(displayingNumbers[index - 1]);
                        }
                    }, 1000 * (2 * index + 1));
                }
                for (int count = 0; count < k - 1; count++) {
                    final int index = count;
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            displayingOperations[index].setImageResource(operationsDrawables[index]);
                            operationsBitmapsList.add(operationsBitmaps[index]);
                            mathGameBoard.addView(displayingOperations[index], displayingOperationsLayoutParams[index]);
                            if (index >= 1)
                                mathGameBoard.removeView(displayingOperations[index - 1]);
                        }
                    }, 2000 * (index + 1));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mathGameBoard.setVisibility(View.INVISIBLE);
                        chooseNumbers();
                    }
                }, 2000 * k);
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

    private void chooseNumbers() {
        timeThread.start();
    }

    private class CheckTime implements Runnable {

        @Override
        public void run() {
            while (timeAmount > 0) {
                try {
                    Thread.sleep(100);
                    timeAmount = timeAmount - 100;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (timeAmount < 10000)
                            textViewMathTimer.setText("0:0" + timeAmount / 1000);
                        else
                            textViewMathTimer.setText("0:" + timeAmount / 1000);
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
}