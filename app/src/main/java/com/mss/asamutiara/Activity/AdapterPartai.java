package com.mss.asamutiara.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

import java.util.ArrayList;

/**
 * Created by Sfyn on 29/06/2018.
 */

public class AdapterPartai extends RecyclerView.Adapter<AdapterPartai.MyViewHolder> {

    private Context mContext;
    private Activity activity;
    Session session;

    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> nmr_partai = new ArrayList<String>();
    private ArrayList<String> nama_partai = new ArrayList<String>();
    private ArrayList<String> imgPartai = new ArrayList<String>();
    private ArrayList<String> jml_suara = new ArrayList<String>();

    RecyclerView recyclerView;
    RequestOptions option;

    private OnEditLocationListener cardOnClick;

    public AdapterPartai(Context mContext, Activity activity, ArrayList<String> id, ArrayList<String> nmr_partai,
                         ArrayList<String> nama_partai, ArrayList<String> imgPartai, ArrayList<String> jml_suara,
                         OnEditLocationListener cardOnClick) {
        this.mContext = mContext;
        this.activity = activity;

        session = new Session(mContext);

        this.id = id;
        this.nmr_partai = nmr_partai;
        this.nama_partai = nama_partai;
        this.imgPartai = imgPartai;
        this.jml_suara = jml_suara;
        this.cardOnClick = cardOnClick;
        option = new RequestOptions().placeholder(R.drawable.ic_hourglass_empty_24).error(R.drawable.ic_highlight_off_24);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_partai, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.no_partai.setText(nmr_partai.get(position));
        if (imgPartai.get(position).equals("")) {
            holder.img_partai.setImageResource(R.drawable.ic_hourglass_empty_24);
        } else {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.signature(
                    new ObjectKey(String.valueOf(System.currentTimeMillis())));
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
//                    .load("http://192.168.1.16:8000/storage/" + gambar.get(position) + "").into(holder.gambar);
                    .load("http://" + session.getBaseUrl() + "/storage/upload/partai/logo/"+ id.get(position)
                            +"/" + imgPartai.get(position) + "").into(holder.img_partai);
        }
        holder.nm_partai.setText(nama_partai.get(position));
        holder.nm_partai.setText(nama_partai.get(position));
        holder.suara_partai.setText(jml_suara.get(position));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardOnClick != null) {
                    cardOnClick.onClickAdapter(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nmr_partai.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView no_partai;
        ImageView img_partai;
        TextView nm_partai;
        EditText suara_partai;
        ImageView edit;

        public MyViewHolder(View itemView) {
            super(itemView);

            no_partai = (TextView) itemView.findViewById(R.id.no_urut_partai);
            img_partai = (ImageView) itemView.findViewById(R.id.img_partai);
            img_partai.setAdjustViewBounds(false);
            nm_partai = (TextView) itemView.findViewById(R.id.nama_partai);
            suara_partai = (EditText) itemView.findViewById(R.id.suara_partai);
            edit = itemView.findViewById(R.id.edit);
        }
    }

    public interface OnEditLocationListener {
        void onClickAdapter(int position);
    }

}
