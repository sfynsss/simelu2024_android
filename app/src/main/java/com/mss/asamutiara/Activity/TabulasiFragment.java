package com.mss.asamutiara.Activity;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mss.asamutiara.R;

public class TabulasiFragment extends Fragment {

    FilterWilayah FilterWilayah;
    AppCompatImageView filter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabulasi, container, false);

        FilterWilayah = new FilterWilayah(getContext(), getActivity());
        filter = view.findViewById(R.id.filter);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterWilayah.show();
            }
        });

        return view;
    }
}