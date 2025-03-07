package com.example.proiectandrada;

public class User {
    private String nume;
    private String Parola;

    public User(String nume, String parola) {
        this.nume = nume;
        Parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    public String getParola() {
        return Parola;
    }

    public void setParola(String parola) {
        Parola = parola;
    }

    @Override
    public String toString() {
        return "Bună, " + nume + "! Parola ta este: " + Parola;
    }

}