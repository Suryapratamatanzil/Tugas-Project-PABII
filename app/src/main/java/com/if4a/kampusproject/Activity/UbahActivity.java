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

public class UbahActivity extends AppCompatActivity {
    private String yId, yNama, yAkreditasi, yMotto, yAlamat, yDeskripsi;
    private EditText etNama, etAkreditasi, etMotto, etAlamat, etDeskripsi;
    private Button btnubah, btnkembali;
    private String nama, akreditasi, motto, alamat, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yAkreditasi = ambil.getStringExtra("xAkreditasi");
        yMotto = ambil.getStringExtra("xMotto");
        yAlamat = ambil.getStringExtra("xAlamat");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");

        etNama = findViewById(R.id.et_nama);
        etAkreditasi = findViewById(R.id.et_akreditasi);
        etMotto = findViewById(R.id.et_motto);
        etAlamat = findViewById(R.id.et_alamat);
        etDeskripsi = findViewById(R.id.et_deskripsi_kampus);
        btnubah = findViewById(R.id.btn_ubah);
        btnkembali = findViewById(R.id.btn_kembali);

        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UbahActivity.this, MainActivity.class));
                finish();
            }
        });

        etNama.setText(yNama);
        etAkreditasi.setText(yAkreditasi);
        etMotto.setText(yMotto);
        etAlamat.setText(yAlamat);
        etDeskripsi.setText(yDeskripsi);

        btnubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                akreditasi = etAkreditasi.getText().toString();
                motto = etMotto.getText().toString();
                alamat = etAlamat.getText().toString();
                deskripsi = etDeskripsi.getText().toString();

                if (nama.trim().equals("")) {
                    etNama.setError("nama harus diisi!");
                }
                if (akreditasi.trim().equals("")) {
                    etAkreditasi.setError("akreditasi harus diisi!");
                }
                if (motto.trim().equals("")) {
                    etMotto.setError("motto harus diisi!");
                }
                if (alamat.trim().equals("")) {
                    etAlamat.setError("alamat harus diisi");
                }
                if (deskripsi.trim().equals("")) {
                    etDeskripsi.setError("deskripsi harus diisi");
                } else {
                    ubahKampus();
                }
            }
        });
    }

    private void ubahKampus() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(yId, nama, akreditasi, motto,
                alamat, deskripsi);
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : " + kode + ", Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}