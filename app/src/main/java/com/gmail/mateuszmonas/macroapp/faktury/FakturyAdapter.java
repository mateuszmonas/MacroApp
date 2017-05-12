package com.gmail.mateuszmonas.macroapp.faktury;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FakturyAdapter extends RecyclerView.Adapter<FakturyAdapter.ViewHolder>{

    private List<Faktura> faktury;
    private FakturyFragment.FakturyListListener listener;

    public FakturyAdapter(List<Faktura> faktury, FakturyFragment.FakturyListListener listener) {
        this.faktury = faktury;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faktury_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Faktura faktura = faktury.get(position);
        holder.data.setText(faktura.getTZ());
        holder.nabywca.setText(faktura.getNAZ());
        holder.kwota.setText(String.format(Locale.getDefault(), "%f", faktura.getBRUTTO()));
        holder.handlowiec.setText(faktura.getHAN());
        holder.item = faktura;

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFakturaClick();
            }
        });
    }

    void replaceData(List<Faktura> faktury){
        setList(faktury);
        notifyDataSetChanged();
    }

    private void setList(List<Faktura> faktury){
        this.faktury = faktury;
    }

    @Override
    public int getItemCount() {
        return faktury.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final View view;
        Faktura item;
        @BindView(R.id.nabywca)
        TextView nabywca;
        @BindView(R.id.handlowiec)
        TextView handlowiec;
        @BindView(R.id.data)
        TextView data;
        @BindView(R.id.kwota)
        TextView kwota;

        ViewHolder(View view){
            super(view);
            this.view =  view;
            ButterKnife.bind(this, view);
        }
    }
}
