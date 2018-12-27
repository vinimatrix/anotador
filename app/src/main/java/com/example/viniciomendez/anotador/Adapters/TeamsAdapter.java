package com.example.viniciomendez.anotador.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viniciomendez.anotador.Entities.Equipo;
import com.example.viniciomendez.anotador.R;

import java.util.List;

public class TeamsAdapter extends
        RecyclerView.Adapter<TeamsAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View teamsView = inflater.inflate(R.layout.item_team,parent,false);
        ViewHolder viewHolder = new ViewHolder(teamsView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsAdapter.ViewHolder holder, int position) {
        final Equipo equipo = mTeams.get(position);

        TextView teamName = holder.TeamName;
        teamName.setText(equipo.getNombre());
        TextView leagueName = holder.LeagueName;
        leagueName.setText(equipo.getLiga());
        TextView adminText = holder.AdminButton;
        adminText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(,"Equipo a Administrar"+equipo.getNombre(),Toast.LENGTH_LONG);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mTeams.size();
    }
    private List<Equipo> mTeams;

    // Pass in the contact array into the constructor
    public TeamsAdapter(List<Equipo> teams) {
        mTeams = teams;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TeamName;
        public TextView LeagueName;
        public TextView AdminButton;
        public ViewHolder(View itemView) {
            super(itemView);
            TeamName = (TextView)itemView.findViewById(R.id.team_name);
            LeagueName = (TextView)itemView.findViewById(R.id.team_league);
            AdminButton = (TextView)itemView.findViewById(R.id.txt_admin);
        }
    }
}
