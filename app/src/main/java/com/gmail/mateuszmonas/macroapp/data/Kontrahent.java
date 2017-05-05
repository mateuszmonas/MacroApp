package com.gmail.mateuszmonas.macroapp.data;


public class Kontrahent {
    private String imie;
    private String nazwisko;

    public Kontrahent() {}

    public Kontrahent(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
