package com.gmail.mateuszmonas.macroapp.data;


import com.google.gson.annotations.SerializedName;

public class Faktura {

    /**
     * BRUTTO : 234.12
     * NETTO : 216.78
     * TZ : 2017-01-09
     * SYM : 16/SPR/01/17
     * NAZ : ŚLĄSKIE 3-Bożena Kwiatkowska
     * REFERENCE : faktuc1700000010
     * VAT : 17.34
     * _ : 0
     */

    private double BRUTTO;
    private double NETTO;
    private String TZ;
    private String SYM;
    @SerializedName("NAZ")
    private String HAN;
    private String REFERENCE;
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

    public String getTZ() {
        return TZ;
    }

    public void setTZ(String D) {
        this.TZ = D;
    }

    public String getSYM() {
        return SYM;
    }

    public void setSYM(String SYM) {
        this.SYM = SYM;
    }

    public String getHAN() {
        return HAN;
    }

    public void setHAN(String HAN) {
        this.HAN = HAN;
    }

    public String getREFERENCE() {
        return REFERENCE;
    }

    public void setREFERENCE(String REFERENCE) {
        this.REFERENCE = REFERENCE;
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
