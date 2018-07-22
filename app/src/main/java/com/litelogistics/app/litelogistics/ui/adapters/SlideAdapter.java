package com.litelogistics.app.litelogistics.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.litelogistics.app.litelogistics.R;
import com.litelogistics.app.litelogistics.models.Slide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lekan Adigun on 7/17/2018.
 */

public class SlideAdapter extends PagerAdapter {

    private List<Slide> mSlides = new ArrayList<>();

    public SlideAdapter() {
        createSlides();
    }

    private void createSlides() {


        mSlides.add(new Slide("Fast", "A very fast transaction and other captions we can think of",
                R.drawable.ic_flash_on_black_24dp));
        mSlides.add(new Slide("Secure", "All connections are completely secured and encrypted and other captions we can think of",
                R.drawable.ic_security_black_24dp));
        mSlides.add(new Slide("Reliable", "Pay and receive payment even in the middle of the night" +
                " and other captions we can think of",
                R.drawable.ic_beenhere_black_24dp));
    }

    @Override
    public int getCount() {
        return mSlides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.layout_slide, container, false);

        Slide slide = mSlides.get(position);
        TextView title = view.findViewById(R.id.tv_title_slide);
        TextView subTitle = view.findViewById(R.id.tv_subtitle_slide);
        ImageView icon = view.findViewById(R.id.iv_slide);

        title.setText(slide.title);
        subTitle.setText(slide.subTitle);
        icon.setColorFilter(ContextCompat.getColor(container.getContext(), R.color.white));
        icon.setImageResource(slide.icon);

        container.addView(view);
        return view;
    }
}
