package com.example.viniciomendez.anotador.Entities;

public class Pais {
    private String pais;
    private String Code;

    public Pais(String pais, String code) {
        this.pais = pais;
        Code = code;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @Override
    public java.lang.String toString() {
        return this.pais;
    }


}
