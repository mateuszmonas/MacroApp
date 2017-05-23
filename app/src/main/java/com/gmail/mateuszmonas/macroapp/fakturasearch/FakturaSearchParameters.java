package com.gmail.mateuszmonas.macroapp.fakturasearch;

import android.os.Parcel;
import android.os.Parcelable;

public class FakturaSearchParameters implements Parcelable {
    public static final Parcelable.Creator<FakturaSearchParameters> CREATOR = new Parcelable.Creator<FakturaSearchParameters>() {
        @Override
        public FakturaSearchParameters createFromParcel(Parcel source) {
            return new FakturaSearchParameters(source);
        }

        @Override
        public FakturaSearchParameters[] newArray(int size) {
            return new FakturaSearchParameters[size];
        }
    };
    private String symbol;
    private String dateMin;
    private String dateMax;
    private String cenaMin;
    private String cenaMax;

    FakturaSearchParameters(String symbol, String dateMin, String dateMax, String cenaMin, String cenaMax) {
        this.symbol = symbol;
        this.dateMin = dateMin;
        this.dateMax = dateMax;
        this.cenaMin = !cenaMin.isEmpty() ? cenaMin : "0";
        this.cenaMax = !cenaMax.isEmpty() ? cenaMax : "2147483647";
    }

    public FakturaSearchParameters() {
        this.symbol = "";
        this.dateMin = "19000101";
        this.dateMax = "20991231";
        this.cenaMin = "0";
        this.cenaMax = "2147483647";
    }

    private FakturaSearchParameters(Parcel in) {
        this.symbol = in.readString();
        this.dateMin = in.readString();
        this.dateMax = in.readString();
        this.cenaMin = in.readString();
        this.cenaMax = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.symbol);
        dest.writeString(this.dateMin);
        dest.writeString(this.dateMax);
        dest.writeString(this.cenaMin);
        dest.writeString(this.cenaMax);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDateMin() {
        return dateMin;
    }

    public String getDateMax() {
        return dateMax;
    }

    public String getCenaMin() {
        return cenaMin;
    }

    public String getCenaMax() {
        return cenaMax;
    }
}
