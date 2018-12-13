package com.example.viniciomendez.anotador;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    //private List<Equipo> equipos;
    private FirebaseFirestore db;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                 //   mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                  //  mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                  //  mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FloatingActionButton myfab = findViewById(R.id.floatingActionButton);
        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddTeamActivity.class);
                startActivity(intent);
            }
        });
        db = FirebaseFirestore.getInstance();

        db.collection("Equipo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    List<Equipo> equipos = new ArrayList<>();
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document:task.getResult()){
                            Equipo e = document.toObject(Equipo.class);
                            equipos.add(e);
                        }
                        ListView list = (ListView)findViewById(R.id.lv_teams);
                        EquiposAdapter adapter = new EquiposAdapter(getApplicationContext(),R.layout.item_team,equipos);
                        list.setAdapter(adapter);

                        }
                        else{
                        Log.d("Main2Activity", "Error getting documents: ", task.getException());
                        }
                    }
                });

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout)findViewById(R.id.swipeview);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                db.collection("Equipo")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                List<Equipo> equipos = new ArrayList<>();
                                if(task.isSuccessful()){
                                    for (QueryDocumentSnapshot document:task.getResult()){
                                        Equipo e = document.toObject(Equipo.class);
                                        equipos.add(e);
                                    }
                                    ListView list = (ListView)findViewById(R.id.lv_teams);
                                    EquiposAdapter adapter = new EquiposAdapter(getApplicationContext(),R.layout.item_team,equipos);
                                    list.setAdapter(adapter);
                                    swipe.setRefreshing(false);

                                }
                                else{
                                    Log.d("Main2Activity", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });
    }
}
