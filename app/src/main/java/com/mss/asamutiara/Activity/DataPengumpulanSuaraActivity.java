package com.mss.asamutiara.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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
import com.mss.asamutiara.Table.Suara;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPengumpulanSuaraActivity extends AppCompatActivity {

    LoaderUi2 loaderUi2;
    Session session;
    Api api;
    Call<BaseResponse<Suara>> callSuara;
    AdapterSuara adapterSuara;

    ListView list_suara;
    FloatingActionButton btn_tambah;

    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> nik = new ArrayList<>();
    ArrayList<String> nama = new ArrayList<>();
    ArrayList<String> no_telp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengumpulan_suara);

        loaderUi2 = new LoaderUi2(this, this);
        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        list_suara = findViewById(R.id.list_suara);
        btn_tambah = findViewById(R.id.btn_tambah);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataPengumpulanSuaraActivity.this, InputSuaraActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        getDataSuara();
    }

    public void getDataSuara() {
        loaderUi2.show();
        callSuara = api.getSuara();
        callSuara.enqueue(new Callback<BaseResponse<Suara>>() {
            @Override
            public void onResponse(Call<BaseResponse<Suara>> call, Response<BaseResponse<Suara>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    id.clear();
                    nik.clear();
                    nama.clear();
                    no_telp.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getId().toString());
                        nik.add(response.body().getData().get(i).getNik());
                        nama.add(response.body().getData().get(i).getNama());
                        no_telp.add(response.body().getData().get(i).getNoTelp());
                    }

                    adapterSuara = new AdapterSuara(DataPengumpulanSuaraActivity.this, id, nik, nama, no_telp);
                    adapterSuara.notifyDataSetChanged();
                    list_suara.setAdapter(adapterSuara);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(DataPengumpulanSuaraActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Suara>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(DataPengumpulanSuaraActivity.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 1) {
                getDataSuara();
            }
        }
    }
}