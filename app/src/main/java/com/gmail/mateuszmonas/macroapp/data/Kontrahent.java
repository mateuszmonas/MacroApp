package com.gmail.mateuszmonas.macroapp.data;


import com.google.gson.annotations.SerializedName;

public class Kontrahent {

    /**
     * KOD : 00000
     * NIP :
     * NAZ : Kontrahent jednorazowy
     * REFERENCE : kontr   00000001
     * KOLOR : 245:100:12
     * _ : 0
     */

    private String KOD;
    private String NIP;
    private String NAZ;
    private String REFERENCE;
    private String KOLOR;
    @SerializedName("_")
    private int id;

    public String getKOD() {
        return KOD;
    }

    public void setKOD(String KOD) {
        this.KOD = KOD;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getNAZ() {
        return NAZ;
    }

    public void setNAZ(String NAZ) {
        this.NAZ = NAZ;
    }

    public String getREFERENCE() {
        return REFERENCE;
    }

    public void setREFERENCE(String REFERENCE) {
        this.REFERENCE = REFERENCE;
    }

    public String getKOLOR() {
        String color = "#ffa05b";
        if(!KOLOR.equals("")) {
            String[] parseKolor = KOLOR.split(":");
            String r = Integer.toHexString(Integer.valueOf(parseKolor[0]));
            if (r.length() < 2) r = "0" + r;
            String g = Integer.toHexString(Integer.valueOf(parseKolor[1]));
            if (g.length() < 2) g = "0" + g;
            String b = Integer.toHexString(Integer.valueOf(parseKolor[2]));
            if (b.length() < 2) b = "0" + b;
            color = "#" + r + g + b;
        }

        return color;
    }

    public void setKOLOR(String KOLOR) {
        this.KOLOR = KOLOR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}