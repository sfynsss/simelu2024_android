package com.mss.asamutiara.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.DataInduk;
import com.mss.asamutiara.Table.Partai;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputC1Dpr extends AppCompatActivity {

    AppCompatButton btn_simpan;
    RelativeLayout refresh;

    Activity activity;
    Context context;
    Session session;
    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;
    Api api;
    Call<BaseResponse<DataInduk>> callDataInduk;
    Call<BaseResponse<Partai>> callDataPartai;
    Call<BaseResponse> callSimpanData;

    TextView header;
    EditText surat_guna, surat_tdk_guna, surat_rusak;
    EditText surat_sah_parpol, surat_tdk_sah_parpol;
    RecyclerView partai;
    AdapterPartai adapterPartai;

    String tmp_kategori;
    ArrayList<String> id_partai = new ArrayList<>();
    ArrayList<String> no_partai = new ArrayList<>();
    ArrayList<String> nm_partai = new ArrayList<>();
    ArrayList<String> nm_lengkap_partai = new ArrayList<>();
    ArrayList<String> img_path = new ArrayList<>();
    ArrayList<String> jml_suara = new ArrayList<>();
    String jml_surat, jml_surat_kembali, jml_surat_tdk_digunakan,
            jml_surat_digunakan, jml_surat_sah, jml_surat_tdk_sah,
            jml_surat_sah_dan_tdk_sah;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_c1_dpr);

        activity = this;
        context = this;

        session = new Session(context);
        loaderUi = new LoaderUi(context, activity);
        loaderUi2 = new LoaderUi2(context, activity);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_simpan = findViewById(R.id.btn_simpan);
        refresh = findViewById(R.id.refresh);
        header = findViewById(R.id.header);
        partai = findViewById(R.id.partai);

        surat_guna = (EditText) findViewById(R.id.surat_guna);
        surat_tdk_guna = (EditText) findViewById(R.id.surat_tdk_guna);
        surat_rusak = (EditText) findViewById(R.id.surat_rusak);

        surat_sah_parpol = (EditText) findViewById(R.id.suara_sah_parpol);
        surat_tdk_sah_parpol = (EditText) findViewById(R.id.suara_tidak_sah);

        tmp_kategori = getIntent().getStringExtra("kategori");

        if (tmp_kategori.equals("1")) {
            header.setText("INPUT C1 DPR RI");
        } else if (tmp_kategori.equals("2")) {
            header.setText("INPUT C1 DPRD. PROV "+session.getProvinsi());
        } else {
            header.setText("INPUT C1 DPRD. KAB "+session.getKabupaten());
        }

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                getPartai();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaderUi.show();
                insertData();
            }
        });

        getData();
        getPartai();
    }

    public void getData() {
        loaderUi2.show();
        callDataInduk = api.getDataInduk(session.getTpsActive());
        callDataInduk.enqueue(new Callback<BaseResponse<DataInduk>>() {
            @Override
            public void onResponse(Call<BaseResponse<DataInduk>> call, Response<BaseResponse<DataInduk>> response) {
                if (response.isSuccessful()) {
                    if (tmp_kategori.equals("1")) {
                        surat_rusak.setText(response.body().getData().get(0).getJmlSuratKembaliDpr().toString());
                        surat_tdk_guna.setText(response.body().getData().get(0).getJmlSuratTidakDigunakanDpr().toString());
                        surat_guna.setText(response.body().getData().get(0).getJmlSuratDigunakanDpr().toString());

                        surat_sah_parpol.setText(response.body().getData().get(0).getJmlSuratSahDpr().toString());
                        surat_tdk_sah_parpol.setText(response.body().getData().get(0).getJmlSuratTidakSahDpr().toString());
                    } else if (tmp_kategori.equals("2")) {
                        surat_rusak.setText(response.body().getData().get(0).getJmlSuratKembaliDprProv().toString());
                        surat_tdk_guna.setText(response.body().getData().get(0).getJmlSuratTidakDigunakanDprProv().toString());
                        surat_guna.setText(response.body().getData().get(0).getJmlSuratDigunakanDprProv().toString());

                        surat_sah_parpol.setText(response.body().getData().get(0).getJmlSuratSahDprProv().toString());
                        surat_tdk_sah_parpol.setText(response.body().getData().get(0).getJmlSuratTidakSahDprProv().toString());
                    } else {
                        surat_rusak.setText(response.body().getData().get(0).getJmlSuratKembaliDprKab().toString());
                        surat_tdk_guna.setText(response.body().getData().get(0).getJmlSuratTidakDigunakanDprKab().toString());
                        surat_guna.setText(response.body().getData().get(0).getJmlSuratDigunakanDprKab().toString());

                        surat_sah_parpol.setText(response.body().getData().get(0).getJmlSuratSahDprKab().toString());
                        surat_tdk_sah_parpol.setText(response.body().getData().get(0).getJmlSuratTidakSahDprKab().toString());
                    }
                    loaderUi2.dismiss();
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<DataInduk>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPartai() {
        loaderUi2.show();
        callDataPartai = api.getPartai(session.getTpsActive()+"", tmp_kategori+"");
        callDataPartai.enqueue(new Callback<BaseResponse<Partai>>() {
            @Override
            public void onResponse(Call<BaseResponse<Partai>> call, Response<BaseResponse<Partai>> response) {
                if (response.isSuccessful()) {
                    id_partai.clear();
                    no_partai.clear();
                    nm_partai.clear();
                    nm_lengkap_partai.clear();
                    img_path.clear();
                    jml_suara.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id_partai.add(response.body().getData().get(i).getId().toString());
                        no_partai.add(response.body().getData().get(i).getNomerUrut().toString());
                        nm_partai.add(response.body().getData().get(i).getSlug());
                        nm_lengkap_partai.add(response.body().getData().get(i).getNamaPartai());
                        img_path.add(response.body().getData().get(i).getLogo());
                        jml_suara.add(response.body().getData().get(i).getJumlahSuara().toString());
                    }

                    adapterPartai = new AdapterPartai(context, activity, id_partai, no_partai, nm_partai, img_path, jml_suara,
                            new AdapterPartai.OnEditLocationListener() {
                                @Override
                                public void onClickAdapter(int position) {
                                    Intent intent = new Intent(context, InputC1Caleg.class);
                                    intent.putExtra("tps", session.getTpsActive());
                                    intent.putExtra("kategori", tmp_kategori);
                                    intent.putExtra("id_partai", id_partai.get(position));
                                    intent.putExtra("partai", nm_lengkap_partai.get(position));
                                    intent.putExtra("gambar_partai", img_path.get(position));
                                    intent.putExtra("suara_partai", jml_suara.get(position));
                                    startActivityForResult(intent, 0);
                                }
                            });
                    adapterPartai.notifyDataSetChanged();
                    partai.setLayoutManager(new GridLayoutManager(context, 2));
                    partai.setAdapter(adapterPartai);
                    loaderUi2.dismiss();
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Partai>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertData() {
        jml_surat_kembali = !TextUtils.isEmpty(surat_rusak.getText().toString()) ? surat_rusak.getText().toString() : "0";
        jml_surat_tdk_digunakan = !TextUtils.isEmpty(surat_tdk_guna.getText().toString()) ? surat_tdk_guna.getText().toString() : "0";
        jml_surat_digunakan = !TextUtils.isEmpty(surat_guna.getText().toString()) ? surat_guna.getText().toString() : "0";
        jml_surat = (Integer.parseInt(jml_surat_kembali) + Integer.parseInt(jml_surat_tdk_digunakan) + Integer.parseInt(jml_surat_digunakan)) + "";
        jml_surat_sah = !TextUtils.isEmpty(surat_sah_parpol.getText().toString()) ? surat_sah_parpol.getText().toString() : "0";
        jml_surat_tdk_sah = !TextUtils.isEmpty(surat_tdk_sah_parpol.getText().toString()) ? surat_tdk_sah_parpol.getText().toString() : "0";
        jml_surat_sah_dan_tdk_sah = (Integer.parseInt(jml_surat_sah) + Integer.parseInt(jml_surat_tdk_sah)) + "";

        callSimpanData = api.storeDataIndukDpr(session.getTpsActive(), tmp_kategori, jml_surat, jml_surat_kembali, jml_surat_tdk_digunakan,
                jml_surat_digunakan, jml_surat_sah, jml_surat_tdk_sah, jml_surat_sah_dan_tdk_sah);
        callSimpanData.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    loaderUi.dismiss();
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    loaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                loaderUi.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 1) {
                getPartai();
            }
        }
    }
}