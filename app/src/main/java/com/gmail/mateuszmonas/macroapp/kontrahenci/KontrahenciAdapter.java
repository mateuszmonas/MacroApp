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
    private KontrahenciFragment.KontrahenciListListener listener;

    public KontrahenciAdapter(List<Kontrahent> kontrahenci, KontrahenciFragment.KontrahenciListListener listener) {
        this.kontrahenci = kontrahenci;
        this.listener = listener;
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
        holder.numer.setText(Integer.toString(kontrahent.getNumer()));
        holder.nip.setText(Integer.toString(kontrahent.getNIP()));
        holder.adres.setText(kontrahent.getAdres());
        holder.skrot.setText(kontrahent.getSkrot());

        final int id = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onKontrachenClick(id);
                }
            }
        });
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
        @BindView(R.id.numer)TextView numer;
        @BindView(R.id.nip)TextView nip;
        @BindView(R.id.adres)TextView adres;
        @BindView(R.id.skrot)TextView skrot;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
