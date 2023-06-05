package com.if4a.kampusproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if4a.kampusproject.API.APIRequestData;
import com.if4a.kampusproject.API.RetroServer;
import com.if4a.kampusproject.Activity.MainActivity;
import com.if4a.kampusproject.Activity.UbahActivity;
import com.if4a.kampusproject.Model.ModelKampus;
import com.if4a.kampusproject.Model.ModelResponse;
import com.if4a.kampusproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.tvNama.setText((position+1) + ". "+ MK.getNama());
        holder.tvAkreditasi.setText("Akreditasi : "+MK.getAkreditasi());
        holder.tvMotto.setText("Motto : " +MK.getMotto());
        holder.tvAlamat.setText(MK.getAlamat());
        holder.tvDeskripsi.setText(MK.getDeskripsi_kampus());
    }

    @Override
    public int getItemCount() {
        return listKampus.size();
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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian!");
                    pesan.setMessage("Operasi apa yang dilakukan?");
                    pesan.setCancelable(true);

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);
                            pindah.putExtra("xId", tvId.getText().toString());
                            pindah.putExtra("xNama", tvNama.getText().toString());
                            pindah.putExtra("xAkreditasi", tvAkreditasi.getText().toString());
                            pindah.putExtra("xMotto", tvMotto.getText().toString());
                            pindah.putExtra("xAlamat", tvAlamat.getText().toString());
                            ctx.startActivity(pindah);
                        }
                    });
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusKampus(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });
                    pesan.show();
                    return false;
                }
            });
        }
        private void hapusKampus(String idKampus){
            APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardDelete(idKampus);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode: "+ kode + "Pesan: "+ pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveKampus();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
