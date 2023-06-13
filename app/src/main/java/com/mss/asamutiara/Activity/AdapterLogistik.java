package com.mss.asamutiara.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

import java.util.ArrayList;

/**
 * Created by Sfyn on 04/02/2018.
 */

public class AdapterLogistik extends ArrayAdapter<String> {

    private Activity context;
    private Context mContext;
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> posisi_logistik = new ArrayList<>();
    private ArrayList<String> gambar_logistik = new ArrayList<>();
    private ArrayList<String> nama_barang = new ArrayList<>();
    private ArrayList<String> jumlah_logistik = new ArrayList<>();
    private OnEditLocationListener salurkan;
    RequestOptions option;
    Session session;

    public AdapterLogistik(Activity context, ArrayList<String> id,
                           ArrayList<String> posisi_logistik, ArrayList<String> gambar_logistik, ArrayList<String> nama_barang,
                           ArrayList<String> jumlah_logistik, OnEditLocationListener salurkan) {
        super(context, R.layout.adapter_logistik, id);

        this.context = context;
        this.mContext = context;
        this.id = id;
        this.posisi_logistik = posisi_logistik;
        this.gambar_logistik = gambar_logistik;
        this.nama_barang = nama_barang;
        this.jumlah_logistik = jumlah_logistik;
        this.salurkan = salurkan;

        option = new RequestOptions().placeholder(R.drawable.ic_hourglass_empty_24).error(R.drawable.ic_highlight_off_24);
        session = new Session(context);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            v = layoutInflater.inflate(R.layout.adapter_logistik, null, true);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        if (gambar_logistik.get(position).equals("")) {
            viewHolder.gambar_logistik.setImageResource(R.drawable.ic_hourglass_empty_24);
        } else {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.signature(
                    new ObjectKey(String.valueOf(System.currentTimeMillis())));
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
//                    .load("http://192.168.1.16:8000/storage/" + gambar.get(position) + "").into(holder.gambar);
                    .load("http://" + session.getBaseUrl() + "/storage/upload/master_logistik/"+ id.get(position)
                            +"/" + gambar_logistik.get(position) + "").into(viewHolder.gambar_logistik);
        }

        viewHolder.posisi_logistik.setText(posisi_logistik.get(position));
        viewHolder.nama_barang.setText(nama_barang.get(position));
        viewHolder.jumlah.setText(jumlah_logistik.get(position));

        viewHolder.btn_salur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (salurkan != null) {
                    salurkan.onClickAdapter(position);
                }
            }
        });

        return v;
    }

    class ViewHolder{
        TextView posisi_logistik, nama_barang, jumlah;
        ImageView gambar_logistik;
        AppCompatButton btn_salur;
        ViewHolder(View view){
            posisi_logistik = view.findViewById(R.id.posisi_logistik);
            nama_barang = view.findViewById(R.id.nama_barang);
            jumlah = view.findViewById(R.id.jumlah);
            gambar_logistik = view.findViewById(R.id.gambar_logistik);
            btn_salur = view.findViewById(R.id.btn_salur);
        }
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    public interface OnEditLocationListener {
        void onClickAdapter(int position);
    }

}
