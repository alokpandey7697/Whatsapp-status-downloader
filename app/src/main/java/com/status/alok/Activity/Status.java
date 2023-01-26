package com.status.alok.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.status.alok.Models.ListenableTabLayout;
import com.status.alok.Models.TabIndicatorFollower;
import com.status.alok.R;
import com.status.alok.adapter.SelectionsPageAdapter;


public class Status extends android.support.v4.app.Fragment {
    private ViewPager mViewPager;
    private ListenableTabLayout mTabLayout;
    private SelectionsPageAdapter mSelectionsPagerAdapter;




    public Status() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_status, container, false);

        mViewPager = v.findViewById(R.id.status_gallery_viewPager);
        View triangle = v.findViewById(R.id.status_triangle);

        mSelectionsPagerAdapter = new SelectionsPageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSelectionsPagerAdapter);

        mTabLayout = v.findViewById(R.id.status_gallery_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        TabIndicatorFollower.setupWith(mTabLayout, triangle);

        return v;
    }


}
