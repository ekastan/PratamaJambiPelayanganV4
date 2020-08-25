package com.example.pratamajambipelayangan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class PersyaratanDetail extends AppCompatActivity {

    private String urlData = konfigurasi.URL_GET_LAYANAN_DETAIL;
    private RecyclerView recyclerViewDokumen;
    private PersyaratanDetailAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private List<PersyaratanModel> mListData;
    private String id_layanan;
    private String hari_kerja;
    private String nama_layanan;
    private String url_formulir;
    private Button btnFormulir;
    private TextView txtIdLayanan;
    private TextView txtNamaLayanan;
    private TextView txtHariKerja;
    private TextView txtURL;
    private TextView txtURLFormulir;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persyaratan_detail);

        recyclerViewDokumen = findViewById(R.id.recyclerviewDokumen);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.show();

        mListData = new ArrayList<>();

        myDialog = new Dialog(this);

        Intent intent = getIntent();
        hari_kerja = intent.getStringExtra(konfigurasi.TAG_HARI_KERJA);
        id_layanan = intent.getStringExtra(konfigurasi.EMP_ID_LAYANAN);
        nama_layanan = intent.getStringExtra(konfigurasi.TAG_NAMA_LAYANAN);
        url_formulir = intent.getStringExtra(konfigurasi.TAG_URL_FORMULIR);

        txtURL = findViewById(R.id.txtURL);
        txtURL.setText(konfigurasi.URL_GET_LAYANAN_DETAIL+id_layanan);

        txtNamaLayanan = findViewById(R.id.txtNamaLayanan);
        txtNamaLayanan.setText(nama_layanan);

        txtHariKerja = findViewById(R.id.txtharikerja);
        txtHariKerja.setText(hari_kerja+" hari kerja");

        btnFormulir = findViewById(R.id.btnFormulir);

        getDataVolley();

        btnFormulir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_formulir));
                intent.putExtra(konfigurasi.EMP_ID_LAYANAN,id_layanan);
                intent.putExtra(konfigurasi.TAG_HARI_KERJA,hari_kerja);
                intent.putExtra(konfigurasi.TAG_NAMA_LAYANAN,nama_layanan);
                intent.putExtra(konfigurasi.TAG_URL_FORMULIR,url_formulir);
                startActivity(intent);
            }
        });
    }

    private void getDataVolley(){
        urlData = txtURL.getText().toString();
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
                JSONObject objectPersyaratan = jsonArray.getJSONObject(i);

                //get data berdasarkan attribte yang ada dijsonnya (harus sama)
                String Dokumen = objectPersyaratan.getString("dokumen");

                //add data ke modelnya
                PersyaratanModel persyaratanModel = new PersyaratanModel();
                persyaratanModel.setDokumen(Dokumen);

                //add model ke list
                mListData.add(persyaratanModel);

                //passing data list ke adapter
                mAdapter = new PersyaratanDetailAdapter(mListData, PersyaratanDetail.this);
                mAdapter.notifyDataSetChanged();
                recyclerViewDokumen.setLayoutManager(new LinearLayoutManager(PersyaratanDetail.this));
                recyclerViewDokumen.setItemAnimator(new DefaultItemAnimator());
                recyclerViewDokumen.setAdapter(mAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    }