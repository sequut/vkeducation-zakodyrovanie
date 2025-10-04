package com.example.rustore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class AppDetailedActivity extends AppCompatActivity {

    private ImageView appImage;
    private TextView appName, appDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detailed);

        appImage = findViewById(R.id.appDetailedImage);
        appName = findViewById(R.id.appDetailedName);
        appDescription = findViewById(R.id.appDetailedDescription);
        setAppImage();
        appName.setText(getIntent().getStringExtra("appName"));
        appDescription.setText(getIntent().getStringExtra("appDescription"));

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
}
