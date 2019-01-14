package com.example.viniciomendez.anotador.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.viniciomendez.anotador.CalendarioFragment;
import com.example.viniciomendez.anotador.Fragments.GameDetails.BoxScoreFragment;
import com.example.viniciomendez.anotador.Fragments.GameDetails.GameInfoFragment;
import com.example.viniciomendez.anotador.Fragments.GameDetails.PlayFragment;
import com.example.viniciomendez.anotador.LineupFragment;
import com.example.viniciomendez.anotador.StatsFragment;

public class GameDetailsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String gameId;
    public GameDetailsPagerAdapter(FragmentManager fm, int numOfTabs, String gameID) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
        this.gameId = gameID;
    }

    public GameDetailsPagerAdapter(FragmentManager fm) {
        super(fm);
        this.mNumOfTabs = 3;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Log.d("POSICION",String.valueOf(position));
                GameInfoFragment gi = new GameInfoFragment();
                Bundle args = new Bundle();
                args.putCharSequence("teamId", this.gameId);
                gi.setArguments(args);
                return gi;
            case 1:
                Log.d("POSICION",String.valueOf(position));
                PlayFragment pf = new PlayFragment();
                args = new Bundle();
                args.putCharSequence("teamId", this.gameId);
                pf.setArguments(args);
                return pf;
            case 2:
                Log.d("POSICION",String.valueOf(position));
                BoxScoreFragment bs = new BoxScoreFragment();

                args = new Bundle();
                args.putCharSequence("teamId", this.gameId);
                bs.setArguments(args);
                return bs;


            default:
                return gi = new GameInfoFragment();
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
