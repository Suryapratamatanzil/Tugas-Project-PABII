package com.if4a.kampusproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.if4a.kampusproject.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvAkreditasi, tvMotto, tvAlamat, tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvAkreditasi = findViewById(R.id.tv_akreditasi);
        tvMotto = findViewById(R.id.tv_motto);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varnama");

        setTitle(nama);
        tvNama.setText(nama);


    }
}