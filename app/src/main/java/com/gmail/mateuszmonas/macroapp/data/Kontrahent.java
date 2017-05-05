package com.gmail.mateuszmonas.macroapp.data;


public class Kontrahent {
    int numer;
    String skrot;
    int NIP;
    String nazwa;
    String adres;

    public Kontrahent() {}

    public Kontrahent(int numer, String skrot, int NIP, String nazwa, String adres) {
        this.numer = numer;
        this.skrot = skrot;
        this.NIP = NIP;
        this.nazwa = nazwa;
        this.adres = adres;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getSkrot() {
        return skrot;
    }

    public void setSkrot(String skrot) {
        this.skrot = skrot;
    }

    public int getNIP() {
        return NIP;
    }

    public void setNIP(int NIP) {
        this.NIP = NIP;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
