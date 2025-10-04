package com.example.rustore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.rustore.R;

public class OnBoardingAdapter extends PagerAdapter {

    private Context context;
    private int[] images;
    private String[] titles;
    private String[] descriptions;

    public OnBoardingAdapter(Context context, int[] images, String[] titles, String[] descriptions) {
        this.context = context;
        this.images = images;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_onboarding, container, false);

        ImageView imageView = view.findViewById(R.id.imageOnboarding);
        TextView titleView = view.findViewById(R.id.titleOnboarding);
        TextView descView = view.findViewById(R.id.descOnboarding);

        imageView.setImageResource(images[position]);
        titleView.setText(titles[position]);
        descView.setText(descriptions[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
