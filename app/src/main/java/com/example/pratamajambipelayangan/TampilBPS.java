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

public class TampilBPS extends AppCompatActivity {
    private String idbps;
    private String id;
    private TextView txtNoBPS;
    private TextView txtStatus;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_bps);

        myDialog = new Dialog(this);

        txtNoBPS = findViewById(R.id.txtNoBPS);
        txtStatus = findViewById(R.id.txtStatus);


        Intent intent = getIntent();
        id = intent.getStringExtra(konfigurasi.EMP_ID);
        idbps = intent.getStringExtra(konfigurasi.EMP_ID_BPS);
        txtNoBPS.setText(idbps);

        getDataBPS();
    }

    private void getDataBPS(){
        class GetDataBPS extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilBPS.this,"Mengambil data...","Silahkan tunggu...",false,false);
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
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_BPS,idbps);
                return s;
            }
        }
        GetDataBPS ge = new GetDataBPS();
        ge.execute();
    }

    private void showDataWP(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String status = c.getString(konfigurasi.TAG_NAMA);
            txtStatus.setText(status);

            if(txtStatus.getText().toString().equals("SELESAI")) {
                txtStatus.setBackgroundColor(getResources().getColor(R.color.colorYellow));
            }
            else {if(txtStatus.getText().toString().equals("null")){
                txtStatus.setBackgroundColor(getResources().getColor(R.color.Merah));
                txtStatus.setTextColor(getResources().getColor(R.color.colorYellow));
                txtStatus.setText("TIDAK DITEMUKAN");
            }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}