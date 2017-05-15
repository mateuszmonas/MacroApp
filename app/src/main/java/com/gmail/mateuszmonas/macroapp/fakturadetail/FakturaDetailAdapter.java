package com.gmail.mateuszmonas.macroapp.fakturadetail;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.PozycjaFaktury;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FakturaDetailAdapter extends RecyclerView.Adapter<FakturaDetailAdapter.ViewHolder> {

    private List<PozycjaFaktury> pozycjeFaktury;

    public FakturaDetailAdapter(List<PozycjaFaktury> pozycjeFaktury) {
        this.pozycjeFaktury = pozycjeFaktury;
        //add header
        this.pozycjeFaktury.add(0, new PozycjaFaktury());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pozycja_faktury_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == 0){
            PozycjaFaktury pozycjaFaktury = pozycjeFaktury.get(position);
            holder.lp.setText("Lp.");
            holder.nazwa.setText("Nazwa towaru lub usługi");
            holder.ilosc.setText("ilość");
            holder.jednostka.setText("JM");
            holder.cenaNetto.setText("C. nett");
            holder.wartNetto.setText("W. netto");
            holder.wartVAT.setText("VAT");
            holder.wartBrutto.setText("W. brutto");
        }else {
            PozycjaFaktury pozycjaFaktury = pozycjeFaktury.get(position);
            holder.lp.setText(String.format(Locale.getDefault(), "%d", pozycjaFaktury.getPozycja()));
            holder.nazwa.setText(pozycjaFaktury.getNazwa());
            holder.ilosc.setText(String.format(Locale.getDefault(), "%d", pozycjaFaktury.getIlosc()));
            holder.jednostka.setText(pozycjaFaktury.getJednostka());
            holder.cenaNetto.setText(new DecimalFormat("#.00").format(pozycjaFaktury.getCenaNetto()) + "PLN");
            holder.wartNetto.setText(new DecimalFormat("#.00").format(pozycjaFaktury.getWartoscNetto()) + "PLN");
            holder.wartVAT.setText(new DecimalFormat("#.00").format(pozycjaFaktury.getWartoscVat()) + "PLN");
            holder.wartBrutto.setText(new DecimalFormat("#.00").format(pozycjaFaktury.getWartoscBrutto()) + "PLN");
        }
    }

    @Override
    public int getItemCount() {
        return pozycjeFaktury.size();
    }

    void replaceData(List<PozycjaFaktury> pozycjeFaktury){
        setList(pozycjeFaktury);
        notifyDataSetChanged();
    }

    private void setList(List<PozycjaFaktury> pozycjeFaktury){
        this.pozycjeFaktury = pozycjeFaktury;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        @BindView(R.id.lp)TextView lp;
        @BindView(R.id.nazwa)TextView nazwa;
        @BindView(R.id.ilosc)TextView ilosc;
        @BindView(R.id.jednostka)TextView jednostka;
        @BindView(R.id.cenaNetto)TextView cenaNetto;
        @BindView(R.id.wartNetto)TextView wartNetto;
        @BindView(R.id.wartVAT)TextView wartVAT;
        @BindView(R.id.wartBrutto)TextView wartBrutto;
        public ViewHolder(View view) {
            super(view);
            this.view =  view;
            ButterKnife.bind(this, view);
        }
    }

}
