package com.if4a.kampusproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.if4a.kampusproject.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama, tvAkreditasi, tvMotto, tvAlamat, tvDeskripsi;
    private Button btn_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvAkreditasi = findViewById(R.id.tv_akreditasi);
        tvMotto = findViewById(R.id.tv_motto);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        btn_kembali = findViewById(R.id.btn_kembali);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varNama");
        String akreditasi = intent.getStringExtra("varAkreditasi");
        String motto = intent.getStringExtra("varMotto");
        String alamat = intent.getStringExtra("varAlamat");
        String deskripsi = intent.getStringExtra("varDeskripsi");

        tvNama.setText(nama);
        tvAkreditasi.setText(akreditasi);
        tvMotto.setText(motto);
        tvAlamat.setText(alamat);
        tvDeskripsi.setText(deskripsi);

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                finish();
            }
        });



    }
}