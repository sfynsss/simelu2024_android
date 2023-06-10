package com.mss.asamutiara.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.material.textfield.TextInputLayout;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

import java.util.ArrayList;

/**
 * Created by Sfyn on 29/06/2018.
 */


public class AdapterPresiden extends RecyclerView.Adapter<AdapterPresiden.MyViewHolder> {

    private Context mContext;
    private Activity activity;
    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> nomor_urut = new ArrayList<String>();
    private ArrayList<String> nama_paslon = new ArrayList<String>();
    private ArrayList<String> gambar_paslon = new ArrayList<String>();
    private ArrayList<String> suara_paslon = new ArrayList<String>();
    RecyclerView recyclerView;
    RequestOptions option;
    int pos;

    Session session;
    MyListener myListener;

    public AdapterPresiden(Context mContext, Activity activity, ArrayList<String> id, ArrayList<String> nomor_urut,
                           ArrayList<String> nama_paslon, ArrayList<String> gambar_paslon, ArrayList<String> suara_paslon,
                           MyListener myListener) {
        this.mContext = mContext;
        this.activity = activity;
        this.id = id;
        this.nomor_urut = nomor_urut;
        this.nama_paslon = nama_paslon;
        this.gambar_paslon = gambar_paslon;
        this.suara_paslon = suara_paslon;
        this.myListener = myListener;

        option = new RequestOptions().placeholder(R.drawable.ic_hourglass_empty_24).error(R.drawable.ic_highlight_off_24);
        session = new Session(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_presiden, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nomor_urut.setText(nomor_urut.get(position));
        holder.nama_paslon.setText(nama_paslon.get(position));
        holder.suara_paslon.setText(suara_paslon.get(position));
        if (TextUtils.isEmpty(gambar_paslon.get(position))) {
            holder.gambar_paslon.setImageResource(R.drawable.ic_highlight_off_24);
        } else {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.signature(
                    new ObjectKey(String.valueOf(System.currentTimeMillis())));
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
//                    .load("http://192.168.1.16:8000/storage/" + gambar.get(position) + "").into(holder.gambar);
                    .load("http://" + session.getBaseUrl() + "/storage/upload/calon/gambar/"+ id.get(position) +"/" + gambar_paslon.get(position) + "").into(holder.gambar_paslon);
        }
        holder.suara_paslon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (myListener != null) {
                    myListener.addValue(charSequence.toString(), position);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomor_urut, nama_paslon;
        ImageView gambar_paslon;
        EditText suara_paslon;

        public MyViewHolder(View v) {
            super(v);
            nomor_urut = v.findViewById(R.id.nomer_urut);
            nama_paslon = v.findViewById(R.id.nama_paslon);
            gambar_paslon = v.findViewById(R.id.gambar_paslon);
            suara_paslon = v.findViewById(R.id.suara_paslon);
        }
    }

}
