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

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.Holder>{

    private List<PersyaratanModel> mListData;
    private Context mContext;
    private String id_layanan;
    private String nama_layanan;
    private String url;
    private String penjelasan;
    private String foto;

    public TutorialAdapter(List<PersyaratanModel> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.adapter_item_data_tutorial, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        PersyaratanModel model = mListData.get(position);

        //set data layanan
        holder.tvIdLayanan.setText(model.getIdLayanan());
        holder.tvNamaLayanan.setText(model.getNamaLayanan());
        holder.tvUrl.setText(model.getUrl());
        holder.tvPenjelasan.setText(model.getPenjelasan());
        Picasso.get()
                .load(model.getFoto())
                .into(holder.tvFoto);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_layanan = holder.tvIdLayanan.getText().toString();
                nama_layanan = holder.tvNamaLayanan.getText().toString();
                url = holder.tvUrl.getText().toString();
                penjelasan = holder.tvPenjelasan.getText().toString();

                Intent intent = new Intent(mContext, Tutorial.class);
                intent.putExtra(konfigurasi.EMP_ID_LAYANAN,id_layanan);
                intent.putExtra(konfigurasi.TAG_NAMA_LAYANAN,nama_layanan);
                intent.putExtra(konfigurasi.TAG_URL_YOUTUBE,url);
                intent.putExtra(konfigurasi.TAG_PENJELASAN,penjelasan);

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
        public TextView tvUrl;
        public TextView tvPenjelasan;
        public ImageView tvFoto;

        public Context context;
        CardView parentLayout;

        public Holder(View itemView) {
            super(itemView);

            tvNamaLayanan = itemView.findViewById(R.id.textview_NamaLayanan);
            tvIdLayanan = itemView.findViewById(R.id.textview_IdLayanan);
            tvUrl = itemView.findViewById(R.id.textURL);
            tvPenjelasan = itemView.findViewById(R.id.textPenjelasan);
            tvFoto = itemView.findViewById(R.id.foto);
            parentLayout = itemView.findViewById(R.id.CardLayanan);

        }
    }

}
