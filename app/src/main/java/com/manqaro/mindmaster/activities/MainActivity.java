package com.manqaro.mindmaster.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.manqaro.mindmaster.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewActionBarMindMaster, imageViewPlay, imageViewEllipse, imageViewShadow, imageViewFooter,
            imageViewFooterTextMainActivity, imageViewActionBarCombo, imageViewBackFromCombo, imageViewColorsGame, imageViewColorsGameShadow,
            imageViewMathGame, imageViewMathGameShadow, imageViewMemorizeGame, imageViewMemorizeGameShadow, imageViewPairsGame,
            imageViewPairsGameShadow, imageViewNumbersGame, imageViewNumbersGameShadow, imageViewShapesGame,
            imageViewComboRulesBack, imageViewComboRulesStart, imageViewAd, imageViewX, imageViewMathOptionsEasy,
            imageViewMathOptionsHard, imageViewMathOptionsBack;
    Intent intent;
    int appLoads;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        appLoads = sharedPreferences.getInt("appLoads", 0);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putInt("appLoads", appLoads + 1);
        ed.commit();

        Intent intent0 = getIntent();
        if (intent0.getStringExtra("status") == null && appLoads < 6) {
            setContentView(R.layout.ad);
            imageViewAd = (ImageView) findViewById(R.id.imageViewAd);
            imageViewX = (ImageView) findViewById(R.id.imageViewX);
            imageViewAd.setOnClickListener(this);
            imageViewX.setOnClickListener(this);
        } else startMainActivity();
    }

    private void startMainActivity() {
        setContentView(R.layout.activity_main);
        imageViewActionBarMindMaster = (ImageView) findViewById(R.id.imageViewActionBarMindMaster);
        imageViewPlay = (ImageView) findViewById(R.id.imageViewPlay);
        imageViewEllipse = (ImageView) findViewById(R.id.imageViewEllipse);
        imageViewShadow = (ImageView) findViewById(R.id.imageViewShadow);
        imageViewFooter = (ImageView) findViewById(R.id.imageViewFooter);
        imageViewFooterTextMainActivity = (ImageView) findViewById(R.id.imageViewFooterTextMainActivity);
        imageViewActionBarMindMaster.setOnClickListener(this);

        imageViewPlay.setOnClickListener(this);
        imageViewEllipse.setOnClickListener(this);
        imageViewShadow.setOnClickListener(this);
        imageViewFooter.setOnClickListener(this);
        imageViewFooterTextMainActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewAd:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.hidden.functions")));
                break;
            case R.id.imageViewX:
                startMainActivity();
                break;
            case R.id.imageViewActionBarMindMaster:
            case R.id.imageViewPlay:
            case R.id.imageViewEllipse:
            case R.id.imageViewShadow:
                goToMainCombo();
                break;
            case R.id.imageViewFooter:
            case R.id.imageViewFooterTextMainActivity:
                //                Animation
                goToLoginPage();
                break;
            case R.id.imageViewActionBarCombo:
                //                Animation4
                goToMainRules();
                break;
            case R.id.imageViewBackFromCombo:
                //                Animation
                startMainActivity();
                break;
            case R.id.imageViewColorsGame:
            case R.id.imageViewColorsGameShadow:
//                Animation
                intent = new Intent(this, ColorsGameActivity.class);
                intent.putExtra("combo", false);
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewMathGame:
            case R.id.imageViewMathGameShadow:
//                Animation
                setContentView(R.layout.activity_main_math_options);
                imageViewMathOptionsEasy = (ImageView) findViewById(R.id.imageViewMathOptionsEasy);
                imageViewMathOptionsHard = (ImageView) findViewById(R.id.imageViewMathOptionsHard);
                imageViewMathOptionsBack = (ImageView) findViewById(R.id.imageViewMathOptionsBack);
                imageViewMathOptionsEasy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(getApplicationContext(), MathGameActivity.class);
                        intent.putExtra("combo", false);
                        intent.putExtra("level", "easy");
                        startActivity(intent);
                        finish();
                    }
                });
                imageViewMathOptionsHard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(getApplicationContext(), MathGameActivity.class);
                        intent.putExtra("combo", false);
                        intent.putExtra("level", "hard");
                        startActivity(intent);
                        finish();
                    }
                });
                imageViewMathOptionsBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToMainCombo();
                    }
                });
                break;
            case R.id.imageViewMemorizeGame:
            case R.id.imageViewMemorizeGameShadow:
