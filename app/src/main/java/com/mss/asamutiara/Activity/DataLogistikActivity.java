package com.mss.asamutiara.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.MasterLogistik;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLogistikActivity extends AppCompatActivity {

    Activity activity;
    Context context;
    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;
    Session session;
    Api api;
    Call<BaseResponse<MasterLogistik>> callGetMasterLogistik;

    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> id_penyalur = new ArrayList<>();
    private ArrayList<String> posisi_logistik = new ArrayList<>();
    private ArrayList<String> gambar_logistik = new ArrayList<>();
    private ArrayList<String> nama_barang = new ArrayList<>();
    private ArrayList<String> jumlah_logistik = new ArrayList<>();

    AdapterLogistik adapterLogistik;
    ListView list_logistik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_logistik);

        activity = this;
        context = this;

        loaderUi = new LoaderUi(context, activity);
        loaderUi2 = new LoaderUi2(context, activity);
        session = new Session(context);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        list_logistik = findViewById(R.id.list_logistik);
        getData();
    }

    public void getData() {
        loaderUi2.show();
        callGetMasterLogistik = api.getDataLogistik();
        callGetMasterLogistik.enqueue(new Callback<BaseResponse<MasterLogistik>>() {
            @Override
            public void onResponse(Call<BaseResponse<MasterLogistik>> call, Response<BaseResponse<MasterLogistik>> response) {
                if (response.isSuccessful()) {
                    id.clear();
                    id_penyalur.clear();
                    posisi_logistik.clear();
                    gambar_logistik.clear();
                    nama_barang.clear();
                    jumlah_logistik.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getId().toString());
                        id_penyalur.add(response.body().getData().get(i).getRelawanId().toString());
                        posisi_logistik.add(response.body().getData().get(i).getNama());
                        gambar_logistik.add(response.body().getData().get(i).getFoto());
                        nama_barang.add(response.body().getData().get(i).getNamaBarang());
                        jumlah_logistik.add(response.body().getData().get(i).getJumlah());
                    }

                    adapterLogistik = new AdapterLogistik(activity, id, posisi_logistik, gambar_logistik, nama_barang, jumlah_logistik,
                            new AdapterLogistik.OnEditLocationListener() {
                                @Override
                                public void onClickAdapter(int position) {
                                    Intent intent = new Intent(context, PenyaluranLogistikActivity.class);
                                    intent.putExtra("id_master_logistik", id.get(position));
                                    intent.putExtra("penyalur", posisi_logistik.get(position));
                                    intent.putExtra("id_penyalur", id_penyalur.get(position));
                                    startActivityForResult(intent, 0);
                                }
                            });
                    adapterLogistik.notifyDataSetChanged();
                    list_logistik.setAdapter(adapterLogistik);
                    loaderUi2.dismiss();
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MasterLogistik>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 1) {
                getData();
            }
        }
    }
}