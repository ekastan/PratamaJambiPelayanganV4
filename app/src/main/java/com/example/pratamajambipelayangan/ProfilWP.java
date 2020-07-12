package com.example.pratamajambipelayangan;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfilWP extends AppCompatActivity {

    private TextView txtNPWP;
    private TextView txtNamaWP;
    private TextView txtNamaAR;
    private TextView txtAlamatWP;
    private TextView txtKLU;
    private TextView txtTunggakan;
    private String id;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_wp);
        txtNPWP = findViewById(R.id.textNPWP);
        txtNamaWP = findViewById(R.id.textNamaWP);
        txtNamaAR = findViewById(R.id.textNamaAR);
        txtAlamatWP = findViewById(R.id.textAlamatWP);
        txtKLU = findViewById(R.id.textKLU);
        txtTunggakan = findViewById(R.id.txtTunggakan);

        myDialog = new Dialog(this);

        Intent intent = getIntent();
        id = intent.getStringExtra(konfigurasi.EMP_ID);
        txtNPWP.setText(id);

        getDataWP();

    }
    private void getDataWP(){
        class GetDataWP extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ProfilWP.this,"Mengambil data...","Silahkan tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDataWP(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetDataWP ge = new GetDataWP();
        ge.execute();
    }

    private void showDataWP(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama_ar = c.getString(konfigurasi.TAG_NAMA_AR);
            String nama_wp = c.getString(konfigurasi.TAG_NAMA_WP);
            String alamat_wp = c.getString(konfigurasi.TAG_ALAMAT_WP);
            String klu = c.getString(konfigurasi.TAG_KLU);
            String tunggakan = c.getString(konfigurasi.TAG_TUNGGAKAN);

            txtNamaAR.setText(nama_ar);
            txtAlamatWP.setText(alamat_wp);
            txtKLU.setText(klu);
            txtNamaWP.setText(nama_wp);
            txtTunggakan.setText(tunggakan);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
