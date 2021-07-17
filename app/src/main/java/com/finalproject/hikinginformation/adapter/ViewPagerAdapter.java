package com.finalproject.hikinginformation.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.finalproject.hikinginformation.fragment.FragmentPeralatan;
import com.finalproject.hikinginformation.fragment.FragmentTips;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentPeralatan();
                break;
            case 1:
                fragment = new FragmentTips();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Peralatan";
                break;
            case 1:
                title = "Tips";
                break;
        }
        return title;
    }

}
