package com.example.rustore;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.rustore.adapters.OnBoardingAdapter;
import android.util.Log;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btnNext;
    private static final String TAG = "OnBoardingActivity";

    private int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    private String[] titles = {"Welcome", "Discover", "Download"};
    private String[] descriptions = {"Welcome to our store", "Find apps you love", "Download easily"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);

        OnBoardingAdapter adapter = new OnBoardingAdapter(this, images, titles, descriptions);
        viewPager.setAdapter(adapter);

        Log.i(TAG, "onBoarding started!!");
        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < titles.length - 1) {
                Log.i(TAG, "next page onBoarding");
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                Log.i(TAG, "onBoarding finished going next");
                startActivity(new Intent(OnBoardingActivity.this, StoreActivity.class));
                finish();
            }
        });
    }
}

