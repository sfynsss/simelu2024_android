package com.mss.asamutiara.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.mss.asamutiara.R;

import java.util.ArrayList;

/**
 * Created by Sfyn on 04/02/2018.
 */

public class AdapterRelawan extends ArrayAdapter<String> {

    private Activity context;
    private Context mContext;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> hierarki = new ArrayList<>();
    ArrayList<String> nama = new ArrayList<>();
    ArrayList<String> no_telp = new ArrayList<>();
    ArrayList<String> target = new ArrayList<>();
    ArrayList<String> persentase = new ArrayList<>();
    ArrayList<String> terpenuhi = new ArrayList<>();
    ArrayList<String> kekurangan_target = new ArrayList<>();

    public AdapterRelawan(Activity context, ArrayList<String> id, ArrayList<String> hierarki,
                          ArrayList<String> nama, ArrayList<String> no_telp,
                          ArrayList<String> target, ArrayList<String> persentase,
                          ArrayList<String> terpenuhi, ArrayList<String> kekurangan_target) {
        super(context, R.layout.adapter_relawan, id);

        this.context = context;
        this.mContext = context;
        this.id = id;
        this.hierarki = hierarki;
        this.nama = nama;
        this.no_telp = no_telp;
        this.target = target;
        this.persentase = persentase;
        this.terpenuhi = terpenuhi;
        this.kekurangan_target = kekurangan_target;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            v = layoutInflater.inflate(R.layout.adapter_relawan, null, true);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.hierarki.setText(hierarki.get(position));
        viewHolder.nama.setText(nama.get(position));
        viewHolder.no_telp.setText(no_telp.get(position));
        viewHolder.persentase.setText(persentase.get(position));
        viewHolder.target.setText(target.get(position));
        viewHolder.terpenuhi.setText(terpenuhi.get(position));
        viewHolder.kekurangan_target.setText(kekurangan_target.get(position));

        return v;
    }

    class ViewHolder{
        TextView hierarki, nama, no_telp;
        AppCompatButton persentase, target, terpenuhi, kekurangan_target;
        ViewHolder(View view){
            hierarki = view.findViewById(R.id.hierarki);
            nama = view.findViewById(R.id.nama);
            no_telp = view.findViewById(R.id.no_telp);
            persentase = view.findViewById(R.id.persentase);
            target = view.findViewById(R.id.target);
            terpenuhi = view.findViewById(R.id.terpenuhi);
            kekurangan_target = view.findViewById(R.id.kekurangan_target);
        }
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

}
