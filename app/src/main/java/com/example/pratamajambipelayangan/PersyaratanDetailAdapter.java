package com.example.pratamajambipelayangan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by putuguna on 21/09/17.
 */

public class PersyaratanDetailAdapter extends RecyclerView.Adapter<PersyaratanDetailAdapter.Holder>{

    private List<PersyaratanModel> mListData;
    private Context mContext;


    public PersyaratanDetailAdapter(List<PersyaratanModel> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.adapter_item_data_detail, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        PersyaratanModel model = mListData.get(position);

        //set data layanan
        holder.tvDokumen.setText(model.getDokumen());


    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView tvDokumen;
        public Context context;
        CardView parentLayout;

        public Holder(View itemView) {
            super(itemView);

            tvDokumen = itemView.findViewById(R.id.textDokumen);
            parentLayout = itemView.findViewById(R.id.CardLayanan);

        }
    }
}
