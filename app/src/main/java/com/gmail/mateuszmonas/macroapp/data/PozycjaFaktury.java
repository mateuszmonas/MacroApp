package com.gmail.mateuszmonas.macroapp.data;


import com.google.gson.annotations.SerializedName;

public class PozycjaFaktury {

    /**
     * IL : 2
     * NAZ : sztuka
     * WN : 131.78
     * NX : Zestaw do kaniulacji dużych naczyń III-kanałowy 7F/15
     * POZ : 1
     * CN : 65.89
     * WB : 142.32
     * WV : 10.54
     * _ : 0
     */

    @SerializedName("IL")
    private int ilosc;
    @SerializedName("NAZ")
    private String jednostka;
    @SerializedName("WN")
    private double wartoscNetto;
    @SerializedName("NX")
    private String nazwa;
    @SerializedName("POZ")
    private int pozycja;
    @SerializedName("CN")
    private double cenaNetto;
    @SerializedName("WB")
    private double wartoscBrutto;
    @SerializedName("WV")
    private double wartoscVat;
    @SerializedName("_")
    private int id;

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public double getWartoscNetto() {
        return wartoscNetto;
    }

    public void setWartoscNetto(double wartoscNetto) {
        this.wartoscNetto = wartoscNetto;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPozycja() {
        return pozycja;
    }

    public void setPozycja(int pozycja) {
        this.pozycja = pozycja;
    }

    public double getCenaNetto() {
        return cenaNetto;
    }

    public void setCenaNetto(double cenaNetto) {
        this.cenaNetto = cenaNetto;
    }

    public double getWartoscBrutto() {
        return wartoscBrutto;
    }

    public void setWartoscBrutto(double wartoscBrutto) {
        this.wartoscBrutto = wartoscBrutto;
    }

    public double getWartoscVat() {
        return wartoscVat;
    }

    public void setWartoscVat(double wartoscVat) {
        this.wartoscVat = wartoscVat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
