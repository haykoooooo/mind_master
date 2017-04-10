package com.manqaro.mindmaster.activities;

import android.content.Intent;
import android.graphics.Color;
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
import java.util.Collections;
import java.util.TreeSet;

public class PairsGameActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout resumeExitLayout, pauseMenuLayout, timeOutLayout, activityPairsGameRules;
    RelativeLayout[] level;
    ImageView imageViewPairsRulesStart, imageViewResumeIcon, imageViewExitIcon, imageViewResume, imageViewExit,
            imageViewBackFromGame, imageViewPauseGame, imageViewActionBarTimer, imageViewResumeGameIcon, imageViewRestartGame,
            imageViewMusic, imageViewSound, imageViewExitGame, imageViewRestartIconTimeOut, imageViewExitIconTimeOut,
            imageViewRestartIconTimeOutCombo, imageViewExitIconTimeOutCombo;
    ImageView[] imagesLevel1, imagesLevel2, imagesLevel3, imagesLevel4, imagesLevel5;
    TextView textViewPairsTimer;
    Boolean isCombo;
    Intent intent0;
    Handler handler;
    int timeAmount, rows, columns, currentLevel, clicks, opened, delta;
    int[] characters, intAnimations, intAnimationsOn, intAnimationsOff;
    ArrayList<Integer> displayedCharactersIndexesList, lastImages, lastCells;
    TreeSet<Integer> openedSet;
    ArrayList<ImageView> imagesList;
    Thread timeThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent0 = getIntent();
        isCombo = intent0.getBooleanExtra("combo", false);
        setContentView(R.layout.activity_pairs_game);
        if (isCombo)
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayoutCombo);
        else
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayout);

        level = new RelativeLayout[5];
        imagesLevel1 = new ImageView[12];
        imagesLevel2 = new ImageView[16];
        imagesLevel3 = new ImageView[20];
        imagesLevel4 = new ImageView[24];
        imagesLevel5 = new ImageView[30];

        level[0] = (RelativeLayout) findViewById(R.id.pairsLevel1);
        level[1] = (RelativeLayout) findViewById(R.id.pairsLevel2);
        level[2] = (RelativeLayout) findViewById(R.id.pairsLevel3);
        level[3] = (RelativeLayout) findViewById(R.id.pairsLevel4);
        level[4] = (RelativeLayout) findViewById(R.id.pairsLevel5);

        intAnimations = new int[]{R.drawable.animation_pairs_0,
                R.drawable.animation_pairs_1,
                R.drawable.animation_pairs_2,
                R.drawable.animation_pairs_3,
                R.drawable.animation_pairs_4,
                R.drawable.animation_pairs_5,
                R.drawable.animation_pairs_6,
                R.drawable.animation_pairs_7,
                R.drawable.animation_pairs_8,
                R.drawable.animation_pairs_9,
                R.drawable.animation_pairs_10,
                R.drawable.animation_pairs_11,
                R.drawable.animation_pairs_12,
                R.drawable.animation_pairs_13,
                R.drawable.animation_pairs_14,
                R.drawable.animation_pairs_15,
                R.drawable.animation_pairs_16,
                R.drawable.animation_pairs_17,
                R.drawable.animation_pairs_18,
                R.drawable.animation_pairs_19,
                R.drawable.animation_pairs_20,
                R.drawable.animation_pairs_21,
                R.drawable.animation_pairs_22,
                R.drawable.animation_pairs_23,
                R.drawable.animation_pairs_24,
                R.drawable.animation_pairs_25,
                R.drawable.animation_pairs_26,
                R.drawable.animation_pairs_27,
                R.drawable.animation_pairs_28,
                R.drawable.animation_pairs_29};

        intAnimationsOn = new int[]{R.drawable.animation_pairs_0_on,
                R.drawable.animation_pairs_1_on,
                R.drawable.animation_pairs_2_on,
                R.drawable.animation_pairs_3_on,
                R.drawable.animation_pairs_4_on,
                R.drawable.animation_pairs_5_on,
                R.drawable.animation_pairs_6_on,
                R.drawable.animation_pairs_7_on,
                R.drawable.animation_pairs_8_on,
                R.drawable.animation_pairs_9_on,
                R.drawable.animation_pairs_10_on,
                R.drawable.animation_pairs_11_on,
                R.drawable.animation_pairs_12_on,
                R.drawable.animation_pairs_13_on,
                R.drawable.animation_pairs_14_on,
                R.drawable.animation_pairs_15_on,
                R.drawable.animation_pairs_16_on,
                R.drawable.animation_pairs_17_on,
                R.drawable.animation_pairs_18_on,
                R.drawable.animation_pairs_19_on,
                R.drawable.animation_pairs_20_on,
                R.drawable.animation_pairs_21_on,
                R.drawable.animation_pairs_22_on,
                R.drawable.animation_pairs_23_on,
                R.drawable.animation_pairs_24_on,
                R.drawable.animation_pairs_25_on,
                R.drawable.animation_pairs_26_on,
                R.drawable.animation_pairs_27_on,
                R.drawable.animation_pairs_28_on,
                R.drawable.animation_pairs_29_on};

        intAnimationsOff = new int[]{R.drawable.animation_pairs_0_off,
                R.drawable.animation_pairs_1_off,
                R.drawable.animation_pairs_2_off,
                R.drawable.animation_pairs_3_off,
                R.drawable.animation_pairs_4_off,
                R.drawable.animation_pairs_5_off,
                R.drawable.animation_pairs_6_off,
                R.drawable.animation_pairs_7_off,
                R.drawable.animation_pairs_8_off,
                R.drawable.animation_pairs_9_off,
                R.drawable.animation_pairs_10_off,
                R.drawable.animation_pairs_11_off,
                R.drawable.animation_pairs_12_off,
                R.drawable.animation_pairs_13_off,
                R.drawable.animation_pairs_14_off,
                R.drawable.animation_pairs_15_off,
                R.drawable.animation_pairs_16_off,
                R.drawable.animation_pairs_17_off,
                R.drawable.animation_pairs_18_off,
                R.drawable.animation_pairs_19_off,
                R.drawable.animation_pairs_20_off,
                R.drawable.animation_pairs_21_off,
                R.drawable.animation_pairs_22_off,
                R.drawable.animation_pairs_23_off,
                R.drawable.animation_pairs_24_off,
                R.drawable.animation_pairs_25_off,
                R.drawable.animation_pairs_26_off,
                R.drawable.animation_pairs_27_off,
                R.drawable.animation_pairs_28_off,
                R.drawable.animation_pairs_29_off};
        currentLevel = 1;

        activityPairsGameRules = (RelativeLayout) findViewById(R.id.activityPairsGameRules);
        imageViewPairsRulesStart = (ImageView) findViewById(R.id.imageViewPairsRulesStart);
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
        textViewPairsTimer = (TextView) findViewById(R.id.textViewTimer);
        imageViewRestartIconTimeOut = (ImageView) findViewById(R.id.imageViewRestartIconTimeOut);
        imageViewExitIconTimeOut = (ImageView) findViewById(R.id.imageViewExitIconTimeOut);
        imageViewRestartIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewRestartIconTimeOutCombo);
        imageViewExitIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewExitIconTimeOutCombo);
        handler = new Handler();
        timeThread = new Thread(new CheckTime());
        textViewPairsTimer.setText("0:30");
        characters = new int[]{R.drawable.pairs_0,
                R.drawable.pairs_1,
                R.drawable.pairs_2,
                R.drawable.pairs_3,
                R.drawable.pairs_4,
                R.drawable.pairs_5,
                R.drawable.pairs_6,
                R.drawable.pairs_7,
                R.drawable.pairs_8,
                R.drawable.pairs_9,
                R.drawable.pairs_10,
                R.drawable.pairs_11,
                R.drawable.pairs_12,
                R.drawable.pairs_13,
                R.drawable.pairs_14,
                R.drawable.pairs_15,
                R.drawable.pairs_16,
                R.drawable.pairs_17,
                R.drawable.pairs_18,
                R.drawable.pairs_19,
                R.drawable.pairs_20,
                R.drawable.pairs_21,
                R.drawable.pairs_22,
                R.drawable.pairs_23,
                R.drawable.pairs_24,
                R.drawable.pairs_25,
                R.drawable.pairs_26,
                R.drawable.pairs_27,
                R.drawable.pairs_28,
                R.drawable.pairs_29};
        displayedCharactersIndexesList = new ArrayList<>(30);
        imagesList = new ArrayList<>(30);
        lastImages = new ArrayList<>(2);
        lastCells = new ArrayList<>(2);
        openedSet = new TreeSet<Integer>();

        imageViewResumeIcon.setOnClickListener(this);
        imageViewExitIcon.setOnClickListener(this);
        imageViewResume.setOnClickListener(this);
        imageViewExit.setOnClickListener(this);
        imageViewPauseGame.setOnClickListener(this);
        imageViewBackFromGame.setOnClickListener(this);
        imageViewPairsRulesStart.setOnClickListener(this);
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
            case R.id.imageViewPairsRulesStart:
                //Animation
                activityPairsGameRules.setVisibility(View.INVISIBLE);
                startPlay();
                break;
            case R.id.imageViewRestartGame:
                startActivity(new Intent(this, PairsGameActivity.class));
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
                startActivity(new Intent(this, PairsGameActivity.class));
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
        displayPairs(currentLevel);
        if (!timeThread.isAlive())
            timeThread.start();
    }

    private void displayPairs(final int k) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                delta = 0;
                clicks = 0;
                opened = 0;
                openedSet.clear();
                level[Math.min(4, currentLevel - 1)].setVisibility(View.VISIBLE);
                displayedCharactersIndexesList.clear();
                switch (k) {
                    case 1:
                        timeAmount = 30000;
                        rows = 4;
                        columns = 3;
                        imagesList.clear();
                        imagesLevel1[0] = (ImageView) findViewById(R.id.imageViewPairs1_11);
                        imagesLevel1[1] = (ImageView) findViewById(R.id.imageViewPairs1_12);
                        imagesLevel1[2] = (ImageView) findViewById(R.id.imageViewPairs1_13);
                        imagesLevel1[3] = (ImageView) findViewById(R.id.imageViewPairs1_21);
                        imagesLevel1[4] = (ImageView) findViewById(R.id.imageViewPairs1_22);
                        imagesLevel1[5] = (ImageView) findViewById(R.id.imageViewPairs1_23);
                        imagesLevel1[6] = (ImageView) findViewById(R.id.imageViewPairs1_31);
                        imagesLevel1[7] = (ImageView) findViewById(R.id.imageViewPairs1_32);
                        imagesLevel1[8] = (ImageView) findViewById(R.id.imageViewPairs1_33);
                        imagesLevel1[9] = (ImageView) findViewById(R.id.imageViewPairs1_41);
                        imagesLevel1[10] = (ImageView) findViewById(R.id.imageViewPairs1_42);
                        imagesLevel1[11] = (ImageView) findViewById(R.id.imageViewPairs1_43);
                        for (int i = 0; i < 12; i++)
                            imagesList.add(imagesLevel1[i]);
                        break;
                    case 2:
                        timeAmount = 30000;
                        rows = 4;
                        columns = 4;
                        imagesList.clear();
                        imagesLevel2[0] = (ImageView) findViewById(R.id.imageViewPairs2_11);
                        imagesLevel2[1] = (ImageView) findViewById(R.id.imageViewPairs2_12);
                        imagesLevel2[2] = (ImageView) findViewById(R.id.imageViewPairs2_13);
                        imagesLevel2[3] = (ImageView) findViewById(R.id.imageViewPairs2_14);
                        imagesLevel2[4] = (ImageView) findViewById(R.id.imageViewPairs2_21);
                        imagesLevel2[5] = (ImageView) findViewById(R.id.imageViewPairs2_22);
                        imagesLevel2[6] = (ImageView) findViewById(R.id.imageViewPairs2_23);
                        imagesLevel2[7] = (ImageView) findViewById(R.id.imageViewPairs2_24);
                        imagesLevel2[8] = (ImageView) findViewById(R.id.imageViewPairs2_31);
                        imagesLevel2[9] = (ImageView) findViewById(R.id.imageViewPairs2_32);
                        imagesLevel2[10] = (ImageView) findViewById(R.id.imageViewPairs2_33);
                        imagesLevel2[11] = (ImageView) findViewById(R.id.imageViewPairs2_34);
                        imagesLevel2[12] = (ImageView) findViewById(R.id.imageViewPairs2_41);
                        imagesLevel2[13] = (ImageView) findViewById(R.id.imageViewPairs2_42);
                        imagesLevel2[14] = (ImageView) findViewById(R.id.imageViewPairs2_43);
                        imagesLevel2[15] = (ImageView) findViewById(R.id.imageViewPairs2_44);
                        for (int i = 0; i < 16; i++)
                            imagesList.add(imagesLevel2[i]);
                        break;
                    case 3:
                        timeAmount = 60000;
                        rows = 5;
                        columns = 4;
                        imagesList.clear();
                        imagesLevel3[0] = (ImageView) findViewById(R.id.imageViewPairs3_11);
                        imagesLevel3[1] = (ImageView) findViewById(R.id.imageViewPairs3_12);
                        imagesLevel3[2] = (ImageView) findViewById(R.id.imageViewPairs3_13);
                        imagesLevel3[3] = (ImageView) findViewById(R.id.imageViewPairs3_14);
                        imagesLevel3[4] = (ImageView) findViewById(R.id.imageViewPairs3_21);
                        imagesLevel3[5] = (ImageView) findViewById(R.id.imageViewPairs3_22);
                        imagesLevel3[6] = (ImageView) findViewById(R.id.imageViewPairs3_23);
                        imagesLevel3[7] = (ImageView) findViewById(R.id.imageViewPairs3_24);
                        imagesLevel3[8] = (ImageView) findViewById(R.id.imageViewPairs3_31);
                        imagesLevel3[9] = (ImageView) findViewById(R.id.imageViewPairs3_32);
                        imagesLevel3[10] = (ImageView) findViewById(R.id.imageViewPairs3_33);
                        imagesLevel3[11] = (ImageView) findViewById(R.id.imageViewPairs3_34);
                        imagesLevel3[12] = (ImageView) findViewById(R.id.imageViewPairs3_41);
                        imagesLevel3[13] = (ImageView) findViewById(R.id.imageViewPairs3_42);
                        imagesLevel3[14] = (ImageView) findViewById(R.id.imageViewPairs3_43);
                        imagesLevel3[15] = (ImageView) findViewById(R.id.imageViewPairs3_44);
                        imagesLevel3[16] = (ImageView) findViewById(R.id.imageViewPairs3_51);
                        imagesLevel3[17] = (ImageView) findViewById(R.id.imageViewPairs3_52);
                        imagesLevel3[18] = (ImageView) findViewById(R.id.imageViewPairs3_53);
                        imagesLevel3[19] = (ImageView) findViewById(R.id.imageViewPairs3_54);
                        for (int i = 0; i < 20; i++)
                            imagesList.add(imagesLevel3[i]);
                        break;
                    case 4:
                        timeAmount = 60000;
                        rows = 6;
                        columns = 4;
                        imagesList.clear();
                        imagesLevel4[0] = (ImageView) findViewById(R.id.imageViewPairs4_11);
                        imagesLevel4[1] = (ImageView) findViewById(R.id.imageViewPairs4_12);
                        imagesLevel4[2] = (ImageView) findViewById(R.id.imageViewPairs4_13);
                        imagesLevel4[3] = (ImageView) findViewById(R.id.imageViewPairs4_14);
                        imagesLevel4[4] = (ImageView) findViewById(R.id.imageViewPairs4_21);
                        imagesLevel4[5] = (ImageView) findViewById(R.id.imageViewPairs4_22);
                        imagesLevel4[6] = (ImageView) findViewById(R.id.imageViewPairs4_23);
                        imagesLevel4[7] = (ImageView) findViewById(R.id.imageViewPairs4_24);
                        imagesLevel4[8] = (ImageView) findViewById(R.id.imageViewPairs4_31);
                        imagesLevel4[9] = (ImageView) findViewById(R.id.imageViewPairs4_32);
                        imagesLevel4[10] = (ImageView) findViewById(R.id.imageViewPairs4_33);
                        imagesLevel4[11] = (ImageView) findViewById(R.id.imageViewPairs4_34);
                        imagesLevel4[12] = (ImageView) findViewById(R.id.imageViewPairs4_41);
                        imagesLevel4[13] = (ImageView) findViewById(R.id.imageViewPairs4_42);
                        imagesLevel4[14] = (ImageView) findViewById(R.id.imageViewPairs4_43);
                        imagesLevel4[15] = (ImageView) findViewById(R.id.imageViewPairs4_44);
                        imagesLevel4[16] = (ImageView) findViewById(R.id.imageViewPairs4_51);
                        imagesLevel4[17] = (ImageView) findViewById(R.id.imageViewPairs4_52);
                        imagesLevel4[18] = (ImageView) findViewById(R.id.imageViewPairs4_53);
                        imagesLevel4[19] = (ImageView) findViewById(R.id.imageViewPairs4_54);
                        imagesLevel4[20] = (ImageView) findViewById(R.id.imageViewPairs4_61);
                        imagesLevel4[21] = (ImageView) findViewById(R.id.imageViewPairs4_62);
                        imagesLevel4[22] = (ImageView) findViewById(R.id.imageViewPairs4_63);
                        imagesLevel4[23] = (ImageView) findViewById(R.id.imageViewPairs4_64);
                        for (int i = 0; i < 24; i++)
                            imagesList.add(imagesLevel4[i]);
                        break;
                    default:
                        timeAmount = 90000;
                        textViewPairsTimer.setText("1:" + 30);
                        rows = 6;
                        columns = 5;
                        imagesList.clear();
                        imagesLevel5[0] = (ImageView) findViewById(R.id.imageViewPairs5_11);
                        imagesLevel5[1] = (ImageView) findViewById(R.id.imageViewPairs5_12);
                        imagesLevel5[2] = (ImageView) findViewById(R.id.imageViewPairs5_13);
                        imagesLevel5[3] = (ImageView) findViewById(R.id.imageViewPairs5_14);
                        imagesLevel5[4] = (ImageView) findViewById(R.id.imageViewPairs5_15);
                        imagesLevel5[5] = (ImageView) findViewById(R.id.imageViewPairs5_21);
                        imagesLevel5[6] = (ImageView) findViewById(R.id.imageViewPairs5_22);
                        imagesLevel5[7] = (ImageView) findViewById(R.id.imageViewPairs5_23);
                        imagesLevel5[8] = (ImageView) findViewById(R.id.imageViewPairs5_24);
                        imagesLevel5[9] = (ImageView) findViewById(R.id.imageViewPairs5_25);
                        imagesLevel5[10] = (ImageView) findViewById(R.id.imageViewPairs5_31);
                        imagesLevel5[11] = (ImageView) findViewById(R.id.imageViewPairs5_32);
                        imagesLevel5[12] = (ImageView) findViewById(R.id.imageViewPairs5_33);
                        imagesLevel5[13] = (ImageView) findViewById(R.id.imageViewPairs5_34);
                        imagesLevel5[14] = (ImageView) findViewById(R.id.imageViewPairs5_35);
                        imagesLevel5[15] = (ImageView) findViewById(R.id.imageViewPairs5_41);
                        imagesLevel5[16] = (ImageView) findViewById(R.id.imageViewPairs5_42);
                        imagesLevel5[17] = (ImageView) findViewById(R.id.imageViewPairs5_43);
                        imagesLevel5[18] = (ImageView) findViewById(R.id.imageViewPairs5_44);
                        imagesLevel5[19] = (ImageView) findViewById(R.id.imageViewPairs5_45);
                        imagesLevel5[20] = (ImageView) findViewById(R.id.imageViewPairs5_51);
                        imagesLevel5[21] = (ImageView) findViewById(R.id.imageViewPairs5_52);
                        imagesLevel5[22] = (ImageView) findViewById(R.id.imageViewPairs5_53);
                        imagesLevel5[23] = (ImageView) findViewById(R.id.imageViewPairs5_54);
                        imagesLevel5[24] = (ImageView) findViewById(R.id.imageViewPairs5_55);
                        imagesLevel5[25] = (ImageView) findViewById(R.id.imageViewPairs5_61);
                        imagesLevel5[26] = (ImageView) findViewById(R.id.imageViewPairs5_62);
                        imagesLevel5[27] = (ImageView) findViewById(R.id.imageViewPairs5_63);
                        imagesLevel5[28] = (ImageView) findViewById(R.id.imageViewPairs5_64);
                        imagesLevel5[29] = (ImageView) findViewById(R.id.imageViewPairs5_65);
                        for (int i = 0; i < 30; i++)
                            imagesList.add(imagesLevel5[i]);
                        break;
                }
                for (int i = 0; i < imagesList.size(); i++)
                    imagesList.get(i).setImageDrawable(null);
                final AnimationDrawable[] animations = new AnimationDrawable[rows * columns];
                for (int i = 0; i < rows * columns / 2; i++) {
                    int index = (int) (Math.random() * 30);
                    while (displayedCharactersIndexesList.contains(index))
                        index = (int) (Math.random() * 30);
                    displayedCharactersIndexesList.add(index);
                }
                for (int i = 0; i < rows * columns / 2; i++)
                    displayedCharactersIndexesList.add(displayedCharactersIndexesList.get(i));
                Collections.shuffle(displayedCharactersIndexesList);
                for (int i = 0; i < rows * columns; i++) {
                    imagesList.get(i).setBackgroundResource(intAnimations[displayedCharactersIndexesList.get(i)]);
                    animations[i] = (AnimationDrawable) imagesList.get(i).getBackground();
                }
                class Starter implements Runnable {

                    public void run() {
                        for (int i = 0; i < rows * columns; i++)
                            animations[i].start();
                    }
                }
                Starter starter = new Starter();
                for (int i = 0; i < rows * columns; i++)
                    imagesList.get(i).post(starter);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        choosePairs();
                    }
                }, 2699);
            }
        });
    }

    private void choosePairs() {
        delta = 100;
        for (int i = 0; i < imagesList.size(); i++) {
            final int finalI = i;
            imagesList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < imagesList.size(); i++)
                        imagesList.get(i).setEnabled(false);
                    clicks++;
                    imagesList.get(finalI).setImageDrawable(null);
                    imagesList.get(finalI).setBackgroundResource(intAnimationsOn[displayedCharactersIndexesList.get(finalI)]);
                    final AnimationDrawable animation = (AnimationDrawable) imagesList.get(finalI).getBackground();
                    class Starter implements Runnable {
                        public void run() {
                            animation.start();
                        }
                    }
                    lastImages.add(displayedCharactersIndexesList.get(finalI));
                    lastCells.add(finalI);
                    Starter starter = new Starter();
                    imagesList.get(finalI).post(starter);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imagesList.get(finalI).setImageDrawable(getResources().getDrawable(characters[displayedCharactersIndexesList.get(finalI)]));
                            for (int i = 0; i < imagesList.size(); i++)
                                if (!openedSet.contains(i) && i != finalI) {
                                    imagesList.get(i).setEnabled(true);
                                }
                        }
                    }, 225);
                    if (clicks % 2 == 0) {
                        for (int i = 0; i < imagesList.size(); i++)
                            imagesList.get(i).setEnabled(false);
                        if (lastImages.get(lastImages.size() - 2).equals(lastImages.get(lastImages.size() - 1))) {
                            openedSet.add(lastCells.get(lastCells.size() - 2));
                            openedSet.add(lastCells.get(lastCells.size() - 1));
                            System.out.println(lastCells.get(lastCells.size() - 2) + " is in openedSet");
                            System.out.println(lastCells.get(lastCells.size() - 1) + " is in openedSet");
                            for (int i = 0; i < imagesList.size(); i++) {
                                if (openedSet.contains(i))
                                    System.out.println("openedSet contains " + i);
                                else
                                    System.out.println("openedSet does not contain " + i);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i = 0; i < imagesList.size(); i++)
                                            if (!openedSet.contains(i)) {
                                                imagesList.get(i).setEnabled(true);
                                            }
                                    }
                                }, 225);
                            }
                        } else {
                            class Ender implements Runnable {
                                public void run() {
                                    imagesList.get(lastCells.get(lastCells.size() - 2)).setImageDrawable(null);
                                    imagesList.get(lastCells.get(lastCells.size() - 1)).setImageDrawable(null);
                                    imagesList.get(lastCells.get(lastCells.size() - 2)).setBackgroundResource(intAnimationsOff[displayedCharactersIndexesList.get(lastCells.get(lastCells.size() - 2))]);
                                    imagesList.get(lastCells.get(lastCells.size() - 1)).setBackgroundResource(intAnimationsOff[displayedCharactersIndexesList.get(lastCells.get(lastCells.size() - 1))]);
                                    AnimationDrawable animation1 = (AnimationDrawable) imagesList.get(lastCells.get(lastCells.size() - 2)).getBackground();
                                    AnimationDrawable animation2 = (AnimationDrawable) imagesList.get(lastCells.get(lastCells.size() - 1)).getBackground();
                                    animation1.start();
                                    animation2.start();
                                }
                            }
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Ender ender = new Ender();
                                    imagesList.get(lastCells.get(lastCells.size() - 2)).post(ender);
                                    imagesList.get(lastCells.get(lastCells.size() - 1)).post(ender);
                                    for (int i = 0; i < imagesList.size(); i++) {
                                        if (openedSet.contains(i))
                                            System.out.println("openedSet contains " + i);
                                        else
                                            System.out.println("openedSet does not contain " + i);
                                        if (!openedSet.contains(i)) {
                                            imagesList.get(i).setEnabled(true);
                                        }
                                    }
                                }
                            }, 225);
                        }
                        if (openedSet.size() == rows * columns) {
                            currentLevel++;
                            delta = 0;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    activityPairsGameRules.setVisibility(View.VISIBLE);
                                }
                            }, 400);
                        }
                    }
                }
            });
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
                        if (timeAmount >= 70000) {
                            textViewPairsTimer.setTextColor(Color.BLACK);
                            textViewPairsTimer.setText("1:" + (timeAmount / 1000 - 60));
                        } else if (timeAmount >= 60000 && timeAmount <= 70000) {
                            textViewPairsTimer.setTextColor(Color.BLACK);
                            textViewPairsTimer.setText("1:0" + (timeAmount / 1000 - 60));
                        } else if (timeAmount < 10000) {
                            textViewPairsTimer.setTextColor(Color.RED);
                            textViewPairsTimer.setText("0:0" + timeAmount / 1000);
                        } else {
                            textViewPairsTimer.setTextColor(Color.BLACK);
                            textViewPairsTimer.setText("0:" + timeAmount / 1000);
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