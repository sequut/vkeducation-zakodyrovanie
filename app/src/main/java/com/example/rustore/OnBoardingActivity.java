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
        if (!firstLaunch) {
            Log.i(TAG, "skipping onboarding");
            startActivity(new Intent(this, StoreActivity.class));
            finish();
            return;
        }


        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        OnBoardingAdapter adapter = new OnBoardingAdapter(this, layouts);
        viewPager.setAdapter(adapter);

        btnNext.setOnClickListener(v -> {
            int next = viewPager.getCurrentItem() + 1;
            if (next < layouts.length) {
                viewPager.setCurrentItem(next);
            } else {
                completeOnBoarding(preferences);
            }
        });

        btnSkip.setOnClickListener(v -> completeOnBoarding(preferences));
    }

    private void completeOnBoarding(SharedPreferences preferences){
        preferences.edit().putBoolean("firstLaunch", false).apply();
        startActivity(new Intent(this, StoreActivity.class));
        finish();
    }
}
