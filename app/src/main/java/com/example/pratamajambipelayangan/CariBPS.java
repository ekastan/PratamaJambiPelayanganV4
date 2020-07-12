package com.example.pratamajambipelayangan;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CariBPS extends AppCompatActivity {
    private TextView txtNoBPS;
    private String id;
    private Button btnCari;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_bps);

        myDialog = new Dialog(this);

        txtNoBPS = findViewById(R.id.txtNoBPS);
        btnCari = findViewById(R.id.btnCari);

        Intent intent = getIntent();
        id = intent.getStringExtra(konfigurasi.EMP_ID);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idBPS = txtNoBPS.getText().toString().trim().replaceAll("[.\\-]", "");


                if(v == btnCari) {
                    Intent intent = new Intent(CariBPS.this, TampilBPS.class);
                    intent.putExtra(konfigurasi.EMP_ID,id);
                    intent.putExtra(konfigurasi.EMP_ID_BPS,idBPS);
                    startActivity(intent);
                }
            }
        });

    }

}
