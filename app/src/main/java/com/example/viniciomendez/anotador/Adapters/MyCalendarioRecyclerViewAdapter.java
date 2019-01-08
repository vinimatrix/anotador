package com.example.viniciomendez.anotador.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viniciomendez.anotador.CalendarioFragment;
import com.example.viniciomendez.anotador.CalendarioFragment.OnListFragmentInteractionListener;
import com.example.viniciomendez.anotador.CalendarioFragment2;
import com.example.viniciomendez.anotador.Entities.Calendario;
import com.example.viniciomendez.anotador.GameDetailsActivity;
import com.example.viniciomendez.anotador.R;
import com.example.viniciomendez.anotador.dummy.DummyContent.DummyItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCalendarioRecyclerViewAdapter extends RecyclerView.Adapter<MyCalendarioRecyclerViewAdapter.MyViewHolder> {

    private final List<Calendario> calendarioList;
    private final CalendarioFragment.OnListFragmentInteractionListener mListener;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtDia,txtFechaMes,txtEquipos,hora,estadio;
        public Calendario mItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtDia = (TextView)itemView.findViewById(R.id.txt_dia);
            txtFechaMes = (TextView) itemView.findViewById(R.id.fecha_mes);
            txtEquipos = (TextView) itemView.findViewById(R.id.txt_equipos);
            hora = (TextView)itemView.findViewById(R.id.txt_hora);
            estadio= (TextView)itemView.findViewById(R.id.txt_estadio);
        }
    }
    public MyCalendarioRecyclerViewAdapter(Context context, List<Calendario> calendarioList, OnListFragmentInteractionListener listener){
        this.calendarioList = calendarioList;
        this.mListener = listener;
        this.mContext = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_calendario,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mItem =calendarioList.get(position);
        String daysArray[] = {"DOM","LUN","MAR", "MIE","JUE","VIE", "SAB"};
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        try {
            date = format.parse(calendarioList.get(position).getFecha().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        holder.hora.setText(calendarioList.get(position).getHora());
        holder.txtEquipos.setText(SetMatch(calendarioList.get(position).getAwayHome(),calendarioList.get(position).getOponent().getNombre()));
        holder.txtDia.setText(daysArray[(cal.get(Calendar.DAY_OF_WEEK)-1)]);
        holder.txtFechaMes.setText(String.format("%02d",cal.get(Calendar.DAY_OF_MONTH))+"/"+cal.get(Calendar.MONTH)+1);
        holder.estadio.setText(calendarioList.get(position).getEstadio());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null !=mListener){
                    mListener.onListFragmentInteraction(holder.mItem);
                    Log.d("CLICKEADO","CLIKEADO " +holder.mItem.getOponent().getNombre());
                    Intent intent = new Intent(mContext,GameDetailsActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return calendarioList.size();
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

