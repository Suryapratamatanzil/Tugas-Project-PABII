package com.if4a.kampusproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if4a.kampusproject.API.APIRequestData;
import com.if4a.kampusproject.API.RetroServer;
import com.if4a.kampusproject.Model.ModelResponse;
import com.if4a.kampusproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etAkreditasi, etMotto, etAlamat, etDeskripsiKampus;
    private Button btntambah, btn_kembali;
    private String nama, akreditasi, motto, alamat, deskripsiKampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAkreditasi = findViewById(R.id.et_akreditasi);
        etMotto = findViewById(R.id.et_motto);
        etAlamat = findViewById(R.id.et_alamat);
        etDeskripsiKampus = findViewById(R.id.et_deskripsi_kampus);
        btntambah = findViewById(R.id.btn_tambah);
        btn_kembali = findViewById(R.id.btn_kembali);

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TambahActivity.this, MainActivity.class));
                finish();
            }
        });
        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                akreditasi = etAkreditasi.getText().toString();
                motto = etMotto.getText().toString();
                alamat = etAlamat.getText().toString();
                deskripsiKampus = etDeskripsiKampus.getText().toString();

                if (nama.trim().equals("")) {
                    etNama.setError("nama harus diisi!");
                }
                if (akreditasi.trim().equals("")) {
                    etAkreditasi.setError("akreditasi harus diisi!");
                }
                if (motto.trim().equals("")) {
                    etMotto.setError("asal harus diisi!");
                }
                if (alamat.trim().equals("")) {
                    etAlamat.setError("alamat harus diisi!");
                }
                if (deskripsiKampus.trim().equals("")) {
                    etDeskripsiKampus.setError("deskripsi kampus harus diisi!");
                } else {
                    tambahKampus();
                }

            }
        });
    }
        private void tambahKampus(){
            APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardCreate(nama, akreditasi, motto, alamat, deskripsiKampus);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(TambahActivity.this, "Kode : " + kode + ", Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(TambahActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }
            });
    }
}