//                Animation
                intent = new Intent(this, MemorizeGameActivity.class);
                intent.putExtra("combo", false);
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewPairsGame:
            case R.id.imageViewPairsGameShadow:
//                Animation
                intent = new Intent(this, PairsGameActivity.class);
                intent.putExtra("combo", false);
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewNumbersGame:
            case R.id.imageViewNumbersGameShadow:
//                Animation
                intent = new Intent(this, NumbersGameActivity.class);
                intent.putExtra("combo", false);
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewShapesGame:
//            case R.id.imageViewShapesGameShadow:
//                Animation
                intent = new Intent(this, ShapesGameActivity.class);
                intent.putExtra("combo", false);
                startActivity(intent);
                finish();
                break;
            case R.id.imageViewComboRulesBack:
//                Animation
                goToMainCombo();
                break;
            case R.id.imageViewComboRulesStart:
//                Animation
                intent = new Intent(this, ColorsGameActivity.class);
                intent.putExtra("combo", true);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void goToMainRules() {
        setContentView(R.layout.activity_main_combo_rules);
        imageViewComboRulesBack = (ImageView) findViewById(R.id.imageViewComboRulesBack);
        imageViewComboRulesStart = (ImageView) findViewById(R.id.imageViewComboRulesStart);
        imageViewComboRulesBack.setOnClickListener(this);
        imageViewComboRulesStart.setOnClickListener(this);
    }

    private void goToMainCombo() {
        setContentView(R.layout.activity_main_combo);

        imageViewActionBarCombo = (ImageView) findViewById(R.id.imageViewActionBarCombo);
        imageViewBackFromCombo = (ImageView) findViewById(R.id.imageViewBackFromCombo);
        imageViewColorsGame = (ImageView) findViewById(R.id.imageViewColorsGame);
        imageViewColorsGameShadow = (ImageView) findViewById(R.id.imageViewColorsGameShadow);
        imageViewMathGame = (ImageView) findViewById(R.id.imageViewMathGame);
        imageViewMathGameShadow = (ImageView) findViewById(R.id.imageViewMathGameShadow);
        imageViewMemorizeGame = (ImageView) findViewById(R.id.imageViewMemorizeGame);
        imageViewMemorizeGameShadow = (ImageView) findViewById(R.id.imageViewMemorizeGameShadow);
        imageViewPairsGame = (ImageView) findViewById(R.id.imageViewPairsGame);
        imageViewPairsGameShadow = (ImageView) findViewById(R.id.imageViewPairsGameShadow);
        imageViewNumbersGame = (ImageView) findViewById(R.id.imageViewNumbersGame);
        imageViewNumbersGameShadow = (ImageView) findViewById(R.id.imageViewNumbersGameShadow);
        imageViewShapesGame = (ImageView) findViewById(R.id.imageViewShapesGame);

        imageViewActionBarCombo.setOnClickListener(this);
        imageViewBackFromCombo.setOnClickListener(this);
        imageViewColorsGame.setOnClickListener(this);
        imageViewColorsGameShadow.setOnClickListener(this);
        imageViewMathGame.setOnClickListener(this);
        imageViewMathGameShadow.setOnClickListener(this);
        imageViewMemorizeGame.setOnClickListener(this);
        imageViewMemorizeGameShadow.setOnClickListener(this);
        imageViewPairsGame.setOnClickListener(this);
        imageViewPairsGameShadow.setOnClickListener(this);
        imageViewNumbersGame.setOnClickListener(this);
        imageViewNumbersGameShadow.setOnClickListener(this);
        imageViewShapesGame.setOnClickListener(this);
//       ImageView imageViewShapesGameShadow = (ImageView) findViewById(R.id.imageViewShapesGameShadow);
//        imageViewShapesGameShadow.setOnClickListener(this);
    }

    private void goToLoginPage() {

    }
}