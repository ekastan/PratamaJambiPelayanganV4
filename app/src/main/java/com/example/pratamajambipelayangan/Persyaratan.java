package com.example.pratamajambipelayangan;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Persyaratan extends AppCompatActivity {

    private String urlData = konfigurasi.URL_GET_LAYANAN;
    private String id;
    private RecyclerView recyclerViewPersyaratan;
    private PersyaratanAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private List<PersyaratanModel> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persyaratan);

        recyclerViewPersyaratan = findViewById(R.id.recyclerview);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.show();

        mListData = new ArrayList<>();

        Intent intent = getIntent();
        id = intent.getStringExtra(konfigurasi.EMP_ID);

        getDataVolley();
    }

    private void getDataVolley(){

        final StringRequest request = new StringRequest(Request.Method.GET, urlData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressDialog.dismiss();
                        iniData(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void iniData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);

            // ini utk mengambil attribute array yg ada di json (yaitu attribute data)
            // karna attribute data adalah array makanya kita get menggunakan JSONArray
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            //looping utk array
            for(int i=0; i<jsonArray.length(); i++){
                //get json berdasarkan banyaknya data (index i)
                JSONObject objectLayanan = jsonArray.getJSONObject(i);

                //get data berdasarkan attribte yang ada dijsonnya (harus sama)
                String IdLayanan = objectLayanan.getString("id_layanan");
                String NamaLayanan = objectLayanan.getString("nama_layanan");
                String Keterangan = objectLayanan.getString("keterangan");
                String HariKerja = objectLayanan.getString("hari_kerja");
                String URLDokumen = objectLayanan.getString("url_formulir");
                String IconFormulir = objectLayanan.getString("icon_formulir");

                //add data ke modelnya
                PersyaratanModel persyaratanModel = new PersyaratanModel();
                persyaratanModel.setIdLayanan(IdLayanan);
                persyaratanModel.setNamaLayanan(NamaLayanan);
                persyaratanModel.setKeterangan(Keterangan);
                persyaratanModel.setHariKerja(HariKerja);
                persyaratanModel.setURLFormulir(URLDokumen);
                persyaratanModel.setIconFormulir(IconFormulir);

                //add model ke list
                mListData.add(persyaratanModel);

                //passing data list ke adapter
                mAdapter = new PersyaratanAdapter(mListData, Persyaratan.this);
                mAdapter.notifyDataSetChanged();
                recyclerViewPersyaratan.setLayoutManager(new LinearLayoutManager(Persyaratan.this));
                recyclerViewPersyaratan.setItemAnimator(new DefaultItemAnimator());
                recyclerViewPersyaratan.setAdapter(mAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
