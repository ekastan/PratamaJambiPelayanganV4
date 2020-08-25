package com.example.pratamajambipelayangan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by putuguna on 21/09/17.
 */

public class PersyaratanAdapter extends RecyclerView.Adapter<PersyaratanAdapter.Holder>{

    private List<PersyaratanModel> mListData;
    private Context mContext;
    private String id_layanan;
    private String hari_kerja;
    private String nama_layanan;
    private String url_formulir;
    private String icon_formulir;


    public PersyaratanAdapter(List<PersyaratanModel> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.adapter_item_data, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        PersyaratanModel model = mListData.get(position);

        //set data layanan
        holder.tvIdLayanan.setText(model.getIdLayanan());
        holder.tvNamaLayanan.setText(model.getNamaLayanan());
        holder.tvHariKerja.setText(model.getHariKerja());
        holder.tvURLFormulir.setText(model.getURLFormulir());
        holder.tvURLIconFormulir.setText(model.getIconFormulir());
        String URLicon_formulir = holder.tvURLIconFormulir.getText().toString();

        Picasso.get()
                .load(URLicon_formulir)
                .into(holder.ivIconFormulir);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_layanan = holder.tvIdLayanan.getText().toString();
                hari_kerja = holder.tvHariKerja.getText().toString();
                nama_layanan = holder.tvNamaLayanan.getText().toString();
                url_formulir = holder.tvURLFormulir.getText().toString();
                icon_formulir = holder.tvURLIconFormulir.getText().toString();

                Intent intent = new Intent(mContext, PersyaratanDetail.class);
                intent.putExtra(konfigurasi.EMP_ID_LAYANAN,id_layanan);
                intent.putExtra(konfigurasi.TAG_HARI_KERJA,hari_kerja);
                intent.putExtra(konfigurasi.TAG_NAMA_LAYANAN,nama_layanan);
                intent.putExtra(konfigurasi.TAG_URL_FORMULIR,url_formulir);
                intent.putExtra(konfigurasi.TAG_ICON_FORMULIR,icon_formulir);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView tvNamaLayanan;
        public TextView tvIdLayanan;
        public TextView tvHariKerja;
        public TextView tvURLFormulir;
        public TextView tvURLIconFormulir;
        public ImageView ivIconFormulir;

        public Context context;
        CardView parentLayout;

        public Holder(View itemView) {
            super(itemView);

            tvNamaLayanan = itemView.findViewById(R.id.textview_NamaLayanan);
            tvIdLayanan = itemView.findViewById(R.id.textview_IdLayanan);
            tvHariKerja = itemView.findViewById(R.id.textHariKerja);
            tvURLFormulir = itemView.findViewById(R.id.textURLFormulir);
            tvURLIconFormulir = itemView.findViewById(R.id.textURLIconFormulir);
            ivIconFormulir = itemView.findViewById(R.id.IconFormulir);
            parentLayout = itemView.findViewById(R.id.CardLayanan);

        }
    }
}
