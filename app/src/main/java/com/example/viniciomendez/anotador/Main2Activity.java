package com.example.viniciomendez.anotador;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
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

    private List<Equipo> equipos;
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
                Intent intent = new Intent(getBaseContext(),AddTeamActivity.class);
                startActivity(intent);
            }
        });
        db =FirebaseFirestore.getInstance();

        equipos = new ArrayList<Equipo>();
        ///////////////////////////////////////////////////
        db.collection("Equipo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Success", document.getId() + " => " + document.getData());
                                Log.d("nombre",(String) document.get("nombre"));
                                Equipo e = new Equipo((String) document.get("nombre"));
                                e.setLiga((String)document.get("liga"));
                                Log.d("liga",(String)document.get("liga"));
                                equipos.add(e);
                                Log.d("Equipo",e.getNombre());
                            }
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });
         Log.d("Conteo",String.valueOf(equipos.size()));
        ///////////////////////////////
        Equipo licey = new Equipo("Licey");
        licey.setLiga("Invierno 2018-2019");
        Equipo licey2 = new Equipo("Licey");
        licey.setLiga("Invierno 2018-2019");
        Equipo aguilas = new Equipo("Aguilas");
        aguilas.setLiga("Invierno 2018-2019");
        Equipo escogido = new Equipo("Escogido");
        escogido.setLiga("Invierno 2018-2019");
        equipos.add(licey);
        equipos.add(licey2);
        equipos.add(aguilas);
        equipos.add(escogido);
       ArrayList<String>teams = new ArrayList<String>();
        for(Equipo item : equipos) {
           teams.add(item.getNombre());
            Log.d("Array", item.getNombre());
        }
        populateListview(teams);


    }

    private void populateListview(ArrayList<String> teams) {
        //teams = new String[]{"licey", "aguilas", "escogido"};

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
