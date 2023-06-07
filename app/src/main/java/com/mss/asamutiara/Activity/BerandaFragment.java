package com.mss.asamutiara.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

public class BerandaFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout btn_relawan_anggota;

    TextView nama_pengguna, nama_hierarki, nama_calon, target;
    ImageView img_profil;
    Session session;
    Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        session = new Session(getContext());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        btn_relawan_anggota = (LinearLayout) view.findViewById(R.id.btn_relawan_anggota);
        img_profil = (ImageView) view.findViewById(R.id.img_profil);

        nama_pengguna = view.findViewById(R.id.nama_pengguna);
        nama_hierarki = view.findViewById(R.id.nama_hierarki);
        nama_calon = view.findViewById(R.id.nama_calon);
        target = view.findViewById(R.id.target);

        nama_pengguna.setText(session.getNama());
        nama_hierarki.setText(session.getNamaHierarki());
        nama_calon.setText(session.getNamaCalon());
        target.setText(session.getTarget());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        btn_relawan_anggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DataRelawanActivity.class));
            }
        });

        return view;
    }
}