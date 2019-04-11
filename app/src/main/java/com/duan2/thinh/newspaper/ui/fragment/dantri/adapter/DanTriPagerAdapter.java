package com.duan2.thinh.newspaper.ui.fragment.dantri.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.duan2.thinh.newspaper.ui.fragment.dantri.DanTriGDFragment;
import com.duan2.thinh.newspaper.ui.fragment.dantri.DanTriKHCNFragment;
import com.duan2.thinh.newspaper.ui.fragment.dantri.DanTriVanHoaFragment;



public class DanTriPagerAdapter extends FragmentPagerAdapter {

//    final int PAGE_COUNT = 3;
//    private String tabTitles[] = new String[] { "KH - CN", "Giáo Dục", "Văn Hóa" };
final int PAGE_COUNT = 1;
    private String tabTitles[] = new String[] { "Dân Trí" };
    private Context context;

    public DanTriPagerAdapter(FragmentManager fm, Context context) {
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
            return new DanTriKHCNFragment();
        }
        if (position == 1) {
            return new DanTriGDFragment();
        } else {
            return new DanTriVanHoaFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
