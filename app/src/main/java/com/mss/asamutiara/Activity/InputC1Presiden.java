package com.mss.asamutiara.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
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
import com.mss.asamutiara.Table.CalonPresiden;
import com.mss.asamutiara.Table.DataInduk;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputC1Presiden extends AppCompatActivity {

    EditText surat_guna, surat_tdk_guna, surat_rusak;
    EditText surat_sah_parpol, surat_tdk_sah_parpol;
    AppCompatButton btn_simpan;
    RelativeLayout refresh;

    Activity activity;
    Context context;
    Session session;
    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;
    Api api;
    Call<BaseResponse<CalonPresiden>> callCalonPresiden;
    Call<BaseResponse<DataInduk>> callDataInduk;
    Call<BaseResponse> callSimpanData;

    RecyclerView recycle_calon;
    AdapterPresiden adapterPresiden;
    AppCompatImageView filter;
    FilterWilayah filterWilayah;

    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> id_calon = new ArrayList<String>();
    private ArrayList<String> nomor_urut = new ArrayList<String>();
    private ArrayList<String> nama_paslon = new ArrayList<String>();
    private ArrayList<String> gambar_paslon = new ArrayList<String>();
    private ArrayList<String> suara_paslon = new ArrayList<String>();

    String jml_surat, jml_surat_kembali, jml_surat_tdk_digunakan, jml_surat_digunakan,
            jml_surat_sah, jml_surat_tdk_sah, jml_surat_sah_dan_tdk_sah;
    TextView title_tps_active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_c1_presiden);

        activity = this;
        context = this;

        session = new Session(context);
        loaderUi = new LoaderUi(context, activity);
        loaderUi2 = new LoaderUi2(context, activity);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        recycle_calon = findViewById(R.id.recycle_calon);
        surat_guna = (EditText) findViewById(R.id.surat_guna);
        surat_tdk_guna = (EditText) findViewById(R.id.surat_tdk_guna);
        surat_rusak = (EditText) findViewById(R.id.surat_rusak);

        surat_sah_parpol = (EditText) findViewById(R.id.suara_sah_parpol);
        surat_tdk_sah_parpol = (EditText) findViewById(R.id.suara_tidak_sah);

        btn_simpan = findViewById(R.id.btn_simpan);
        refresh = findViewById(R.id.refresh);
        title_tps_active = findViewById(R.id.title_tps_active);
        filter = findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterWilayah = new FilterWilayah(context, activity, new FilterWilayah.OnEditLocationListener() {
                    @Override
                    public void onClickAdapter(String tps_id, String tps) {
                        if (!session.getNamaTpsActive().equals("")) {
                            title_tps_active.setText(session.getNamaTpsActive());
                        } else {
                            title_tps_active.setText(tps);
                        }
                        getData();
                    }
                });
                filterWilayah.show();
            }
        });

        if (!session.getNamaTpsActive().equals("")) {
            title_tps_active.setText(session.getNamaTpsActive());
        }

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                getCalonPresiden();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaderUi.show();
                insertSuaraPresiden();
            }
        });

        getData();
        getCalonPresiden();

    }

    public void getData() {
        loaderUi2.show();
        callDataInduk = api.getDataInduk(session.getTpsActive());
        callDataInduk.enqueue(new Callback<BaseResponse<DataInduk>>() {
            @Override
            public void onResponse(Call<BaseResponse<DataInduk>> call, Response<BaseResponse<DataInduk>> response) {
                if (response.isSuccessful()) {
                    surat_rusak.setText(response.body().getData().get(0).getJmlSuratKembaliPres().toString());
                    surat_tdk_guna.setText(response.body().getData().get(0).getJmlSuratTidakDigunakanPres().toString());
                    surat_guna.setText(response.body().getData().get(0).getJmlSuratDigunakanPres().toString());

                    surat_sah_parpol.setText(response.body().getData().get(0).getJmlSuratSahPres().toString());
                    surat_tdk_sah_parpol.setText(response.body().getData().get(0).getJmlSuratTidakSahPres().toString());
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

    public void getCalonPresiden() {
        loaderUi2.show();
        callCalonPresiden = api.getCalonPresiden(session.getTpsActive());
        callCalonPresiden.enqueue(new Callback<BaseResponse<CalonPresiden>>() {
            @Override
            public void onResponse(Call<BaseResponse<CalonPresiden>> call, Response<BaseResponse<CalonPresiden>> response) {
                if (response.isSuccessful()) {
                    id.clear();
                    id_calon.clear();
                    nomor_urut.clear();
                    nama_paslon.clear();
                    gambar_paslon.clear();
                    suara_paslon.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getId().toString());
                        id_calon.add(response.body().getData().get(i).getIdCalon().toString());
                        nomor_urut.add(response.body().getData().get(i).getNomerUrut().toString());
                        nama_paslon.add(response.body().getData().get(i).getNamaCalon());
                        gambar_paslon.add(response.body().getData().get(i).getGambarCalon());
                        suara_paslon.add(response.body().getData().get(i).getJumlahSuara().toString());
                    }

                    adapterPresiden = new AdapterPresiden(context, activity, id_calon, nomor_urut, nama_paslon, gambar_paslon, suara_paslon, new MyListener() {
                        @Override
                        public void addValue(String s, int position) {
                            suara_paslon.set(position, s);
                        }
                    });
                    recycle_calon.setLayoutManager(new GridLayoutManager(context, 2));
                    recycle_calon.setAdapter(adapterPresiden);
                    loaderUi2.dismiss();
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CalonPresiden>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertSuaraPresiden() {
        String tmp_id_calon = TextUtils.join(";", id_calon);
        String suara_calon = TextUtils.join(";", suara_paslon);

        jml_surat_kembali = !TextUtils.isEmpty(surat_rusak.getText().toString()) ? surat_rusak.getText().toString() : "0";
        jml_surat_tdk_digunakan = !TextUtils.isEmpty(surat_tdk_guna.getText().toString()) ? surat_tdk_guna.getText().toString() : "0";
        jml_surat_digunakan = !TextUtils.isEmpty(surat_guna.getText().toString()) ? surat_guna.getText().toString() : "0";
        jml_surat = (Integer.parseInt(jml_surat_kembali) + Integer.parseInt(jml_surat_tdk_digunakan) + Integer.parseInt(jml_surat_digunakan)) + "";
        jml_surat_sah = !TextUtils.isEmpty(surat_sah_parpol.getText().toString()) ? surat_sah_parpol.getText().toString() : "0";
        jml_surat_tdk_sah = !TextUtils.isEmpty(surat_tdk_sah_parpol.getText().toString()) ? surat_tdk_sah_parpol.getText().toString() : "0";
        jml_surat_sah_dan_tdk_sah = (Integer.parseInt(jml_surat_sah) + Integer.parseInt(jml_surat_tdk_sah)) + "";

        callSimpanData = api.updateSuaraPresiden(session.getTpsActive(),
                jml_surat+"", jml_surat_kembali+"", jml_surat_tdk_digunakan+"",
                jml_surat_digunakan+"", jml_surat_sah+"", jml_surat_tdk_sah+"",
                jml_surat_sah_dan_tdk_sah+"", tmp_id_calon+"", suara_calon+"");
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
}