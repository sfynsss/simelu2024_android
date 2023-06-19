package com.mss.asamutiara.Activity;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

import java.util.ArrayList;

/**
 * Created by Sfyn on 29/06/2018.
 */


public class AdapterRelawanParent extends RecyclerView.Adapter<AdapterRelawanParent.MyViewHolder> {

    private Context mContext;
    private Activity activity;
    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> judul = new ArrayList<String>();
    private ArrayList<String> nama_relawan = new ArrayList<String>();

    Session session;

    public AdapterRelawanParent(Context mContext, Activity activity, ArrayList<String> id, ArrayList<String> judul,
                                ArrayList<String> nama_relawan) {
        this.mContext = mContext;
        this.activity = activity;
        this.id = id;
        this.judul = judul;
        this.nama_relawan = nama_relawan;

        session = new Session(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_relawan_parent, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.judul.setText(judul.get(position));
        holder.nama_relawan.setText(nama_relawan.get(position));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul, nama_relawan;

        public MyViewHolder(View v) {
            super(v);
            judul = v.findViewById(R.id.judul);
            nama_relawan = v.findViewById(R.id.nama_relawan);
        }
    }

}
