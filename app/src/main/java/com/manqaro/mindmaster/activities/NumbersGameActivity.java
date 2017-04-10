package com.manqaro.mindmaster.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manqaro.mindmaster.R;

import java.util.ArrayList;

public class NumbersGameActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout timeOutLayout, resumeExitLayout, pauseMenuLayout, numbersBonus;
    RelativeLayout activityNumbersGameRules;
    ImageView imageViewNumbersRulesStart, imageViewResumeIcon, imageViewExitIcon, imageViewResume, imageViewExit,
            imageViewBackFromGame, imageViewPauseGame, imageViewActionBarTimer, imageViewNumbersAnimPlace,
            imageViewResumeGameIcon, imageViewRestartGame, imageViewMusic, imageViewSound, imageViewExitGame,
            imageViewRestartIconTimeOut, imageViewExitIconTimeOut, imageViewRestartIconTimeOutCombo, imageViewExitIconTimeOutCombo;
    ImageView[] numbersCells;
    TextView textViewNumbersTimer;
    Boolean isCombo;
    Intent intent0;
    Handler handler;
    int timeAmount, number, delta, sum, correctFrom15, bonusDisplayingTime;
    int[] intAnimationsOn, intAnimationsOff, numbers, white, green, red, cellNumbers;
    ArrayList<Integer> clickedIndexesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent0 = getIntent();
        isCombo = intent0.getBooleanExtra("combo", false);
        setContentView(R.layout.activity_numbers_game);
        if (isCombo)
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayoutCombo);
        else
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayout);
        activityNumbersGameRules = (RelativeLayout) findViewById(R.id.activityNumbersGameRules);
        numbersBonus = (RelativeLayout) findViewById(R.id.numbersBonus);
        imageViewNumbersRulesStart = (ImageView) findViewById(R.id.imageViewNumbersRulesStart);
        resumeExitLayout = (RelativeLayout) findViewById(R.id.resumeExitLayout);
        pauseMenuLayout = (RelativeLayout) findViewById(R.id.pauseMenuLayout);
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
        textViewNumbersTimer = (TextView) findViewById(R.id.textViewTimer);
        imageViewRestartIconTimeOut = (ImageView) findViewById(R.id.imageViewRestartIconTimeOut);
        imageViewExitIconTimeOut = (ImageView) findViewById(R.id.imageViewExitIconTimeOut);
        imageViewRestartIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewRestartIconTimeOutCombo);
        imageViewExitIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewExitIconTimeOutCombo);
        numbersCells = new ImageView[25];
        numbersCells[0] = (ImageView) findViewById(R.id.imageViewNumbersCell1);
        numbersCells[1] = (ImageView) findViewById(R.id.imageViewNumbersCell2);
        numbersCells[2] = (ImageView) findViewById(R.id.imageViewNumbersCell3);
        numbersCells[3] = (ImageView) findViewById(R.id.imageViewNumbersCell4);
        numbersCells[4] = (ImageView) findViewById(R.id.imageViewNumbersCell5);
        numbersCells[5] = (ImageView) findViewById(R.id.imageViewNumbersCell6);
        numbersCells[6] = (ImageView) findViewById(R.id.imageViewNumbersCell7);
        numbersCells[7] = (ImageView) findViewById(R.id.imageViewNumbersCell8);
        numbersCells[8] = (ImageView) findViewById(R.id.imageViewNumbersCell9);
        numbersCells[9] = (ImageView) findViewById(R.id.imageViewNumbersCell10);
        numbersCells[10] = (ImageView) findViewById(R.id.imageViewNumbersCell11);
        numbersCells[11] = (ImageView) findViewById(R.id.imageViewNumbersCell12);
        numbersCells[12] = (ImageView) findViewById(R.id.imageViewNumbersCell13);
        numbersCells[13] = (ImageView) findViewById(R.id.imageViewNumbersCell14);
        numbersCells[14] = (ImageView) findViewById(R.id.imageViewNumbersCell15);
        numbersCells[15] = (ImageView) findViewById(R.id.imageViewNumbersCell16);
        numbersCells[16] = (ImageView) findViewById(R.id.imageViewNumbersCell17);
        numbersCells[17] = (ImageView) findViewById(R.id.imageViewNumbersCell18);
        numbersCells[18] = (ImageView) findViewById(R.id.imageViewNumbersCell19);
        numbersCells[19] = (ImageView) findViewById(R.id.imageViewNumbersCell20);
        numbersCells[20] = (ImageView) findViewById(R.id.imageViewNumbersCell21);
        numbersCells[21] = (ImageView) findViewById(R.id.imageViewNumbersCell22);
        numbersCells[22] = (ImageView) findViewById(R.id.imageViewNumbersCell23);
        numbersCells[23] = (ImageView) findViewById(R.id.imageViewNumbersCell24);
        numbersCells[24] = (ImageView) findViewById(R.id.imageViewNumbersCell25);
        imageViewNumbersAnimPlace = (ImageView) findViewById(R.id.imageViewNumbersAnimPlace);

        intAnimationsOn = new int[]{R.drawable.animation_numbers_10_on,
                R.drawable.animation_numbers_11_on,
                R.drawable.animation_numbers_12_on,
                R.drawable.animation_numbers_13_on,
                R.drawable.animation_numbers_14_on,
                R.drawable.animation_numbers_15_on,
                R.drawable.animation_numbers_16_on,
                R.drawable.animation_numbers_17_on,
                R.drawable.animation_numbers_18_on,
                R.drawable.animation_numbers_19_on,
                R.drawable.animation_numbers_20_on,
                R.drawable.animation_numbers_21_on,
                R.drawable.animation_numbers_22_on,
                R.drawable.animation_numbers_23_on,
                R.drawable.animation_numbers_24_on,
                R.drawable.animation_numbers_25_on,
                R.drawable.animation_numbers_26_on,
                R.drawable.animation_numbers_27_on,
                R.drawable.animation_numbers_28_on,
                R.drawable.animation_numbers_29_on,
                R.drawable.animation_numbers_30_on};

        intAnimationsOff = new int[]{R.drawable.animation_numbers_10_off,
                R.drawable.animation_numbers_11_off,
                R.drawable.animation_numbers_12_off,
                R.drawable.animation_numbers_13_off,
                R.drawable.animation_numbers_14_off,
                R.drawable.animation_numbers_15_off,
                R.drawable.animation_numbers_16_off,
                R.drawable.animation_numbers_17_off,
                R.drawable.animation_numbers_18_off,
                R.drawable.animation_numbers_19_off,
                R.drawable.animation_numbers_20_off,
                R.drawable.animation_numbers_21_off,
                R.drawable.animation_numbers_22_off,
                R.drawable.animation_numbers_23_off,
                R.drawable.animation_numbers_24_off,
                R.drawable.animation_numbers_25_off,
                R.drawable.animation_numbers_26_off,
                R.drawable.animation_numbers_27_off,
                R.drawable.animation_numbers_28_off,
                R.drawable.animation_numbers_29_off,
                R.drawable.animation_numbers_30_off};

        white = new int[]{
                R.drawable.w1,
                R.drawable.w2,
                R.drawable.w3,
                R.drawable.w4,
                R.drawable.w5,
                R.drawable.w6,
                R.drawable.w7,
                R.drawable.w8,
                R.drawable.w9,
        };

        green = new int[]{
                R.drawable.g1,
                R.drawable.g2,
                R.drawable.g3,
                R.drawable.g4,
                R.drawable.g5,
                R.drawable.g6,
                R.drawable.g7,
                R.drawable.g8,
                R.drawable.g9,
        };

        red = new int[]{
                R.drawable.r1,
                R.drawable.r2,
                R.drawable.r3,
                R.drawable.r4,
                R.drawable.r5,
                R.drawable.r6,
                R.drawable.r7,
                R.drawable.r8,
                R.drawable.r9,
        };

        numbers = new int[21];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = i + 10;
        cellNumbers = new int[25];
        clickedIndexesList = new ArrayList<>(5);
        handler = new Handler();

        imageViewResumeIcon.setOnClickListener(this);
        imageViewExitIcon.setOnClickListener(this);
        imageViewResume.setOnClickListener(this);
        imageViewExit.setOnClickListener(this);
        imageViewPauseGame.setOnClickListener(this);
        imageViewBackFromGame.setOnClickListener(this);
        imageViewNumbersRulesStart.setOnClickListener(this);
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
            case R.id.imageViewNumbersRulesStart:
                //Animation
                startPlay();
                break;
            case R.id.imageViewRestartGame:
                startActivity(new Intent(this, NumbersGameActivity.class));
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
                startActivity(new Intent(this, NumbersGameActivity.class));
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
        activityNumbersGameRules.setVisibility(View.GONE);
        displayNumbers();
        new Thread(new CheckTime()).start();
    }

    private void displayNumbers() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                delta = 0;
                sum = 0;
                correctFrom15 = 0;
                bonusDisplayingTime = 0;
                int random = (int) (Math.random() * 21);
                number = numbers[random];
                imageViewNumbersAnimPlace.setImageDrawable(null);
                imageViewNumbersAnimPlace.setBackgroundResource(intAnimationsOn[number - 10]);
                final AnimationDrawable animation = (AnimationDrawable) imageViewNumbersAnimPlace.getBackground();
                class Starter implements Runnable {
                    public void run() {
                        animation.start();
                    }
                }
                imageViewNumbersAnimPlace.post(new Starter());
                for (int i = 0; i < numbersCells.length; i++) {
                    numbersCells[i].setEnabled(false);
                    cellNumbers[i] = 1 + (int) (9 * Math.random());
                    numbersCells[i].setImageDrawable(getResources().getDrawable(white[cellNumbers[i] - 1]));
                }
                clickedIndexesList.clear();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chooseNumbers();
                    }
                }, 750);
            }
        });
    }

    private void chooseNumbers() {
        for (int i = 0; i < numbersCells.length; i++) {
            numbersCells[i].setEnabled(true);
            final int finalI = i;
            numbersCells[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickedIndexesList.contains(finalI)) {
                        clickedIndexesList.remove((Integer) finalI);
                        numbersCells[finalI].setImageDrawable(getResources().getDrawable(white[cellNumbers[finalI] - 1]));
                        sum = sum - cellNumbers[finalI];
                    } else {
                        clickedIndexesList.add(finalI);
                        sum = sum + cellNumbers[finalI];
                        if (sum < number) {
                            if (clickedIndexesList.size() <= 5)
                                numbersCells[finalI].setImageDrawable(getResources().getDrawable(green[cellNumbers[finalI] - 1]));
                            else {
                                for (int i = 0; i < numbersCells.length; i++)
                                    numbersCells[i].setEnabled(false);
                                imageViewNumbersAnimPlace.setImageDrawable(null);
                                imageViewNumbersAnimPlace.setBackgroundResource(intAnimationsOff[number - 10]);
                                final AnimationDrawable animation = (AnimationDrawable) imageViewNumbersAnimPlace.getBackground();
                                class Starter implements Runnable {
                                    public void run() {
                                        animation.start();
                                    }
                                }
                                imageViewNumbersAnimPlace.post(new Starter());
                                delta = 0;
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageViewNumbersAnimPlace.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                                    }
                                }, 310);
                                for (int i = 0; i < clickedIndexesList.size(); i++)
                                    numbersCells[clickedIndexesList.get(i)].setImageDrawable(getResources().getDrawable(red[cellNumbers[clickedIndexesList.get(i)] - 1]));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        displayNumbers();
                                    }
                                }, 750);
                            }
                        } else if (sum > number) {
                            for (int i = 0; i < numbersCells.length; i++)
                                numbersCells[i].setEnabled(false);
                            imageViewNumbersAnimPlace.setImageDrawable(null);
                            imageViewNumbersAnimPlace.setBackgroundResource(intAnimationsOff[number - 10]);
                            final AnimationDrawable animation = (AnimationDrawable) imageViewNumbersAnimPlace.getBackground();
                            class Starter implements Runnable {
                                public void run() {
                                    animation.start();
                                }
                            }
                            imageViewNumbersAnimPlace.post(new Starter());
                            delta = 0;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    imageViewNumbersAnimPlace.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                                }
                            }, 310);
                            for (int i = 0; i < clickedIndexesList.size(); i++)
                                numbersCells[clickedIndexesList.get(i)].setImageDrawable(getResources().getDrawable(red[cellNumbers[clickedIndexesList.get(i)] - 1]));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    displayNumbers();
                                }
                            }, 750);
                        } else if (sum == number) {
                            for (int i = 0; i < numbersCells.length; i++)
                                numbersCells[i].setEnabled(false);
                            if (clickedIndexesList.size() <= 5) {
                                correctFrom15++;
                                imageViewNumbersAnimPlace.setImageDrawable(null);
                                imageViewNumbersAnimPlace.setBackgroundResource(intAnimationsOff[number - 10]);
                                final AnimationDrawable animation = (AnimationDrawable) imageViewNumbersAnimPlace.getBackground();
                                class Starter implements Runnable {
                                    public void run() {
                                        animation.start();
                                    }
                                }
                                imageViewNumbersAnimPlace.post(new Starter());
                                delta = 0;
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageViewNumbersAnimPlace.setImageDrawable(getResources().getDrawable(R.drawable.correct));
                                    }
                                }, 310);
                                for (int i = 0; i < 25; i++)
                                    if (clickedIndexesList.contains(i))
                                        numbersCells[i].setImageDrawable(null);
                                if (correctFrom15 == 15) {
                                    bonusDisplayingTime = 400;
                                    bonusPlus5();
                                }
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        won();
                                    }
                                }, 750 + bonusDisplayingTime);
                            } else {
                                imageViewNumbersAnimPlace.setImageDrawable(null);
                                imageViewNumbersAnimPlace.setBackgroundResource(intAnimationsOff[number - 10]);
                                final AnimationDrawable animation = (AnimationDrawable) imageViewNumbersAnimPlace.getBackground();
                                class Starter implements Runnable {
                                    public void run() {
                                        animation.start();
                                    }
                                }
                                imageViewNumbersAnimPlace.post(new Starter());
                                delta = 0;
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        imageViewNumbersAnimPlace.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                                    }
                                }, 310);
                                for (int i = 0; i < clickedIndexesList.size(); i++)
                                    numbersCells[clickedIndexesList.get(i)].setImageDrawable(getResources().getDrawable(red[cellNumbers[clickedIndexesList.get(i)] - 1]));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        displayNumbers();
                                    }
                                }, 750);
                            }
                        }
                    }
                }
            });
        }
        delta = 100;
    }

    private void bonusPlus5() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                numbersBonus.setVisibility(View.VISIBLE);
                timeAmount = timeAmount + 5000;
            }
        }, 750);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                numbersBonus.setVisibility(View.INVISIBLE);
                bonusDisplayingTime = 0;
            }
        }, 1150);
    }

    private void won() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                delta = 0;
                sum = 0;
                bonusDisplayingTime = 0;
                int random = (int) (Math.random() * 21);
                number = numbers[random];
                imageViewNumbersAnimPlace.setImageDrawable(null);
                imageViewNumbersAnimPlace.setBackgroundResource(intAnimationsOn[number - 10]);
                final AnimationDrawable animation = (AnimationDrawable) imageViewNumbersAnimPlace.getBackground();
                class Starter implements Runnable {
                    public void run() {
                        animation.start();
                    }
                }
                for (int i = 0; i < numbersCells.length; i++) {
                    numbersCells[i].setEnabled(false);
                    if (clickedIndexesList.contains(i)) {
                        cellNumbers[i] = 1 + (int) (9 * Math.random());
                        numbersCells[i].setImageDrawable(getResources().getDrawable(white[cellNumbers[i] - 1]));
                    }
                }
                clickedIndexesList.clear();
                imageViewNumbersAnimPlace.post(new Starter());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chooseNumbers();
                    }
                }, 750);
            }
        });
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
                        if (timeAmount == 60000)
                            textViewNumbersTimer.setText("1:00");
                        else if (timeAmount < 10000)
                            textViewNumbersTimer.setText("0:0" + timeAmount / 1000);
                        else
                            textViewNumbersTimer.setText("0:" + timeAmount / 1000);
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
}