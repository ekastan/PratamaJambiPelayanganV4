package com.example.pratamajambipelayangan;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KuesionerWP extends AppCompatActivity {
    private TextView txtStatus;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuesioner_wp);

        myDialog = new Dialog(this);
        Button btnKuesioner = findViewById(R.id.btnKuisioner);

        txtStatus = findViewById(R.id.txtStatus);

        getDataKuesioner();


        btnKuesioner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = txtStatus.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }

    private void getDataKuesioner(){
        class GetDataKuesioner extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(KuesionerWP.this,"Mengambil data...","Silahkan tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDataKuesioner(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,"1");
                return s;
            }
        }
        GetDataKuesioner ge = new GetDataKuesioner();
        ge.execute();
    }

    private void showDataKuesioner(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String urlKuesioner = c.getString(konfigurasi.TAG_URL_KUESIONER);
            txtStatus.setText(urlKuesioner);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}