package com.example.rustore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AppDetailedActivity extends AppCompatActivity {

    private ImageView appImage;
    private TextView appName, appDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detailed);

        appImage = findViewById(R.id.appDetailImage);
        appName = findViewById(R.id.appDetailName);
        appDescription = findViewById(R.id.appDetailDescription);

        appName.setText(getIntent().getStringExtra("name"));
        appDescription.setText(getIntent().getStringExtra("description"));
        appImage.setImageResource(getIntent().getIntExtra("imageRes", R.drawable.img1));
    }
}
