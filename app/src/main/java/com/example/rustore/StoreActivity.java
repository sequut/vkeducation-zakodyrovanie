package com.example.rustore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rustore.adapters.AppsAdapter;
import com.example.rustore.models.AppItem;
import com.example.rustore.network.ApiClient;
import com.example.rustore.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppsAdapter adapter;
    private static final String TAG = "StoreActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        recyclerView = findViewById(R.id.recyclerViewApps);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadAppsFromApi();
    }

    private void loadAppsFromApi() {
        ApiService apiService = ApiClient.getApiService();
        Call<List<AppItem>> call = apiService.getApps();
        call.enqueue(new Callback<List<AppItem>>() {
            @Override
            public void onResponse(Call<List<AppItem>> call, Response<List<AppItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AppItem> apps = response.body();
                    adapter = new AppsAdapter(StoreActivity.this, apps);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(StoreActivity.this, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<AppItem>> call, Throwable t) {
                Toast.makeText(StoreActivity.this, "Не удалось загрузить данные", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API call failed", t);
            }
        });
    }
}
