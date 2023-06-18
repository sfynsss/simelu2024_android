package com.mss.asamutiara.Activity;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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


public class AdapterBerkas extends RecyclerView.Adapter<AdapterBerkas.MyViewHolder> {

    private Context mContext;
    private Activity activity;
    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> gambar_berkas = new ArrayList<String>();
    RecyclerView recyclerView;
    RequestOptions option;
    int pos;

    Session session;
    OnEditLocationListener hapus;

    public AdapterBerkas(Context mContext, Activity activity, ArrayList<String> id, ArrayList<String> gambar_berkas,
                         OnEditLocationListener hapus) {
        this.mContext = mContext;
        this.activity = activity;
        this.id = id;
        this.gambar_berkas = gambar_berkas;
        this.hapus = hapus;

        option = new RequestOptions().placeholder(R.drawable.ic_hourglass_empty_24).error(R.drawable.ic_highlight_off_24);
        session = new Session(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_berkas, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (TextUtils.isEmpty(gambar_berkas.get(position))) {
            holder.gambar_berkas.setImageResource(R.drawable.ic_highlight_off_24);
        } else {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.signature(
                    new ObjectKey(String.valueOf(System.currentTimeMillis())));
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
//                    .load("http://192.168.1.16:8000/storage/" + gambar.get(position) + "").into(holder.gambar);
                    .load("http://" + session.getBaseUrl() + "/storage/upload/calon/gambar/"+ id.get(position) +"/" + gambar_berkas.get(position) + "")
                    .into(holder.gambar_berkas);
        }

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hapus != null) {
                    hapus.onClickAdapter(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar_berkas, btn_hapus;

        public MyViewHolder(View v) {
            super(v);
            gambar_berkas = v.findViewById(R.id.gambar_berkas);
            btn_hapus = v.findViewById(R.id.btn_hapus);
        }
    }

    public interface OnEditLocationListener {
        void onClickAdapter(int position);
    }
}
