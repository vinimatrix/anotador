package com.example.viniciomendez.anotador.Entities;

import java.util.Date;

public class Calendario {
    private String teamId;  //id del equipo dueño del dato
    private String estadio; //estadio donde se va a jugar
    private String fecha;  //fecha del juego
    private boolean passed; // si el juego ya paso
    private Equipo oponent;
    private String winLoose; //si el equipo gano o perdio
    private String hora;   // hora del juego
    private String Notas;  //Nota del juego
    private String awayHome;

    public String getCalendarioId() {
        return calendarioId;
    }

    public void setCalendarioId(String calendarioId) {
        this.calendarioId = calendarioId;
    }

    private String calendarioId;
    public String getAwayHome() {
        return awayHome;
    }

    public void setAwayHome(String awayHome) {
        this.awayHome = awayHome;
    }

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

    public Equipo getOponent() {
        return oponent;
    }

    public void setOponent(Equipo oponent) {
        this.oponent = oponent;
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
