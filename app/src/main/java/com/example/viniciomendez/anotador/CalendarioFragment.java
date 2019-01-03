package com.example.viniciomendez.anotador;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.viniciomendez.anotador.Adapters.EquiposAdapter;
import com.example.viniciomendez.anotador.Entities.Calendario;
import com.example.viniciomendez.anotador.Entities.Equipo;
import com.example.viniciomendez.anotador.dummy.DummyContent;
import com.example.viniciomendez.anotador.dummy.DummyContent.DummyItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CalendarioFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private String idTeam;
    FloatingActionButton fab;
    private FirebaseFirestore db;
    List<Calendario> calendarios = new ArrayList<>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CalendarioFragment() {

    }



    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CalendarioFragment newInstance(String idTeam,int columnCount) {
        CalendarioFragment fragment = new CalendarioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putCharSequence("idTeam",idTeam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            this.idTeam = getArguments().getString("teamId");
            Log.d("BUMDLE",getArguments().getString("teamId"));
        }
        Log.d("TEAMID",idTeam);
        db = FirebaseFirestore.getInstance();
        db.collection("Calendarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        final List<Calendario> calendarios = new ArrayList<>();
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document:task.getResult()){
                                Calendario c = document.toObject(Calendario.class);
                                c.setTeamId(getArguments().getString("teamId"));
                                calendarios.add(c);
                            }


                        }
                        else{
                            Log.d("Main2Activity", "Error getting documents: ", task.getException());
                        }
                    }
                });
        /*Calendario c = new Calendario("MqL8ETiqkboMeBHdpaUp");
        Calendario c1 = new Calendario("SnzM3auFCZZAJCQrXVMn\n");
        this.calendarios.add(c);
        this.calendarios.add(c1);*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendario_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyCalendarioRecyclerViewAdapter(calendarios));
            TextView title = (TextView)view.findViewById(R.id.tv_titulo);
            title.setText(idTeam);
        }
        fab = (FloatingActionButton) view.findViewById(R.id.btn_add_calendario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AddCalendario.class);
                intent.putExtra("ID_TEAM",idTeam);
                Log.d("TeamID",idTeam);

                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
           /* throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Calendario item);
    }
}
