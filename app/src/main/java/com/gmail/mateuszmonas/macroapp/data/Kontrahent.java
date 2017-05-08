package com.gmail.mateuszmonas.macroapp.data;


import com.google.gson.annotations.SerializedName;

public class Kontrahent {

    /**
     * KOD : 00000
     * NIP :
     * NAZ : Kontrahent jednorazowy
     * _ : 0
     */

    private String KOD;
    private String NIP;
    private String NAZ;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
