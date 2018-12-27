package com.example.viniciomendez.anotador.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.viniciomendez.anotador.Entities.Equipo;
import com.example.viniciomendez.anotador.R;

import java.util.List;

public class EquiposAdapter extends ArrayAdapter<Equipo>{
private  int resourceLayout;
private  Context mContext;
    public EquiposAdapter(@NonNull Context context, int resource, @NonNull List<Equipo> equipos) {
        super(context, resource, equipos);
        this.resourceLayout = resource;
        this.mContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout,null);
        }
        Equipo p = getItem(position);
        if(p!= null){

            TextView tt1 = (TextView)v.findViewById(R.id.team_name);
            TextView tt2 = (TextView)v.findViewById(R.id.team_league);
            TextView tt3 = (TextView)v.findViewById(R.id.txt_admin);
            if(tt1!= null) {
                tt1.setText(p.getNombre());
            }
            if(tt2!= null) {
                tt2.setText(p.getLiga());
            }
            if(tt3!= null) {
                tt3.setText("Admin");
            }
        }

        return v;
    }

}

