package com.status.alok.Activity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.status.alok.Models.ListenableTabLayout;
import com.status.alok.Models.TabIndicatorFollower;
import com.status.alok.R;
import com.status.alok.adapter.SavedGalleryAdapter;



public class SavedGallery extends android.support.v4.app.Fragment {


    public SavedGallery() {
    }


    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private ListenableTabLayout mTabLayout;
    private SavedGalleryAdapter mSavedGalleryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_saved_gallery, container, false);
        mViewPager = v.findViewById(R.id.saved_gallery_viewPager);
        View triangle = v.findViewById(R.id.triangle);

        mSavedGalleryAdapter = new SavedGalleryAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSavedGalleryAdapter);

        mTabLayout = v.findViewById(R.id.saved_gallery_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        TabIndicatorFollower.setupWith(mTabLayout, triangle);

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
















    @Override
    public void onStart() {
        super.onStart();
    }





}


