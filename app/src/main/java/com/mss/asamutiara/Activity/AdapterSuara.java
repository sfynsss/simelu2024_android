package com.mss.asamutiara.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.mss.asamutiara.R;

import java.util.ArrayList;

/**
 * Created by Sfyn on 04/02/2018.
 */

public class AdapterSuara extends ArrayAdapter<String> {

    private Activity context;
    private Context mContext;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> nik = new ArrayList<>();
    ArrayList<String> nama = new ArrayList<>();
    ArrayList<String> no_telp = new ArrayList<>();

    public AdapterSuara(Activity context, ArrayList<String> id, ArrayList<String> nik,
                        ArrayList<String> nama, ArrayList<String> no_telp) {
        super(context, R.layout.adapter_suara, id);

        this.context = context;
        this.mContext = context;
        this.id = id;
        this.nik = nik;
        this.nama = nama;
        this.no_telp = no_telp;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            v = layoutInflater.inflate(R.layout.adapter_suara, null, true);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.nik.setText(nik.get(position));
        viewHolder.nama.setText(nama.get(position));
        viewHolder.no_telp.setText(no_telp.get(position));

        return v;
    }

    class ViewHolder{
        TextView nik, nama, no_telp;
        ViewHolder(View view){
            nik = view.findViewById(R.id.nik);
            nama = view.findViewById(R.id.nama);
            no_telp = view.findViewById(R.id.no_telp);
        }
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }
}
