package com.mss.asamutiara.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.Relawan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRelawanActivity extends AppCompatActivity {

    FloatingActionButton btn_tambah;
    Session session;
    LoaderUi2 loaderUi2;
    Api api;
    Call<BaseResponse<Relawan>> getRelawan;

    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> hierarki = new ArrayList<>();
    ArrayList<String> nama = new ArrayList<>();
    ArrayList<String> no_telp = new ArrayList<>();
    ArrayList<String> target = new ArrayList<>();
    ArrayList<String> persentase = new ArrayList<>();
    ArrayList<String> terpenuhi = new ArrayList<>();
    ArrayList<String> kekurangan_target = new ArrayList<>();

    AdapterRelawan adapterRelawan;
    ListView list_relawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_relawan);

        session = new Session(this);
        loaderUi2 = new LoaderUi2(this, this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_tambah = findViewById(R.id.btn_tambah);
        list_relawan = findViewById(R.id.list_relawan);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataRelawanActivity.this, input_data_relawan.class);
                startActivityForResult(intent, 0);
            }
        });

        loaderUi2.show();
        getDataRelawan();
    }

    public void getDataRelawan() {
        getRelawan = api.getRelawan();
        getRelawan.enqueue(new Callback<BaseResponse<Relawan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Relawan>> call, Response<BaseResponse<Relawan>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    id.clear();
                    hierarki.clear();
                    nama.clear();
                    no_telp.clear();
                    target.clear();
                    persentase.clear();
                    terpenuhi.clear();
                    kekurangan_target.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getId().toString());
                        hierarki.add(response.body().getData().get(i).getNamaHierarki());
                        nama.add(response.body().getData().get(i).getNama());
                        no_telp.add(response.body().getData().get(i).getNoTelp());
                        target.add(response.body().getData().get(i).getTarget().toString());
                        persentase.add("0");
                        terpenuhi.add("0");
                        kekurangan_target.add("0");
                    }

                    adapterRelawan = new AdapterRelawan(DataRelawanActivity.this, id, hierarki, nama,
                            no_telp, target, persentase, terpenuhi, kekurangan_target);
                    adapterRelawan.notifyDataSetChanged();
                    list_relawan.setAdapter(adapterRelawan);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(DataRelawanActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Relawan>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(DataRelawanActivity.this, "Error disini, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 1) {
                loaderUi2.show();
                getDataRelawan();
            }
        }
    }
}