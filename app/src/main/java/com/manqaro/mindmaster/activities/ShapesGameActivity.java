package com.manqaro.mindmaster.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manqaro.mindmaster.R;
import com.manqaro.mindmaster.adapters.GridViewContainersAdapter;

import java.util.ArrayList;

public class ShapesGameActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    int shapesNumber, step, timeAmount, gameBoardWidth, gameBoardHeight, filled, correctFrom5, delta, bonusDisplayingTime;
    ArrayList<Integer> shapesBitmapsList, shapesContainersList;
    Thread timeThread;
    RelativeLayout timeOutLayout, resumeExitLayout, pauseMenuLayout, shapesGameBoard, activityShapesGameRules,
            shapesGridViewCover, shapesCover;
    GridView gridViewShapesContainer;
    ImageView imageViewShapesRulesStart, imageViewResumeIcon, imageViewExitIcon, imageViewResume, imageViewExit,
            imageViewBackFromGame, imageViewPauseGame, imageViewActionBarTimer, imageViewShapesOk,
            imageViewResumeGameIcon, imageViewRestartGame, imageViewMusic, imageViewSound, imageViewExitGame,
            imageViewRestartIconTimeOut, imageViewExitIconTimeOut, imageViewRestartIconTimeOutCombo, imageViewExitIconTimeOutCombo;
    int[] shapesDrawables;
    ImageView[] shapesBoxes;
    TextView textViewShapesTimer, textViewCurrentScore, textViewHighscore;
    Intent intent0;
    Handler handler;
    Boolean isCombo;
    GridViewContainersAdapter gridViewContainersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent0 = getIntent();
        isCombo = intent0.getBooleanExtra("combo", false);
        setContentView(R.layout.activity_shapes_game);
        if (isCombo)
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayoutCombo);
        else
            timeOutLayout = (RelativeLayout) findViewById(R.id.timeOutLayout);
        shapesGridViewCover = (RelativeLayout) findViewById(R.id.shapesGridViewCover);
        shapesCover = (RelativeLayout) findViewById(R.id.shapesCover);
        activityShapesGameRules = (RelativeLayout) findViewById(R.id.activityShapesGameRules);
        activityShapesGameRules = (RelativeLayout) findViewById(R.id.activityShapesGameRules);
        imageViewShapesRulesStart = (ImageView) findViewById(R.id.imageViewShapesRulesStart);
        resumeExitLayout = (RelativeLayout) findViewById(R.id.resumeExitLayout);
        pauseMenuLayout = (RelativeLayout) findViewById(R.id.pauseMenuLayout);
        imageViewBackFromGame = (ImageView) findViewById(R.id.imageViewBackFromGame);
        imageViewPauseGame = (ImageView) findViewById(R.id.imageViewPauseGame);
        shapesGameBoard = (RelativeLayout) findViewById(R.id.shapesGameBoard);
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
        imageViewShapesOk = (ImageView) findViewById(R.id.imageViewShapesOk);
        imageViewRestartIconTimeOut = (ImageView) findViewById(R.id.imageViewRestartIconTimeOut);
        imageViewExitIconTimeOut = (ImageView) findViewById(R.id.imageViewExitIconTimeOut);
        imageViewRestartIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewRestartIconTimeOutCombo);
        imageViewExitIconTimeOutCombo = (ImageView) findViewById(R.id.imageViewExitIconTimeOutCombo);
        textViewShapesTimer = (TextView) findViewById(R.id.textViewTimer);
        textViewCurrentScore = (TextView) findViewById(R.id.textViewCurrentScore);
        textViewHighscore = (TextView) findViewById(R.id.textViewHighscore);
        gridViewShapesContainer = (GridView) findViewById(R.id.gridViewShapesContainer);
        gridViewShapesContainer.setOnItemClickListener(this);
        shapesContainersList = new ArrayList<>(12);
        shapesDrawables = new int[]{R.drawable.shapes_0010_shape5s,
                R.drawable.shapes_0011_shape4s,
                R.drawable.shapes_0012_shape3s,
                R.drawable.shapes_0013_shape2s,
                R.drawable.shapes_0014_shape1s,
                R.drawable.shapes_0015_shape10s,
                R.drawable.shapes_0016_shape9s,
                R.drawable.shapes_0017_shape8s,
                R.drawable.shapes_0018_shape7s,
                R.drawable.shapes_0019_shape6s};
        shapesBoxes = new ImageView[10];
        shapesBoxes[0] = (ImageView) findViewById(R.id.imageViewShapesShape1);
        shapesBoxes[1] = (ImageView) findViewById(R.id.imageViewShapesShape2);
        shapesBoxes[2] = (ImageView) findViewById(R.id.imageViewShapesShape3);
        shapesBoxes[3] = (ImageView) findViewById(R.id.imageViewShapesShape4);
        shapesBoxes[4] = (ImageView) findViewById(R.id.imageViewShapesShape5);
        shapesBoxes[5] = (ImageView) findViewById(R.id.imageViewShapesShape6);
        shapesBoxes[6] = (ImageView) findViewById(R.id.imageViewShapesShape7);
        shapesBoxes[7] = (ImageView) findViewById(R.id.imageViewShapesShape8);
        shapesBoxes[8] = (ImageView) findViewById(R.id.imageViewShapesShape9);
        shapesBoxes[9] = (ImageView) findViewById(R.id.imageViewShapesShape10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            shapesBoxes[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int k = 0; k < shapesNumber; k++)
                        if (shapesContainersList.get(k).equals(R.drawable.memorize_cell)) {
                            shapesContainersList.set(k, shapesDrawables[index]);
                            gridViewContainersAdapter = new GridViewContainersAdapter(getApplicationContext(), shapesContainersList);
                            gridViewShapesContainer.setAdapter(gridViewContainersAdapter);
                            k = shapesNumber;
                        }
                }
            });
        }
        shapesBitmapsList = new ArrayList<>();
        handler = new Handler();
        timeThread = new Thread(new CheckTime());
        step = 0;
        shapesNumber = 3;

        imageViewResumeIcon.setOnClickListener(this);
        imageViewExitIcon.setOnClickListener(this);
        imageViewResume.setOnClickListener(this);
        imageViewExit.setOnClickListener(this);
        imageViewPauseGame.setOnClickListener(this);
        imageViewBackFromGame.setOnClickListener(this);
        imageViewShapesRulesStart.setOnClickListener(this);
        imageViewResumeGameIcon.setOnClickListener(this);
        imageViewRestartGame.setOnClickListener(this);
        imageViewMusic.setOnClickListener(this);
        imageViewSound.setOnClickListener(this);
        imageViewExitGame.setOnClickListener(this);
        imageViewRestartIconTimeOut.setOnClickListener(this);
        imageViewExitIconTimeOut.setOnClickListener(this);
        imageViewRestartIconTimeOutCombo.setOnClickListener(this);
        imageViewExitIconTimeOutCombo.setOnClickListener(this);
        imageViewShapesOk.setOnClickListener(this);

        shapesGameBoard.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                shapesGameBoard.getHitRect(rect);
                gameBoardWidth = rect.width();
                gameBoardHeight = rect.height();
                shapesGameBoard.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.imageViewShapesRulesStart:
                //Animation
                startPlay();
                break;
            case R.id.imageViewRestartGame:
                startActivity(new Intent(this, ShapesGameActivity.class));
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
                startActivity(new Intent(this, ShapesGameActivity.class));
                finish();
                break;
            case R.id.imageViewExitIconTimeOut:
            case R.id.imageViewExitIconTimeOutCombo:
                //Animation
                intent.putExtra("status", "exit");
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewShapesOk:
                //Animation
                delta = 0;
                int correct = 0;
                bonusDisplayingTime = 0;
                for (int i = 0; i < shapesNumber; i++)
                    if (shapesContainersList.get(i).equals(shapesBitmapsList.get(i)))
                        correct++;
                if (correct == shapesNumber) {
                    imageViewShapesOk.setImageDrawable(getResources().getDrawable(R.drawable.correct));
                    correctFrom5++;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shapesGridViewCover.setVisibility(View.VISIBLE);
                        }
                    }, 600);
                } else {
                    for (int k = 0; k < shapesNumber; k++) {
                        gridViewContainersAdapter = new GridViewContainersAdapter(getApplicationContext(), shapesBitmapsList);
                        gridViewShapesContainer.setAdapter(gridViewContainersAdapter);
                        imageViewShapesOk.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                    }
                }

                step++;
                shapesCover.startAnimation(AnimationUtils.loadAnimation(this, R.anim.hideanim));
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shapesCover.setVisibility(View.INVISIBLE);
                    }
                }, 300);

                if (step % 5 == 0) {
                    if (correctFrom5 == 5) {
                        bonusDisplayingTime = 400;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imageViewShapesOk.setImageDrawable(getResources().getDrawable(R.drawable.bonus_plus5));
                            }
                        }, 850);
                        timeAmount = timeAmount + 5000;
                        if (timeAmount < 10000) {
                            textViewShapesTimer.setTextColor(Color.RED);
                            textViewShapesTimer.setText("0:0" + timeAmount / 1000);
                        } else {
                            textViewShapesTimer.setTextColor(Color.BLACK);
                            textViewShapesTimer.setText("0:" + timeAmount / 1000);
                        }
                    }
                    correctFrom5 = 0;
                    shapesNumber++;
                }

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageViewShapesOk.setImageDrawable(getResources().getDrawable(R.drawable.ok));
                        displayShapes(shapesNumber);
                        shapesGridViewCover.setVisibility(View.INVISIBLE);
                        shapesGameBoard.setVisibility(View.VISIBLE);
                        shapesCover.setVisibility(View.VISIBLE);
                    }
                }, 850 + bonusDisplayingTime);
                break;
        }
    }

    private void startPlay() {
        timeAmount = 60000;
        activityShapesGameRules.setVisibility(View.GONE);
        displayShapes(shapesNumber);
        shapesGameBoard.setVisibility(View.VISIBLE);
        //game code
    }

    private void displayShapes(final int k) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                delta = 0;
                shapesGameBoard.setVisibility(View.VISIBLE);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                shapesBitmapsList.clear();
                final ImageView[] displayingShapes = new ImageView[k];
                final RelativeLayout.LayoutParams displayingShapesLayoutParams[] = new RelativeLayout.LayoutParams[k];
                int diameter = getResources().getDimensionPixelSize(R.dimen._53sdp);
                int marginLeft = (int) (Math.random() * (gameBoardWidth - diameter));
                int marginTop = (int) (Math.random() * (gameBoardHeight - diameter));
                for (int count = 0; count < k; count++) {
                    displayingShapes[count] = new ImageView(getApplicationContext());
                    displayingShapesLayoutParams[count] = new RelativeLayout.LayoutParams(diameter, diameter);
                    displayingShapesLayoutParams[count].setMargins(marginLeft, marginTop, 0, 0);
                    marginLeft = (int) (Math.random() * (gameBoardWidth - diameter));
                    marginTop = (int) (Math.random() * (gameBoardHeight - diameter));
                }
                for (int count = 0; count < k; count++) {
                    final int index = count;
                    final int randomIndex = (int) (Math.random() * 10); //01.04
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            displayingShapes[index].setImageResource(shapesDrawables[randomIndex]); //01.04
                            shapesBitmapsList.add(shapesDrawables[randomIndex]); //01.04
                            shapesGameBoard.addView(displayingShapes[index], displayingShapesLayoutParams[index]);
                            if (index >= 1)
                                shapesGameBoard.removeView(displayingShapes[index - 1]);
                        }
                    }, 1000 * (index + 1));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shapesGameBoard.removeView(displayingShapes[shapesNumber - 1]);
                        shapesGameBoard.setVisibility(View.INVISIBLE);
                        chooseShapes(shapesNumber);
                    }
                }, 1000 * (k + 1));
            }
        });
    }

    private void chooseShapes(int k) {
        delta = 100;
        shapesContainersList.clear();
        for (int i = 0; i < k; i++) //01.04
            shapesContainersList.add(R.drawable.memorize_cell); //01.04
        gridViewContainersAdapter = new GridViewContainersAdapter(this, shapesContainersList); //01.04
        gridViewShapesContainer.setAdapter(gridViewContainersAdapter); //01.04
        gridViewShapesContainer.setNumColumns(Math.min(shapesNumber, 6)); //01.04
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
        shapesContainersList.set(position, R.drawable.memorize_cell);
        gridViewContainersAdapter = new GridViewContainersAdapter(getApplicationContext(), shapesContainersList);
        gridViewShapesContainer.setAdapter(gridViewContainersAdapter);
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
                            for (int i = 0; i < shapesNumber; i++)
                                if (!shapesContainersList.get(i).equals(R.drawable.memorize_cell))
                                    filled++;
                            if (filled == shapesNumber)
                                imageViewShapesOk.setEnabled(true);
                            else imageViewShapesOk.setEnabled(false);
                            if (timeAmount < 10000) {
                                textViewShapesTimer.setTextColor(Color.RED);
                                textViewShapesTimer.setText("0:0" + timeAmount / 1000);
                            } else {
                                textViewShapesTimer.setTextColor(Color.BLACK);
                                textViewShapesTimer.setText("0:" + timeAmount / 1000);
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