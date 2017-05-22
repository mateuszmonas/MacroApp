package com.gmail.mateuszmonas.macroapp.fakturadetail;


import android.support.annotation.Nullable;
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

class FakturaDetailAdapter extends RecyclerView.Adapter<FakturaDetailAdapter.ViewHolder> {

    private final List<PozycjaFaktury> pozycjeFaktury = new ArrayList<>();

    FakturaDetailAdapter(List<PozycjaFaktury> pozycjeFaktury) {
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
            if (holder.lp != null && holder.jednostka != null && holder.cenaNetto != null) {
                holder.lp.setText(String.format(Locale.getDefault(), "%d", pozycjaFaktury.getPozycja()));
                holder.jednostka.setText(pozycjaFaktury.getJednostka());
                holder.cenaNetto.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getCenaNetto()));
            }
            holder.nazwa.setText(pozycjaFaktury.getNazwa());
            holder.ilosc.setText(new DecimalFormat("#.##").format(pozycjaFaktury.getIlosc()));
            holder.wartNetto.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getWartoscNetto()));
            holder.wartVAT.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getWartoscVat()));
            holder.wartBrutto.setText(new DecimalFormat("0.00").format(pozycjaFaktury.getWartoscBrutto()));
        }
        //set the header row
        else {
            if (holder.lp != null && holder.jednostka != null && holder.cenaNetto != null) {
                holder.lp.setText("lp");
                holder.jednostka.setText("JM");
                holder.cenaNetto.setText("C. net.");
            }
            holder.nazwa.setText("nazwa");
            holder.ilosc.setText("#");
            holder.wartNetto.setText("W. net.");
            holder.wartVAT.setText("VAT");
            holder.wartBrutto.setText("W. brut.");
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
        //add header row to the list
        this.pozycjeFaktury.add(new PozycjaFaktury());
        this.pozycjeFaktury.addAll(pozycjeFaktury);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        @BindView(R.id.lp)
        @Nullable
        TextView lp;
        @BindView(R.id.nazwa)
        TextView nazwa;
        @BindView(R.id.ilosc)
        TextView ilosc;
        @BindView(R.id.jednostka)
        @Nullable
        TextView jednostka;
        @BindView(R.id.cenaNetto)
        @Nullable
        TextView cenaNetto;
        @BindView(R.id.wartNetto)
        TextView wartNetto;
        @BindView(R.id.wartVAT)
        TextView wartVAT;
        @BindView(R.id.wartBrutto)
        TextView wartBrutto;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }

}
