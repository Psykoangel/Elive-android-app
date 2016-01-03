
package fr.elive.android.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    public int getCount() {
        return this.fragments.size();
    }
}
