package com.duan2.thinh.newspaper.ui.fragment.soha.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.duan2.thinh.newspaper.ui.fragment.soha.SohaINFOFragment;
import com.duan2.thinh.newspaper.ui.fragment.soha.SohaPLFragment;
import com.duan2.thinh.newspaper.ui.fragment.soha.SohaThoiSuFragment;

public class SoHaPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Thời sự", "Pháp Luật", "Infographic" };
    private Context context;

    public SoHaPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SohaThoiSuFragment();
        }
        if (position == 1) {
            return new SohaPLFragment();
        } else {
            return new SohaINFOFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}