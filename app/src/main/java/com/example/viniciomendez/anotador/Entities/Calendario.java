package com.example.viniciomendez.anotador.Entities;

import java.util.Date;

public class Calendario {
    private String teamId;  //id del equipo dueño del dato
    private String estadio; //estadio donde se va a jugar
    private String fecha;  //fecha del juego
    private boolean passed; // si el juego ya paso
    private Equipo homeTeam; //equipo local
    private Equipo awayTeam; //equipo visitante
    private String winLoose; //si el equipo gano o perdio
    private String hora;   // hora del juego
    private String Notas;  //Nota del juego

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Equipo getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Equipo homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Equipo getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Equipo awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getWinLoose() {
        return winLoose;
    }

    public void setWinLoose(String winLoose) {
        this.winLoose = winLoose;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String notas) {
        Notas = notas;
    }

    public String getTipoJuego() {
        return TipoJuego;
    }

    public void setTipoJuego(String tipoJuego) {
        TipoJuego = tipoJuego;
    }

    private String TipoJuego; // posttemporada,exibicion,temporada,final

    public Calendario(String teamId) {
        this.teamId = teamId;
    }

    public Calendario() {
    }
}
