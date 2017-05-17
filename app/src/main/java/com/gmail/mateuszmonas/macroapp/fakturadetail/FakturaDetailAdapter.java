package com.gmail.mateuszmonas.macroapp.fakturadetail;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FakturaDetailAdapter extends RecyclerView.Adapter<FakturaDetailAdapter.ViewHolder> {

    private List<PozycjaFaktury> pozycjeFaktury = new ArrayList<>();

    public FakturaDetailAdapter(List<PozycjaFaktury> pozycjeFaktury) {
        setList(pozycjeFaktury);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pozycja_faktury_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position != 0) {
            PozycjaFaktury pozycjaFaktury = pozycjeFaktury.get(position);
            holder.lp.setText(String.format(Locale.getDefault(), "%d", pozycjaFaktury.getPozycja()));
            holder.nazwa.setText(pozycjaFaktury.getNazwa());
            holder.ilosc.setText(new DecimalFormat("#.##").format(pozycjaFaktury.getIlosc()));
            holder.jednostka.setText(pozycjaFaktury.getJednostka());
            holder.cenaNetto.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getCenaNetto()));
            holder.wartNetto.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getWartoscNetto()));
            holder.wartVAT.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getWartoscVat()));
            holder.wartBrutto.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getWartoscBrutto()));
        }
    }

    @Override
    public int getItemCount() {
        return pozycjeFaktury.size();
    }

    void replaceData(List<PozycjaFaktury> pozycjeFaktury) {
        setList(pozycjeFaktury);
        notifyDataSetChanged();
    }

    private void setList(List<PozycjaFaktury> pozycjeFaktury) {
        this.pozycjeFaktury.clear();
        this.pozycjeFaktury.add(new PozycjaFaktury());
        this.pozycjeFaktury.addAll(pozycjeFaktury);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        @BindView(R.id.lp)
        TextView lp;
        @BindView(R.id.nazwa)
        TextView nazwa;
        @BindView(R.id.ilosc)
        TextView ilosc;
        @BindView(R.id.jednostka)
        TextView jednostka;
        @BindView(R.id.cenaNetto)
        TextView cenaNetto;
        @BindView(R.id.wartNetto)
        TextView wartNetto;
        @BindView(R.id.wartVAT)
        TextView wartVAT;
        @BindView(R.id.wartBrutto)
        TextView wartBrutto;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }

}
