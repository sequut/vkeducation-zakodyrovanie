package com.example.rustore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.rustore.adapters.OnBoardingAdapter;
import android.util.Log;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnNext, btnSkip;
    private int current = 0;
    private static final String TAG = "OnBoardingActivity";

    private int[] layouts = {
            R.layout.activity_onboarding_1,
            R.layout.activity_onboarding_2,
            R.layout.activity_onboarding_3,
            R.layout.activity_onboarding_4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        boolean firstLaunch = preferences.getBoolean("firstLaunch", true);
        //firstLaunch=true;
        if (!firstLaunch) {
            Log.i(TAG, "skipping onboarding");
            startActivity(new Intent(this, StoreActivity.class));
            finish();
            return;
        }

        setupFirstLayout(preferences);
    }

    private void setupFirstLayout(SharedPreferences preferences){
        setContentView(layouts[0]);
        Button button1nxt = findViewById(R.id.btnNext_1);
        Button button1skip = findViewById(R.id.btnSkip_1);


        button1nxt.setOnClickListener(v -> {
            setupSecondLayout(preferences);
        });

        button1skip.setOnClickListener(v -> completeOnBoarding(preferences));
    }

    private void setupSecondLayout(SharedPreferences preferences){
        setContentView(layouts[1]);
        Button button2nxt = findViewById(R.id.btnNext_2);
        Button button2skip = findViewById(R.id.btnSkip_2);


        button2nxt.setOnClickListener(v -> {
            setupThirdLayout(preferences);
        });

        button2skip.setOnClickListener(v -> completeOnBoarding(preferences));
    }

    private void setupThirdLayout(SharedPreferences preferences){
        setContentView(layouts[2]);
        Button button3nxt = findViewById(R.id.btnNext_3);
        Button button3skip = findViewById(R.id.btnSkip_3);


        button3nxt.setOnClickListener(v -> {
            setupFourthLayout(preferences);
        });

        button3skip.setOnClickListener(v -> completeOnBoarding(preferences));
    }

    private void setupFourthLayout(SharedPreferences preferences){
        setContentView(layouts[3]);
        Button button4nxt = findViewById(R.id.btnNext_4);
        Button button4skip = findViewById(R.id.btnSkip_4);


        button4nxt.setOnClickListener(v -> completeOnBoarding(preferences));

        button4skip.setOnClickListener(v -> completeOnBoarding(preferences));
    }

    private void completeOnBoarding(SharedPreferences preferences){
        preferences.edit().putBoolean("firstLaunch", false).apply();
        startActivity(new Intent(this, StoreActivity.class));
        finish();
    }
}
