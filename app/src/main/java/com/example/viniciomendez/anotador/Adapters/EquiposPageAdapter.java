package com.example.viniciomendez.anotador.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.viniciomendez.anotador.CalendarioFragment;
import com.example.viniciomendez.anotador.LineupFragment;
import com.example.viniciomendez.anotador.StatsFragment;

public class EquiposPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String teamId;
    public EquiposPageAdapter(FragmentManager fm, int numOfTabs,String teamID) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
        this.teamId = teamID;
    }

    public EquiposPageAdapter(FragmentManager fm) {
        super(fm);
        this.mNumOfTabs = 3;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Log.d("POSICION",String.valueOf(position));
                CalendarioFragment cf = new CalendarioFragment();
                Bundle args = new Bundle();
                args.putCharSequence("teamId", this.teamId);
               cf.setArguments(args);
                return cf;
            case 1:
                Log.d("POSICION",String.valueOf(position));
                StatsFragment sf = new StatsFragment();
                args = new Bundle();
                args.putCharSequence("teamId", this.teamId);
                sf.setArguments(args);
               return sf;
            case 2:
                Log.d("POSICION",String.valueOf(position));
                LineupFragment lf = new LineupFragment();
                args = new Bundle();
                args.putCharSequence("teamId", this.teamId);
                lf.setArguments(args);
                return lf;


            default:
                return cf = new CalendarioFragment();
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
