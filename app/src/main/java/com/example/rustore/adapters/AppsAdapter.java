package com.example.rustore.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rustore.AppDetailedActivity;
import com.example.rustore.R;
import com.example.rustore.models.AppItem;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppViewHolder> {

    private Context context;
    private List<AppItem> apps;
    private static final String TAG = "AppAdapterActivity";

    public AppsAdapter(Context context, List<AppItem> apps) {
        this.context = context;
        this.apps = apps;
    }

    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        AppItem app = apps.get(position);
        holder.textName.setText(app.getName());
        holder.textDescription.setText(app.getDescription());

        Glide.with(context)
                .load(app.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AppDetailedActivity.class);
            intent.putExtra("appName", app.getName());
            intent.putExtra("appDescription", app.getDescription());
            intent.putExtra("appAuthor", app.getAuthor());
            intent.putExtra("appDownloads", app.getDownloads());
            intent.putExtra("appScreenshots", app.getScreenshots());
            intent.putExtra("appLogo", app.getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textName, textDescription;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.appImage);
            textName = itemView.findViewById(R.id.appName);
            textDescription = itemView.findViewById(R.id.appDescription);
        }
    }

}
