package com.example.rustore.adapters;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rustore.R;

import java.io.File;
import java.util.List;

public class ScreenshotsAdapter extends RecyclerView.Adapter<ScreenshotsAdapter.ViewHolder> {
    private List<File> files;

    public ScreenshotsAdapter(List<File> files) {
        this.files = files;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.imageView.getContext())
                .load(files.get(position))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}