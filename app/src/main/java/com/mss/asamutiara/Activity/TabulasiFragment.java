package com.mss.asamutiara.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mss.asamutiara.R;

public class TabulasiFragment extends Fragment {

    FilterWilayah FilterWilayah;
    AppCompatImageView filter;
    CardView data_induk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabulasi, container, false);

        FilterWilayah = new FilterWilayah(getContext(), getActivity());
        filter = view.findViewById(R.id.filter);
        data_induk = view.findViewById(R.id.data_induk);

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

        return view;
    }
}