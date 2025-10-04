package com.example.rustore.network;

import com.example.rustore.models.AppItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("apps")
    Call<List<AppItem>> getApps();
}
