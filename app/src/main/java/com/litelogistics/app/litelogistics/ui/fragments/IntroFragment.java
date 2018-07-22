package com.litelogistics.app.litelogistics.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.litelogistics.app.litelogistics.R;
import com.litelogistics.app.litelogistics.ui.adapters.SlideAdapter;
import com.litelogistics.app.litelogistics.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lekan Adigun on 7/21/2018.
 */

public class IntroFragment extends BaseFragment {

    @BindView(R.id.view_pager_intro_activity)
    ViewPager mViewPager;
    @BindView(R.id.viewpager_indicator_main)
    ViewPagerIndicator mViewPagerIndicator;


    public static IntroFragment newInstance() {

        Bundle args = new Bundle();

        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_intro_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createSlides();
    }

    private void createSlides() {

        SlideAdapter slideAdapter = new SlideAdapter();
        mViewPager.setAdapter(slideAdapter);

        mViewPagerIndicator.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.btn_get_started_intro_fragment) public void onGetStartedClick() {

    }
}
