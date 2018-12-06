package com.example.viniciomendez.anotador;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private List<Equipo> equipos;
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
                Intent intent = new Intent(getBaseContext(),AddTeamActivity.class);
                startActivity(intent);
            }
        });
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
        populateListview();

    }

    private void populateListview() {
        String[] teams = {"licey","aguilas","escogido"};
        EquiposAdapter adapter = new EquiposAdapter(this,R.layout.item_team,equipos);
        final ListView list = findViewById(R.id.lv_teams);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Equipo o = (Equipo)list.getItemAtPosition(position);
                String str = (String) o.getNombre(); //As you are using Default String Adapter
                Toast.makeText(getBaseContext(),o.getNombre(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
