package com.gmail.mateuszmonas.macroapp.faktury;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Faktura;

import java.text.DecimalFormat;
import java.util.List;

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
        final Faktura faktura = faktury.get(position);
        holder.data.setText(faktura.getTZ());
        holder.symbol.setText(faktura.getSYM());
        holder.kwota.setText(new DecimalFormat("#.00").format(faktura.getBRUTTO()));
        holder.handlowiec.setText(faktura.getHAN());
        holder.item = faktura;

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFakturaClick(faktura.getREFERENCE());
            }
        });
    }

    void replaceData(List<Faktura> faktury, boolean forceUpdate){
        setList(faktury, forceUpdate);
        notifyDataSetChanged();
    }

    private void setList(List<Faktura> faktury, boolean forceUpdate){
        if(forceUpdate) {
            this.faktury = faktury;
        } else {
            this.faktury.addAll(faktury);
        }
    }

    @Override
    public int getItemCount() {
        if(faktury==null) return 0;
        return faktury.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final View view;
        Faktura item;
        @BindView(R.id.symbol)
        TextView symbol;
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
