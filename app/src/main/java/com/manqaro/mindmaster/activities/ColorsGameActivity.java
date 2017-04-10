package com.manqaro.mindmaster.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manqaro.mindmaster.R;
import com.manqaro.mindmaster.adapters.GridViewContainersAdapter;

import java.util.ArrayList;

public class ColorsGameActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    int colorsNumber, step, timeAmount, gameBoardWidth, gameBoardHeight, filled, correctFrom5, delta, bonusDisplayingTime;
    ArrayList<Integer> colorsBitmapsList, colorsContainersList;
    Thread timeThread;
    RelativeLayout timeOutLayout, resumeExitLayout, pauseMenuLayout, colorsGameBoard, activityColorsGameRules,
            colorsGridViewCover, colorsCover;
    GridView gridViewColorsContainer;
    ImageView imageViewColorsRulesStart, imageViewResumeIcon, imageViewExitIcon, imageViewResume, imageViewExit,
            imageViewBackFromGame, imageViewPauseGame, imageViewActionBarTimer, imageViewColorsOk,
            imageViewResumeGameIcon, imageViewRestartGame, imageViewMusic, imageViewSound, imageViewExitGame,
            imageViewRestartIconTimeOut, imageViewExitIconTimeOut, imageViewRestartIconTimeOutCombo, imageViewExitIconTimeOutCombo;
    int[] colorsDrawables;
    ImageView[] colorsBoxes;
    TextView textViewColorsTimer, textViewCurrentScore, textViewHighscore;
    Intent intent0;
    Handler handler;
    Boolean isCombo;
    GridViewContainersAdapter gridViewContainersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent0 = getIntent();
        isCombo = intent0.getBooleanExtra("combo", false);
        setContentView(R.layout.activity_colors_game);
        if (isCombo)
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayoutCombo);
        else
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayout);
        colorsGridViewCover = (RelativeLayout) findViewById(R.id.colorsGridViewCover);
        colorsCover = (RelativeLayout) findViewById(R.id.colorsCover);
        activityColorsGameRules = (RelativeLayout) findViewById(R.id.activityColorsGameRules);
        activityColorsGameRules = (RelativeLayout) findViewById(R.id.activityColorsGameRules);
        imageViewColorsRulesStart = (ImageView) findViewById(R.id.imageViewColorsRulesStart);
        resumeExitLayout = (RelativeLayout) findViewById(R.id.resumeExitLayout);
        pauseMenuLayout = (RelativeLayout) findViewById(R.id.pauseMenuLayout);
        imageViewBackFromGame = (ImageView) findViewById(R.id.imageViewBackFromGame);
        imageViewPauseGame = (ImageView) findViewById(R.id.imageViewPauseGame);
        colorsGameBoard = (RelativeLayout) findViewById(R.id.colorsGameBoard);
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
        imageViewColorsOk = (ImageView) findViewById(R.id.imageViewColorsOk);
        imageViewRestartIconTimeOut = (ImageView) findViewById(R.id.imageViewRestartIconTimeOut);
        imageViewExitIconTimeOut = (ImageView) findViewById(R.id.imageViewExitIconTimeOut);
        imageViewRestartIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewRestartIconTimeOutCombo);
        imageViewExitIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewExitIconTimeOutCombo);
        textViewColorsTimer = (TextView) findViewById(R.id.textViewTimer);
        textViewCurrentScore = (TextView) findViewById(R.id.textViewCurrentScore);
        textViewHighscore = (TextView) findViewById(R.id.textViewHighscore);
        gridViewColorsContainer = (GridView) findViewById(R.id.gridViewColorsContainer);
        gridViewColorsContainer.setOnItemClickListener(this);
        colorsContainersList = new ArrayList<>(12);
        colorsDrawables = new int[]{R.drawable.rounds_0009_blue,
                R.drawable.rounds_0008_red,
                R.drawable.rounds_0007_green,
                R.drawable.rounds_0006_yellow,
                R.drawable.rounds_0005_pink,
                R.drawable.rounds_0004_orange,
                R.drawable.rounds_0003_violet,
                R.drawable.rounds_0002_light_blue,
                R.drawable.rounds_0001_brown,
                R.drawable.rounds_0000_fire};
        colorsBoxes = new ImageView[10];
        colorsBoxes[0] = (ImageView) findViewById(R.id.imageViewColorsColor1);
        colorsBoxes[1] = (ImageView) findViewById(R.id.imageViewColorsColor2);
        colorsBoxes[2] = (ImageView) findViewById(R.id.imageViewColorsColor3);
        colorsBoxes[3] = (ImageView) findViewById(R.id.imageViewColorsColor4);
        colorsBoxes[4] = (ImageView) findViewById(R.id.imageViewColorsColor5);
        colorsBoxes[5] = (ImageView) findViewById(R.id.imageViewColorsColor6);
        colorsBoxes[6] = (ImageView) findViewById(R.id.imageViewColorsColor7);
        colorsBoxes[7] = (ImageView) findViewById(R.id.imageViewColorsColor8);
        colorsBoxes[8] = (ImageView) findViewById(R.id.imageViewColorsColor9);
        colorsBoxes[9] = (ImageView) findViewById(R.id.imageViewColorsColor10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            colorsBoxes[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int k = 0; k < colorsNumber; k++)
                        if (colorsContainersList.get(k).equals(R.drawable.rounds_0000_white)) {
                            colorsContainersList.set(k, colorsDrawables[index]);
                            gridViewContainersAdapter = new GridViewContainersAdapter(getApplicationContext(), colorsContainersList);
                            gridViewColorsContainer.setAdapter(gridViewContainersAdapter);
                            k = colorsNumber;
                        }
                }
            });
        }
        colorsBitmapsList = new ArrayList<>();
        handler = new Handler();
        timeThread = new Thread(new CheckTime());
        step = 0;
        colorsNumber = 3;

        imageViewResumeIcon.setOnClickListener(this);
        imageViewExitIcon.setOnClickListener(this);
        imageViewResume.setOnClickListener(this);
        imageViewExit.setOnClickListener(this);
        imageViewPauseGame.setOnClickListener(this);
        imageViewBackFromGame.setOnClickListener(this);
        imageViewColorsRulesStart.setOnClickListener(this);
        imageViewResumeGameIcon.setOnClickListener(this);
        imageViewRestartGame.setOnClickListener(this);
        imageViewMusic.setOnClickListener(this);
        imageViewSound.setOnClickListener(this);
        imageViewExitGame.setOnClickListener(this);
        imageViewRestartIconTimeOut.setOnClickListener(this);
        imageViewExitIconTimeOut.setOnClickListener(this);
        imageViewRestartIconTimeOutCombo.setOnClickListener(this);
        imageViewExitIconTimeOutCombo.setOnClickListener(this);
        imageViewColorsOk.setOnClickListener(this);

        colorsGameBoard.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                colorsGameBoard.getHitRect(rect);
                gameBoardWidth = rect.width();
                gameBoardHeight = rect.height();
                colorsGameBoard.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.imageViewColorsRulesStart:
                //Animation
                startPlay();
                break;
            case R.id.imageViewRestartGame:
                startActivity(new Intent(this, ColorsGameActivity.class));
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
                startActivity(new Intent(this, ColorsGameActivity.class));
                finish();
                break;
            case R.id.imageViewExitIconTimeOut:
            case R.id.imageViewExitIconTimeOutCombo:
                //Animation
                intent.putExtra("status", "exit");
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewColorsOk:
                //Animation
                delta = 0;
                int correct = 0;
                bonusDisplayingTime = 0;
                for (int i = 0; i < colorsNumber; i++)
                    if (colorsContainersList.get(i).equals(colorsBitmapsList.get(i)))
                        correct++;
                if (correct == colorsNumber) {
                    imageViewColorsOk.setImageDrawable(getResources().getDrawable(R.drawable.correct));
                    correctFrom5++;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            colorsGridViewCover.setVisibility(View.VISIBLE);
                        }
                    }, 600);
                } else {
                    for (int k = 0; k < colorsNumber; k++) {
                        gridViewContainersAdapter = new GridViewContainersAdapter(getApplicationContext(), colorsBitmapsList);
                        gridViewColorsContainer.setAdapter(gridViewContainersAdapter);
                        imageViewColorsOk.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                    }
                }

                step++;
                colorsCover.startAnimation(AnimationUtils.loadAnimation(this, R.anim.hideanim));
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        colorsCover.setVisibility(View.INVISIBLE);
                    }
                }, 300);

                if (step % 5 == 0) {
                    if (correctFrom5 == 5) {
                        bonusDisplayingTime = 400;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imageViewColorsOk.setImageDrawable(getResources().getDrawable(R.drawable.bonus_plus5));
                            }
                        }, 850);
                        timeAmount = timeAmount + 5000;
                        if (timeAmount < 10000) {
                            textViewColorsTimer.setTextColor(Color.RED);
                            textViewColorsTimer.setText("0:0" + timeAmount / 1000);
                        } else {
                            textViewColorsTimer.setTextColor(Color.BLACK);
                            textViewColorsTimer.setText("0:" + timeAmount / 1000);
                        }
                    }
                    correctFrom5 = 0;
                    colorsNumber++;
                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageViewColorsOk.setImageDrawable(getResources().getDrawable(R.drawable.ok));
                        displayColors(colorsNumber);
                        colorsGridViewCover.setVisibility(View.INVISIBLE);
                        colorsGameBoard.setVisibility(View.VISIBLE);
                        colorsCover.setVisibility(View.VISIBLE);
                    }
                }, 850 + bonusDisplayingTime);
                break;
        }
    }

    private void startPlay() {
        timeAmount = 60000;
        activityColorsGameRules.setVisibility(View.GONE);
        displayColors(colorsNumber);
        colorsGameBoard.setVisibility(View.VISIBLE);
        //game code
    }

    private void displayColors(final int k) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                delta = 0;
                colorsGameBoard.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        colorsCover.setVisibility(View.INVISIBLE);
                    }
                }, 300);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                colorsBitmapsList.clear();
                final ImageView[] displayingColors = new ImageView[k];
                final RelativeLayout.LayoutParams displayingColorsLayoutParams[] = new RelativeLayout.LayoutParams[k];
                int diameter = getResources().getDimensionPixelSize(R.dimen._53sdp);
                int marginLeft = (int) (Math.random() * (gameBoardWidth - diameter));
                int marginTop = (int) (Math.random() * (gameBoardHeight - diameter));
                for (int count = 0; count < k; count++) {
                    displayingColors[count] = new ImageView(getApplicationContext());
                    displayingColorsLayoutParams[count] = new RelativeLayout.LayoutParams(diameter, diameter);
                    displayingColorsLayoutParams[count].setMargins(marginLeft, marginTop, 0, 0);
                    marginLeft = (int) (Math.random() * (gameBoardWidth - diameter));
                    marginTop = (int) (Math.random() * (gameBoardHeight - diameter));
                }
                for (int count = 0; count < k; count++) {
                    final int index = count;
                    final int randomIndex = (int) (Math.random() * 10); //01.04
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            displayingColors[index].setImageResource(colorsDrawables[randomIndex]); //01.04
                            colorsBitmapsList.add(colorsDrawables[randomIndex]); //01.04
                            colorsGameBoard.addView(displayingColors[index], displayingColorsLayoutParams[index]);
                            if (index >= 1)
                                colorsGameBoard.removeView(displayingColors[index - 1]);
                        }
                    }, 1000 * (index + 1));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        colorsGameBoard.removeView(displayingColors[colorsNumber - 1]);
                        colorsGameBoard.setVisibility(View.INVISIBLE);
                        chooseColors(colorsNumber);
                    }
                }, 1000 * (k + 1));
            }
        });
    }

    private void chooseColors(int k) {
        delta = 100;
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        anim.setDuration(192);
        anim.setFillAfter(true);
        colorsCover.startAnimation(anim); //10.04
        colorsContainersList.clear();
        for (int i = 0; i < k; i++)
            colorsContainersList.add(R.drawable.rounds_0000_white);
        gridViewContainersAdapter = new GridViewContainersAdapter(this, colorsContainersList);
        gridViewColorsContainer.setAdapter(gridViewContainersAdapter);
        gridViewColorsContainer.setNumColumns(Math.min(colorsNumber, 6));
        if (!timeThread.isAlive())
            timeThread.start();
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
        colorsContainersList.set(position, R.drawable.rounds_0000_white);
        gridViewContainersAdapter = new GridViewContainersAdapter(getApplicationContext(), colorsContainersList);
        gridViewColorsContainer.setAdapter(gridViewContainersAdapter);
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
                            filled = 0;
                            for (int i = 0; i < colorsNumber; i++)
                                if (!colorsContainersList.get(i).equals(R.drawable.rounds_0000_white))
                                    filled++;
                            if (filled == colorsNumber) {
                                imageViewColorsOk.setAlpha(1.0f);
                                imageViewColorsOk.setEnabled(true);
                            } else {
                                imageViewColorsOk.setAlpha(0.5f);
                                imageViewColorsOk.setEnabled(false);
                            }
                            if (timeAmount < 10000) {
                                textViewColorsTimer.setTextColor(Color.RED);
                                textViewColorsTimer.setText("0:0" + timeAmount / 1000);
                            } else {
                                textViewColorsTimer.setTextColor(Color.BLACK);
                                textViewColorsTimer.setText("0:" + timeAmount / 1000);
                            }
                        }
                    }
                });
            }
            if (timeAmount <= 0)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeOutLayout.setVisibility(View.VISIBLE);
                        // stop thread
                    }
                });
        }
    }
}