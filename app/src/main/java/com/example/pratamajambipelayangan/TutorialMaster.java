package com.example.pratamajambipelayangan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class TutorialMaster extends AppCompatActivity {

    private String urlData = konfigurasi.URL_GET_TUTORIAL;
    private String id;
    private RecyclerView recyclerViewPersyaratan;
    private TutorialAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private List<PersyaratanModel> mListData;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_master);

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
                JSONObject objectTutorial = jsonArray.getJSONObject(i);

                //get data berdasarkan attribte yang ada dijsonnya (harus sama)
                String IdLayanan = objectTutorial.getString("id");
                String NamaLayanan = objectTutorial.getString("tutorial");
                String Url = objectTutorial.getString("url");
                String Penjelasan = objectTutorial.getString("penjelasan");
                String Gambar = objectTutorial.getString("foto");

                //add data ke modelnya
                PersyaratanModel persyaratanModel = new PersyaratanModel();
                persyaratanModel.setIdLayanan(IdLayanan);
                persyaratanModel.setNamaLayanan(NamaLayanan);
                persyaratanModel.setUrl(Url);
                persyaratanModel.setPenjelasan(Penjelasan);
                persyaratanModel.setFoto(Gambar);

                //add model ke list
                mListData.add(persyaratanModel);

                //passing data list ke adapter
                mAdapter = new TutorialAdapter(mListData, TutorialMaster.this);
                mAdapter.notifyDataSetChanged();
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
                recyclerViewPersyaratan.setLayoutManager(mLayoutManager);
                recyclerViewPersyaratan.setItemAnimator(new DefaultItemAnimator());
                recyclerViewPersyaratan.setAdapter(mAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
