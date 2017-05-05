package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KontrahenciAdapter  extends RecyclerView.Adapter<KontrahenciAdapter.ViewHolder> {

    private List<Kontrahent> kontrahenci;

    public KontrahenciAdapter(List<Kontrahent> kontrahenci) {
        this.kontrahenci = kontrahenci;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kontrahenci_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Kontrahent kontrahent = kontrahenci.get(position);
        holder.item = kontrahent;
        holder.imie.setText(kontrahent.getImie());
        holder.nazwisko.setText(kontrahent.getNazwisko());
    }

    public void replaceData(List<Kontrahent> kontrahenci){
        setList(kontrahenci);
        notifyDataSetChanged();
    }

    private void setList(List<Kontrahent> kontrahenci){
        this.kontrahenci = kontrahenci;
    }

    @Override
    public int getItemCount() {
        return kontrahenci.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        Kontrahent item;
        @BindView(R.id.imie)TextView imie;
        @BindView(R.id.nazwisko)TextView nazwisko;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
