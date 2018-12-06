package com.example.viniciomendez.anotador;

public class Equipo {
    private String Nombre;
    private String NombreCorto;
    private String Liga;
    private String Pais;
    private String Ciudad;
    private String Division;
    private String Tempotada;
    private String TeamId;
    private int Id;
    public Equipo(String nombre){
        this.setNombre(nombre);
    }
    public String getNombre(){
        return this.Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombreCorto() {
        return NombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        NombreCorto = nombreCorto;
    }

    public String getLiga() {
        return Liga;
    }

    public void setLiga(String liga) {
        Liga = liga;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public String getTempotada() {
        return Tempotada;
    }

    public void setTempotada(String tempotada) {
        Tempotada = tempotada;
    }

    public String getTeamId() {
        return TeamId;
    }

    public void setTeamId(String teamId) {
        TeamId = teamId;
    }
}
