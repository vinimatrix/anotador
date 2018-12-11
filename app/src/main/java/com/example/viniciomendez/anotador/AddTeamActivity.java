package com.example.viniciomendez.anotador;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class AddTeamActivity extends AppCompatActivity {

    private Spinner userSpinner;
    private EditText ciudad;
    private EditText NombreEquipo;
    private EditText NombreCorto;
    private AutoCompleteTextView liga;
    ArrayAdapter<String> ligaAdapter;
    private Button btnAccept;
    private Equipo equipo;
    private String _pais;
    private List<Pais> users;
   // private DatabaseReference mDatabase;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        users = new ArrayList<Pais>();
        users.add(new Pais("Seleccione el pais","none"));
        users.add(new Pais("Republica Dominicana","DOM"));
        users.add(new Pais("Estados Unidos","USA"));
        users.add(new Pais("Venezuela","VEN"));
        users.add(new Pais("Colombia","COL"));
        ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, users);

        userSpinner = (Spinner) findViewById(R.id.spinner2);
        ciudad = (EditText)findViewById(R.id.team_ciudad);
        NombreEquipo = (EditText) findViewById(R.id.team_nombre);
        NombreCorto = (EditText) findViewById(R.id.team_short_name);
        userSpinner.setPrompt("Seleccione el Pais");
        db = FirebaseFirestore.getInstance();
        userSpinner.setAdapter(userAdapter);
        userSpinner.setSelection(0);

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Pais user = (Pais) parent.getSelectedItem();
                displayUserData(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        userSpinner.setFocusable(true);
// And to get the actual User object that was selected, you can do this.
        final Pais user = (Pais) ( (Spinner) findViewById(R.id.spinner2) ).getSelectedItem();

        ////////////////////////////////////////////////////////
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final String[] ligas = new String[]{"Verano " + year,"Primavera " + year,"Otoño " + year,"Invierno " + year+" - "+(year+1)};
        ligaAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ligas);
        liga = (AutoCompleteTextView)findViewById(R.id.liga);
        liga.setThreshold(0);
        liga.setAdapter(ligaAdapter);
        liga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liga.showDropDown();
            }
        });
        liga.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (ligas.length > 0) {
                    // show all suggestions
                    if (!liga.getText().toString().equals(""))
                        ligaAdapter.getFilter().filter(null);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    liga.setInputType(InputType.TYPE_NULL);
                    liga.showDropDown();
                }
                return false;
            }
        });
        ////////////////////////////////////////////////////////
      btnAccept = (Button)findViewById(R.id.button);
      btnAccept.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              equipo = new Equipo(NombreEquipo.getText().toString());
              equipo.setPais(_pais);
              equipo.setLiga(liga.getText().toString());
              equipo.setNombreCorto(NombreCorto.getText().toString());
              equipo.setCiudad(ciudad.getText().toString());
              equipo.setTempotada((String.valueOf(Calendar.getInstance().get(calendar.YEAR))));
             /* // Write a message to the database

// ...        FirebaseApp.initializeApp(getBaseContext());
              // Write a message to the database
              FirebaseDatabase database = FirebaseDatabase.getInstance();
              DatabaseReference myref = database.getReference("anotador-5ff36");
*/
              db.collection("Equipo")
                      .add(equipo)
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

            /*  Map<String, Equipo> users = new HashMap<>();
              users.put("equipos", equipo);
             // myref.setValue(users);*/

          }

      });
    }
    public void getSelectedUser(View v) {
        Pais user = (Pais) userSpinner.getSelectedItem();
        displayUserData(user);
    }
    private void displayUserData(Pais user) {
        String pais = user.getPais();
       // int age = user.getAge();
        String code = user.getCode();

        String userData = "Pais: " + pais + "\ncode: " + code;
        _pais = pais;
        Toast.makeText(this, userData, Toast.LENGTH_LONG).show();
    }
}
