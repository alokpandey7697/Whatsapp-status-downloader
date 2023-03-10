package com.status.alok.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.status.alok.Fragment.PictureFragment;
import com.status.alok.Fragment.VideosFragment;

public class SelectionsPageAdapter extends FragmentPagerAdapter {
    public SelectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PictureFragment pictureFragment = new PictureFragment();
                return pictureFragment;
            case 1:
                VideosFragment videosFragment = new VideosFragment();
                return videosFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "PICTURES";
            case 1:
                return "VIDEOS";
            default:
                return null;
        }
    }
}