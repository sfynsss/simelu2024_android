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
    private ArrayList<String> penyalur = new ArrayList<>();
    private ArrayList<String> penerima = new ArrayList<>();
    private ArrayList<String> foto = new ArrayList<>();
    private ArrayList<String> nama_barang = new ArrayList<>();
    private ArrayList<String> jumlah_logistik = new ArrayList<>();
    private ArrayList<String> lat = new ArrayList<>();
    private ArrayList<String> leng = new ArrayList<>();
    RequestOptions option;
    Session session;

    public AdapterLogistik(Activity context, ArrayList<String> id,
                           ArrayList<String> penyalur, ArrayList<String> penerima, ArrayList<String> foto,
                           ArrayList<String> nama_barang, ArrayList<String> jumlah_logistik,
                           ArrayList<String> lat, ArrayList<String> leng) {
        super(context, R.layout.adapter_logistik, id);

        this.context = context;
        this.mContext = context;
        this.id = id;
        this.penyalur = penyalur;
        this.penerima = penerima;
        this.foto = foto;
        this.nama_barang = nama_barang;
        this.jumlah_logistik = jumlah_logistik;
        this.lat = lat;
        this.leng = leng;

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

        if (foto.get(position).equals("")) {
            viewHolder.gambar_logistik.setImageResource(R.drawable.ic_hourglass_empty_24);
        } else {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.signature(
                    new ObjectKey(String.valueOf(System.currentTimeMillis())));
            requestOptions.placeholder(R.drawable.ic_hourglass_empty_24).error(R.drawable.ic_highlight_off_24);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
//                    .load("http://192.168.1.16:8000/storage/" + gambar.get(position) + "").into(holder.gambar);
                    .load("http://" + session.getBaseUrl() + "/storage/upload/master_logistik/"+ id.get(position)
                            +"/" + foto.get(position) + "").into(viewHolder.gambar_logistik);
        }

        viewHolder.penyalur.setText(penyalur.get(position));
        viewHolder.penerima.setText(penerima.get(position));
        viewHolder.nama_barang.setText(nama_barang.get(position));
        viewHolder.jumlah.setText(jumlah_logistik.get(position));
        viewHolder.lat.setText(lat.get(position));
        viewHolder.leng.setText(leng.get(position));

        return v;
    }

    class ViewHolder{
        TextView penyalur, penerima, lat, leng, nama_barang, jumlah;
        ImageView gambar_logistik;
        ViewHolder(View view){
            penyalur = view.findViewById(R.id.penyalur);
            penerima = view.findViewById(R.id.penerima);
            lat = view.findViewById(R.id.lat);
            leng = view.findViewById(R.id.leng);
            nama_barang = view.findViewById(R.id.nama_barang);
            jumlah = view.findViewById(R.id.jumlah);
            gambar_logistik = view.findViewById(R.id.gambar_logistik);
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
