package com.example.viniciomendez.anotador;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.viniciomendez.anotador.Adapters.TeamsAdapter;
import com.example.viniciomendez.anotador.Entities.Equipo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<Equipo> equipos;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recicler_equipos);
        equipos = new ArrayList<Equipo>();
        Equipo licey = new Equipo("Licey");
        licey.setLiga("Invierno 2018-2019");
        Equipo aguilas = new Equipo("Aguilas");
        aguilas.setLiga("Invierno 2018-2019");
        Equipo escogido = new Equipo("Escogido");
        escogido.setLiga("Invierno 2018-2019");
        equipos.add(licey);
        equipos.add(aguilas);
        equipos.add(escogido);
        TeamsAdapter adapter = new TeamsAdapter(equipos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FloatingActionButton myfab = findViewById(R.id.floatingActionButton);
        myfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),Main2Activity.class);
                startActivity(intent);
            }
        });
    }

}
