package com.example.viniciomendez.anotador.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.viniciomendez.anotador.CalendarioFragment;

public class EquiposPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public EquiposPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    public EquiposPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                CalendarioFragment cf = new CalendarioFragment();
                return cf;


            default:
                return cf = new CalendarioFragment();
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
