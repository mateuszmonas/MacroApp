package com.gmail.mateuszmonas.macroapp.kontrahenci;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.mateuszmonas.macroapp.R;
import com.gmail.mateuszmonas.macroapp.data.Kontrahent;
import com.gmail.mateuszmonas.macroapp.utils.ActivityUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KontrahenciAdapter extends RecyclerView.Adapter<KontrahenciAdapter.ViewHolder> {

    private List<Kontrahent> kontrahenci;
    private KontrahenciFragment.KontrahenciListListener listener;

    public KontrahenciAdapter(List<Kontrahent> kontrahenci, KontrahenciFragment.KontrahenciListListener listener) {
        this.kontrahenci = kontrahenci;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kontrahenci_item, parent, false);
        view.getLayoutParams().height = ActivityUtils.getScreenWidth(parent.getContext()) / 3;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Kontrahent kontrahent = kontrahenci.get(position);
        holder.item = kontrahent;

        holder.nip.setText(kontrahent.getNIP().isEmpty() ? "-------------" : kontrahent.getNIP());
        holder.kod.setText(kontrahent.getKOD());
        holder.skrot.setText(kontrahent.getNAZ());
        holder.view.setBackgroundColor(Color.parseColor(kontrahent.getKOLOR()));

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

    Kontrahent getKontrahent(int id) {
        return kontrahenci.get(id);
    }

    void replaceData(List<Kontrahent> kontrahenci, boolean forceUpdate) {
        setList(kontrahenci, forceUpdate);
        notifyDataSetChanged();
    }

    private void setList(List<Kontrahent> kontrahenci, boolean forceUpdate) {
        if (forceUpdate) {
            this.kontrahenci = kontrahenci;
        } else {
            this.kontrahenci.addAll(kontrahenci);
        }
    }

    @Override
    public int getItemCount() {
        return kontrahenci.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        Kontrahent item;
        @BindView(R.id.kod)
        TextView kod;
        @BindView(R.id.nip)
        TextView nip;
        @BindView(R.id.skrot)
        TextView skrot;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
