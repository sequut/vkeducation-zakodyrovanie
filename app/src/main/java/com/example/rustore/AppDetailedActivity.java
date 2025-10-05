package com.example.rustore;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.rustore.adapters.ScreenshotsAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppDetailedActivity extends AppCompatActivity {

    private ImageView appImage;
    private TextView appName, appDescription, appDownloads, appAuthor, appVersion, appAge, appTags;
    private static final String TAG = "screens";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detailed);

        appImage = findViewById(R.id.appDetailedImage);
        appName = findViewById(R.id.appDetailedName);
        appDescription = findViewById(R.id.appDetailedDescription);
        appDownloads = findViewById(R.id.appDownloads);
        appAuthor = findViewById(R.id.appAuthor);
        appVersion = findViewById(R.id.appVersion);
        appAge = findViewById(R.id.appAge);
        appTags = findViewById(R.id.appTags);

        setAppImage();
        appName.setText(getIntent().getStringExtra("appName"));
        appDescription.setText(getIntent().getStringExtra("appDescription"));
        appDownloads.setText(getIntent().getStringExtra("appDownloads"));
        appAuthor.setText(getIntent().getStringExtra("appAuthor"));
        appAge.setText(getIntent().getStringExtra("appAge"));
        appTags.setText(getIntent().getStringExtra("appTags"));
        appVersion.setText(getIntent().getStringExtra("appVersion"));

        List<String> screenshots = setScreenshots();
        ViewPager2 viewPager = findViewById(R.id.appScreenshots);
        ScreenshotsAdapter adapter = new ScreenshotsAdapter(screenshots);
        viewPager.setAdapter(adapter);


    }
    private void setAppImage(){
        String appLogoUrl = getIntent().getStringExtra("appLogo");
        if (appLogoUrl != null && !appLogoUrl.isEmpty()) {
            Glide.with(this)
                    .load(appLogoUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(appImage);
        } else {
            appImage.setImageResource(R.drawable.placeholder);
        }
    }

    private List<String> setScreenshots(){
        String screenshotsString = getIntent().getStringExtra("appScreenshots");
        List<String> screenshots = new ArrayList<>();

        if (screenshotsString != null && !screenshotsString.isEmpty()) {
            String[] urls = screenshotsString.split(",");
            for (String url : urls) {
                screenshots.add(url.trim());
            }
        }

        return screenshots;
    }
}
