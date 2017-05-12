package com.gmail.mateuszmonas.macroapp.data;


import com.google.gson.annotations.SerializedName;

public class Faktura {

    /**
     * BRUTTO : 60.83
     * NETTO : 0
     * HAN : DOK
     * NAZ : ***
     * TZ : 2003-07-20
     * UL :
     * VAT : 60.83
     * _ : 0
     */

    private double BRUTTO;
    private double NETTO;
    private String HAN;
    private String NAZ;
    private String TZ;
    private String UL;
    private double VAT;
    @SerializedName("_")
    private int id;

    public double getBRUTTO() {
        return BRUTTO;
    }

    public void setBRUTTO(double BRUTTO) {
        this.BRUTTO = BRUTTO;
    }

    public double getNETTO() {
        return NETTO;
    }

    public void setNETTO(double NETTO) {
        this.NETTO = NETTO;
    }

    public String getHAN() {
        return HAN;
    }

    public void setHAN(String HAN) {
        this.HAN = HAN;
    }

    public String getNAZ() {
        return NAZ;
    }

    public void setNAZ(String NAZ) {
        this.NAZ = NAZ;
    }

    public String getTZ() {
        return TZ;
    }

    public void setTZ(String TZ) {
        this.TZ = TZ;
    }

    public String getUL() {
        return UL;
    }

    public void setUL(String UL) {
        this.UL = UL;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
