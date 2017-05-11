package com.gmail.mateuszmonas.macroapp.data.remote;


import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.List;

public class ServerResponseFaktury {

    private List<Faktura> faktury;

    public List<Faktura> getFaktury() {
        return faktury;
    }

    public void setFaktury(List<Faktura> faktury) {
        this.faktury = faktury;
    }
}
