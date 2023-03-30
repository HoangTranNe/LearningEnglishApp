package edu.huflit.learningenglishapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideShowPageAdapter extends PagerAdapter {
    private Context context;
    private int[] slideLayouts = {R.layout.slide_1, R.layout.slide_2, R.layout.slide_3};

    public SlideShowPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slideLayouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(slideLayouts[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
