package com.status.alok;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.status.alok.Activity.SavedGallery;
import com.status.alok.Activity.Status;
import com.status.alok.Fragment.Setting;


public class SelectionsPageAdapter extends FragmentPagerAdapter {
    public SelectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Status status = new Status();
                return status;
            case 1:
                SavedGallery savedGallery = new SavedGallery();
                return savedGallery;
            case 2:
                Setting setting = new Setting();
                return setting;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "STATUS";
            case 1:
                return "SAVED";
            case 2:
                return "SETTING";
            default:
                return null;
        }
    }
}