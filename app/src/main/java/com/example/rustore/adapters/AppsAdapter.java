package com.example.rustore.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rustore.AppDetailedActivity;
import com.example.rustore.R;
import com.example.rustore.models.AppItem;
import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppViewHolder> {

    private Context context;
    private List<AppItem> apps;

    public AppsAdapter(Context context, List<AppItem> apps) {
        this.context = context;
        this.apps = apps;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        AppItem app = apps.get(position);
        holder.name.setText(app.getName());
        holder.image.setImageResource(app.getImageRes());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AppDetailedActivity.class);
            intent.putExtra("name", app.getName());
            intent.putExtra("description", app.getDescription());
            intent.putExtra("imageRes", app.getImageRes());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.appImage);
            name = itemView.findViewById(R.id.appName);
        }
    }
}
