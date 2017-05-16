package com.gmail.mateuszmonas.macroapp.data;


import com.google.gson.annotations.SerializedName;

public class DetaleFaktury {
    /**
     * BRUTTO : 5343.34
     * MIASTO : Piaseczno
     * NETTO : 5194.75
     * NIP : 123-000-99-04
     * NAZ : Stanmed Włodzimierz Stańczyk
     * UL : Kajki 18
     * KPOCZ : 05-500
     * VAT : 148.59
     * _ : 0
     */

    @SerializedName("BRUTTO")
    private double brutto;
    @SerializedName("MIASTO")
    private String miasto;
    @SerializedName("NETTO")
    private double netto;
    @SerializedName("NIP")
    private String nip;
    @SerializedName("NAZ")
    private String nazwa;
    @SerializedName("UL")
    private String ulica;
    @SerializedName("KPOCZ")
    private String kodPocztowy;
    @SerializedName("VAT")
    private double vat;
    @SerializedName("_")
    private int id;

    public double getBrutto() {
        return brutto;
    }

    public void setBrutto(double brutto) {
        this.brutto = brutto;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
