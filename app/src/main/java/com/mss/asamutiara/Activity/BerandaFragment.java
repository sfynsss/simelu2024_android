package com.mss.asamutiara.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.CekSession;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout btn_relawan_anggota, btn_logistik;

    TextView nama_pengguna, nama_hierarki, nama_calon, target, perolehan, kurang, persentase;
    TextView judul_button, detail_judul_button, judul_hierarki;
    ImageView img_profil;
    LinearLayoutCompat d_content_hierarki;

    LoaderUi2 LoaderUi2;
    Session session;
    Api api;
    Call<BaseResponse<CekSession>> cekSession;

    int tmp_hierarki = 0;
    AdapterRelawanParent adapterRelawanParent;
    RecyclerView recycler_hierarki;

    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> judul = new ArrayList<String>();
    ArrayList<String> nama_relawan = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        session = new Session(getContext());
        LoaderUi2 = new LoaderUi2(getContext(), getActivity());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        getUser();
        btn_relawan_anggota = (LinearLayout) view.findViewById(R.id.btn_relawan_anggota);
        btn_logistik = (LinearLayout) view.findViewById(R.id.btn_logistik);
        img_profil = (ImageView) view.findViewById(R.id.img_profil);

        nama_pengguna = view.findViewById(R.id.nama_pengguna);
        nama_hierarki = view.findViewById(R.id.nama_hierarki);
        nama_calon = view.findViewById(R.id.nama_calon);
        target = view.findViewById(R.id.target);
        perolehan = view.findViewById(R.id.perolehan);
        kurang = view.findViewById(R.id.kurang);
        persentase = view.findViewById(R.id.persentase);
        judul_button = view.findViewById(R.id.judul_button);
        detail_judul_button = view.findViewById(R.id.detail_judul_button);
        recycler_hierarki = view.findViewById(R.id.recycle_hierarki);
        judul_hierarki = view.findViewById(R.id.judul_hierarki);
        d_content_hierarki = view.findViewById(R.id.d_content_hierarki);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        getUser();
                    }
                }, 1000);
            }
        });

        btn_relawan_anggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (tmp_hierarki == 6) {
                    intent = new Intent(getContext(), DataPengumpulanSuaraActivity.class);
                    intent.putExtra("relawan_id", "");
                    startActivityForResult(intent, 0);
                } else {
                    intent = new Intent(getContext(), DataRelawanActivity.class);
                    intent.putExtra("relawan_id", "");
                    startActivityForResult(intent, 0);
                }
            }
        });

        btn_logistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DataLogistikActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 1) {
                getUser();
            }
        }
    }

    public void getUser() {
        LoaderUi2.show();
        cekSession = api.cekSession();
        cekSession.enqueue(new Callback<BaseResponse<CekSession>>() {
            @Override
            public void onResponse(Call<BaseResponse<CekSession>> call, Response<BaseResponse<CekSession>> response) {
                if (response.isSuccessful()) {
                    LoaderUi2.dismiss();
                    tmp_hierarki = response.body().getData().get(0).getHierarkiId();
                    nama_pengguna.setText(response.body().getData().get(0).getNama());
                    if (tmp_hierarki == 6) {
                        nama_hierarki.setText(response.body().getData().get(0).getNamaHierarki()+" \n"
                                +response.body().getData().get(0).getDesa()+" "+response.body().getData().get(0).getTps());
                    } else {
                        nama_hierarki.setText(response.body().getData().get(0).getNamaHierarki());
                    }
                    if (!TextUtils.isEmpty(response.body().getData().get(0).getNamaCalon())) {
                        nama_calon.setText(response.body().getData().get(0).getNamaCalon());
                    } else {
                        nama_calon.setVisibility(View.GONE);
                    }
                    target.setText(response.body().getData().get(0).getTarget().toString());
                    perolehan.setText(response.body().getData().get(0).getSuaraCount().toString());
                    int tmp_kurang = response.body().getData().get(0).getSuaraCount() - response.body().getData().get(0).getTarget();
                    double tmp_persentase = ((double) response.body().getData().get(0).getSuaraCount() / (double) response.body().getData().get(0).getTarget()) * 100;
                    kurang.setText(tmp_kurang+"");
                    persentase.setText(Math.round(tmp_persentase)+"%");
                    if (tmp_hierarki == 6) {
                        judul_button.setText("Pengumpulan Suara");
                        detail_judul_button.setText("Lihat data suara");
                    } else {
                        judul_button.setText("Relawan Anggota");
                        detail_judul_button.setText("Lihat relawan anggota");
                    }

//                    Log.d("TAG", "onResponse: "+response.body().getData().get(0).getRelawan().size());
                    if (tmp_hierarki > 1) {
                        judul_hierarki.setVisibility(View.VISIBLE);
                        d_content_hierarki.setVisibility(View.VISIBLE);
                    }

                    id.clear();
                    judul.clear();
                    nama_relawan.clear();

                    for (int i = 0; i < response.body().getData().get(0).getRelawan().size() - 1; i++) {
                        id.add(response.body().getData().get(0).getRelawan().get(i).getId().toString());
                        String tmp_val = "";
                        if (response.body().getData().get(0).getRelawan().get(i).getHierarkiId() == 1) {
                            tmp_val = "KOORDINATOR DAPIL PROVINSI " + response.body().getData().get(0).getRelawan().get(i).getDapilProvinsi();
                        } else if (response.body().getData().get(0).getRelawan().get(i).getHierarkiId() == 2) {
                            tmp_val = "KOORDINATOR KABUPATEN " + response.body().getData().get(0).getRelawan().get(i).getKabupaten();
                        } else if (response.body().getData().get(0).getRelawan().get(i).getHierarkiId() == 3) {
                            tmp_val = "KOORDINATOR DAPIL KABUPATEN " + response.body().getData().get(0).getRelawan().get(i).getDapilKabupaten();
                        } else if (response.body().getData().get(0).getRelawan().get(i).getHierarkiId() == 4) {
                            tmp_val = "KOORDINATOR KECAMATAN " + response.body().getData().get(0).getRelawan().get(i).getKecamatan();
                        } else if (response.body().getData().get(0).getRelawan().get(i).getHierarkiId() == 5) {
                            tmp_val = "KOORDINATOR DESA " + response.body().getData().get(0).getRelawan().get(i).getDesa();
                        } else if (response.body().getData().get(0).getRelawan().get(i).getHierarkiId() == 6) {
                            tmp_val = "KOORDINATOR TPS " + response.body().getData().get(0).getRelawan().get(i).getTps();
                        }
                        judul.add(tmp_val);
                        nama_relawan.add(response.body().getData().get(0).getRelawan().get(i).getNama());
                    }

                    adapterRelawanParent = new AdapterRelawanParent(getContext(), getActivity(), id, judul, nama_relawan);
                    adapterRelawanParent.notifyDataSetChanged();
                    recycler_hierarki.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    recycler_hierarki.setAdapter(adapterRelawanParent);
                } else {
                    LoaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                    session.setUserStatus(false, "","", "", "", "",
                            "", "", "", "", "", "",
                            "","");
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CekSession>> call, Throwable t) {
                LoaderUi2.dismiss();
                Toast.makeText(getContext(), "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}