package com.example.viniciomendez.anotador;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.viniciomendez.anotador.Adapters.EquiposAdapter;
import com.example.viniciomendez.anotador.Entities.Calendario;
import com.example.viniciomendez.anotador.Entities.Equipo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.opencensus.stats.View;

public class AddCalendario extends AppCompatActivity {

    DatePickerDialog picker;
    TimePickerDialog timepicker;
    EditText eText,hora,estadio;
    Button btnGet;
    TextView tvw;
    Intent myintent;
    String idTEam;
    List<Equipo> equipos;
    Equipo myteam;
    Spinner oponente,localVisitante;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendario);
        tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        estadio = (EditText)findViewById(R.id.txt_estadio);
        hora = (EditText) findViewById(R.id.txt_hora);
        hora.setInputType(InputType.TYPE_NULL);

        if(savedInstanceState==null) {
            Bundle extras = getIntent().getExtras();
            if(extras==null){
                idTEam=null;
            }else {
                idTEam = extras.getString("ID_TEAM");
            }
        }else{
           idTEam=(String) savedInstanceState.getSerializable("ID_TEAM");
        }
        eText.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myintent = getIntent();
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddCalendario.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        hora.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myintent = getIntent();
                final Calendar cldr = Calendar.getInstance();
                final int hour = cldr.get(Calendar.HOUR);
                final int minutes = cldr.get(Calendar.MINUTE);
                final String[] AMPM = {""};
                timepicker = new TimePickerDialog(AddCalendario.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                 AMPM[0] = (i < 12) ? "AM" : "PM";
                                hora.setText(i+":"+i1 +" " + AMPM[0]);
                            }
                        },hour,minutes,false
                );
                timepicker.show();
            }
        });
        btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                tvw.setText("Selected Date: "+ eText.getText());
                Equipo oponente = (Equipo)((Spinner) findViewById(R.id.oponente)).getSelectedItem();
                String location = ((String)localVisitante.getSelectedItem());
                Log.d("Location",location);
                Log.d("VERSUS",SetMatch(location,oponente.getNombre()));
                Log.d("TEST",idTEam);

               Calendario c = new Calendario();
               c.setTeamId(idTEam);
               c.setOponent(oponente);
               c.setHora(hora.getText().toString());
               c.setFecha(eText.getText().toString());
               c.setEstadio(estadio.getText().toString());
               c.setAwayHome(location);
                db.collection("Calendario")
                        .add(c)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Success", "DocumentSnapshot added with ID: " + documentReference.getId());


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            public void onFailure(@NonNull Exception e) {

                                Log.w("Error", "Error adding document", e);

                            }
                        });
                //Equipo local = (Equipo)((Spinner) findViewById(R.id.spinner3)).getSelectedItem();

            }
        });
        List<String> locvis =  Arrays.asList("Local","Visitante");
        localVisitante = (Spinner)findViewById(R.id.local_visitante);
        localVisitante.setAdapter(new ArrayAdapter(this,R.layout.spinner,locvis));
        oponente = (Spinner)findViewById(R.id.oponente);
       // local= (Spinner)findViewById(R.id.spinner3);
        db = FirebaseFirestore.getInstance();
        db.collection("Equipo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        equipos = new ArrayList<>();
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                Equipo e = document.toObject(Equipo.class);
                                e.setTeamId(document.getId());
                                equipos.add(e);
                            }
                            //ListView list = (ListView)findViewById(R.id.lv_teams);
                          //  EquiposAdapter adapter = new EquiposAdapter(getApplicationContext(),R.layout.item_team,equipos);
                          //  list.setAdapter(adapter);
                          /*  list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                                    Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                                    // long iD = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                                    Intent intent = new Intent(getBaseContext(),EquipoActivity.class);
                                    intent.putExtra("TEAM_ID",equipos.get(i).getTeamId());
                                    intent.putExtra("TEAM_NAME",equipos.get(i).getNombre());
                                    startActivity(intent);
                                    //Toast.makeText(getBaseContext(), equipos.get(i).getTeamId() + "", Toast.LENGTH_LONG).show();
                                }
                            });*/
                            oponente.setAdapter(new ArrayAdapter(getApplicationContext(),R.layout.spinner,equipos));
                            //local.setAdapter(new ArrayAdapter(getApplicationContext(),R.layout.spinner,equipos));


                        }
                        else{
                            Log.d("Main2Activity", "Error getting documents: ", task.getException());
                        }
                    }
                });
        db.document("/Equipo/"+idTEam).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Equipo equipo;
                        if(task.isSuccessful()){
                            equipo = task.getResult().toObject(Equipo.class);
                            equipo.setTeamId(idTEam);
                            myteam = equipo;
                            Log.d("TEst3",equipo.getNombre());
                        }
                    }
                });

    }

    private boolean IsHome(String location){
        if(location=="Local"){
            return true;
        }
        return false;
    }
    private String SetMatch(String location,String oponentName){

        if(IsHome(location)){
            return "VS "+oponentName;
        }
        return " @ "+oponentName;
    }
}
