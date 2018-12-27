package com.example.viniciomendez.anotador.Entities;

import java.security.PublicKey;

public class Equipo {
    private String nombre;
    private String nombreCorto;
    private String liga;
    private String pais;
    private String ciudad;
    private String division;
    private String tempotada;
    private String teamId;
    private int Id;

    public Equipo(){}
    public Equipo(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getTempotada() {
        return tempotada;
    }

    public void setTempotada(String tempotada) {
        this.tempotada = tempotada;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }




}
