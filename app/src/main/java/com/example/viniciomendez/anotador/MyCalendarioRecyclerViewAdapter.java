package com.example.viniciomendez.anotador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viniciomendez.anotador.CalendarioFragment.OnListFragmentInteractionListener;
import com.example.viniciomendez.anotador.Entities.Calendario;
import com.example.viniciomendez.anotador.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCalendarioRecyclerViewAdapter extends RecyclerView.Adapter<MyCalendarioRecyclerViewAdapter.MyViewHolder> {

    private List<Calendario> calendarioList;


    public class MyViewHolder extends RecyclerView.ViewHolder{ public TextView txtDia,txtFechaMes,txtEquipos,hora,estadio;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtDia = (TextView)itemView.findViewById(R.id.txt_dia);
            txtFechaMes = (TextView) itemView.findViewById(R.id.fecha_mes);
            txtEquipos = (TextView) itemView.findViewById(R.id.txt_equipos);
            hora = (TextView)itemView.findViewById(R.id.txt_hora);
            estadio= (TextView)itemView.findViewById(R.id.txt_estadio);
        }
    }
    public MyCalendarioRecyclerViewAdapter(List<Calendario> calendarioList){
        this.calendarioList = calendarioList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_calendario,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Calendario calendario =calendarioList.get(position);
        holder.hora.setText(calendario.getHora());
        holder.txtEquipos.setText(calendario.getAwayTeam()+" @ "+calendario.getHomeTeam());
        holder.txtDia.setText(calendario.getFecha());
        holder.estadio.setText(calendario.getEstadio());
    }


    @Override
    public int getItemCount() {
        return calendarioList.size();
    }
}

