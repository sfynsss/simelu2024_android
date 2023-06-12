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


public class AdapterCaleg extends RecyclerView.Adapter<AdapterCaleg.MyViewHolder> {

    private Context mContext;
    private Activity activity;
    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> nomor_urut = new ArrayList<String>();
    private ArrayList<String> nama_caleg = new ArrayList<String>();
    private ArrayList<String> suara_caleg = new ArrayList<String>();

    Session session;
    MyListener myListener;

    public AdapterCaleg(Context mContext, Activity activity, ArrayList<String> id, ArrayList<String> nomor_urut,
                        ArrayList<String> nama_caleg, ArrayList<String> suara_caleg,
                        MyListener myListener) {
        this.mContext = mContext;
        this.activity = activity;
        this.id = id;
        this.nomor_urut = nomor_urut;
        this.nama_caleg = nama_caleg;
        this.suara_caleg = suara_caleg;
        this.myListener = myListener;

        session = new Session(mContext);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_caleg, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nomor_urut.setText(nomor_urut.get(position));
        holder.nama_caleg.setText(nama_caleg.get(position));
        holder.suara_caleg.setText(suara_caleg.get(position));
        holder.suara_caleg.addTextChangedListener(new TextWatcher() {
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
        TextView nomor_urut, nama_caleg;
        EditText suara_caleg;

        public MyViewHolder(View v) {
            super(v);
            nomor_urut = v.findViewById(R.id.nomer_urut);
            nama_caleg = v.findViewById(R.id.nama_caleg);
            suara_caleg = v.findViewById(R.id.suara_caleg);
        }
    }

}
