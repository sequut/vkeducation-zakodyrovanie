package com.example.rustore;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rustore.adapters.AppsAdapter;
import com.example.rustore.models.AppItem;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class StoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppsAdapter adapter;
    private List<AppItem> apps;
    private static final String TAG = "StoreActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        recyclerView = findViewById(R.id.recyclerViewApps);
        Log.i(TAG, recyclerView.toString());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apps = new ArrayList<>();
        apps.add(new AppItem("App 1", "Description 1", R.drawable.img1));
        apps.add(new AppItem("App 2", "Description 2", R.drawable.img2));
        apps.add(new AppItem("App 3", "Description 3", R.drawable.img3));

        adapter = new AppsAdapter(this, apps);
        recyclerView.setAdapter(adapter);
    }
}
