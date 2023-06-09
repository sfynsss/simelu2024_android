package com.mss.asamutiara.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

public class ProfilFragment extends Fragment {

    RelativeLayout btn_logout, btn_edit, btn_qr;

    Session session;
    Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        session = new Session(getContext());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        btn_edit = view.findViewById(R.id.btn_edit);
        btn_logout = view.findViewById(R.id.btn_logout);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EditProfilActivity.class));
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setUserStatus(false, "","", "", "", "",
                        "", "", "", "", "", "",
                        "","");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }

}