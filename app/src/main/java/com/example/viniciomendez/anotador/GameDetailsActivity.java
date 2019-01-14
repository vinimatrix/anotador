package com.example.viniciomendez.anotador;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.viniciomendez.anotador.Adapters.EquiposPageAdapter;
import com.example.viniciomendez.anotador.Adapters.GameDetailsPagerAdapter;
import com.example.viniciomendez.anotador.Fragments.GameDetails.BoxScoreFragment;
import com.example.viniciomendez.anotador.Fragments.GameDetails.GameInfoFragment;
import com.example.viniciomendez.anotador.Fragments.GameDetails.PlayFragment;
import com.example.viniciomendez.anotador.Fragments.GameDetails.dummy.DummyContent;
import com.google.firebase.firestore.FirebaseFirestore;


public class GameDetailsActivity extends AppCompatActivity implements GameInfoFragment.OnFragmentInteractionListener,BoxScoreFragment.OnFragmentInteractionListener,PlayFragment.OnListFragmentInteractionListener {

    private GameDetailsPagerAdapter mSectionsPagerAdapter;
    private FirebaseFirestore db;
    private ViewPager mViewPager;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setContentView(R.layout.activity_game_details);
        Intent intent = getIntent();
        final String gameID=  intent.getExtras().getString("GAME_ID");
        mSectionsPagerAdapter = new GameDetailsPagerAdapter(getSupportFragmentManager(),3,gameID);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager_game_details);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.game_details_tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
