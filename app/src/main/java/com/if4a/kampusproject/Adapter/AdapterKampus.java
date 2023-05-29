package com.if4a.kampusproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if4a.kampusproject.Model.ModelKampus;
import com.if4a.kampusproject.R;

import java.util.List;

public class AdapterKampus extends RecyclerView.Adapter<AdapterKampus.VHKampus> {
    private Context ctx;
    private List<ModelKampus> listKampus;


    public AdapterKampus(Context ctx, List<ModelKampus> listKampus) {
        this.ctx = ctx;
        this.listKampus = listKampus;
    }

    @NonNull
    @Override
    public AdapterKampus.VHKampus onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kampus, parent, false);
        return new VHKampus(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKampus.VHKampus holder, int position) {
        ModelKampus MK = listKampus.get(position);
        holder.tvId.setText( MK.getId());
        holder.tvNama.setText((position+1) + MK.getNama() + " (" + MK.getAkreditasi() + ")" );
        holder.tvAkreditasi.setText(MK.getAkreditasi());
        holder.tvMotto.setText(MK.getMotto());
        holder.tvAlamat.setText(MK.getDeskripsi_kampus());
        holder.tvDeskripsi.setText(MK.getDeskripsi_kampus());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VHKampus extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvAkreditasi, tvMotto, tvAlamat, tvDeskripsi;
        public VHKampus(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAkreditasi = itemView.findViewById(R.id.tv_akreditasi);
            tvMotto = itemView.findViewById(R.id.tv_motto);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}
