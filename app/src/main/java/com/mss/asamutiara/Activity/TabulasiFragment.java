package com.mss.asamutiara.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

public class TabulasiFragment extends Fragment {

    FilterWilayah FilterWilayah;
    AppCompatImageView filter;
    CardView data_induk, presiden, dpr_pusat, dpr_prov, dpr_kab;
    TextView title_tabulasi;

    Session session;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabulasi, container, false);

        filter = view.findViewById(R.id.filter);
        data_induk = view.findViewById(R.id.data_induk);
        presiden = view.findViewById(R.id.presiden);
        dpr_pusat = view.findViewById(R.id.dpr_pusat);
        dpr_prov = view.findViewById(R.id.dpr_prov);
        dpr_kab = view.findViewById(R.id.dpr_kab);
        title_tabulasi = view.findViewById(R.id.title_tabulasi);
        session = new Session(getContext());

        if (!session.getNamaTpsActive().equals("")) {
            title_tabulasi.setText(session.getNamaTpsActive());
        }

        FilterWilayah = new FilterWilayah(getContext(), getActivity(), new FilterWilayah.OnEditLocationListener() {
            @Override
            public void onClickAdapter(String tps_id, String tps) {
                if (!session.getNamaTpsActive().equals("")) {
                    title_tabulasi.setText(session.getNamaTpsActive());
                } else {
                    title_tabulasi.setText(tps);
                }
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterWilayah.show();
            }
        });

        data_induk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InputC1Induk.class));
            }
        });

        presiden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InputC1Presiden.class));
            }
        });

        dpr_pusat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), InputC1Dpr.class);
                intent.putExtra("kategori", "1");
                startActivity(intent);
            }
        });

        dpr_prov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), InputC1Dpr.class);
                intent.putExtra("kategori", "2");
                startActivity(intent);
            }
        });

        dpr_kab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), InputC1Dpr.class);
                intent.putExtra("kategori", "3");
                startActivity(intent);
            }
        });

        return view;
    }
}