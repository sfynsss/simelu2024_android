package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputC1Induk extends AppCompatActivity {

    EditText dpt, dptb, dpk, dpktb, jum;
    EditText p_dpt, p_dptb, p_dpk, p_dpktb, p_jum;
    AppCompatButton btn_simpan;
    RelativeLayout refresh;

    Context context;
    Activity activity;
    FilterWilayah filterWilayah;

    Session session;
    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;
    Api api;
    Call<BaseResponse<DataInduk>> callDataInduk;
    Call<BaseResponse> callSimpanData;

    AppCompatImageView filter;
    TextView title_tps_active;

    int jum_tot_dpt = 0 , jum_tot_pengguna = 0;
    String tmp_dpt = "0", tmp_dptb = "0", tmp_dpk = "0", tmp_dpktb = "0",
            tmp_jum_dp = "0", tmp_p_dpt = "0", tmp_p_dptb = "0", tmp_p_dpk = "0",
            tmp_p_dpktb = "0", tmp_jum_p_dp = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_c1_induk);

        context = this;
        activity = this;

        session = new Session(this);
        loaderUi = new LoaderUi(this, this);
        loaderUi2 = new LoaderUi2(this, this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        dpt = (EditText) findViewById(R.id.dpt);
        dptb = (EditText) findViewById(R.id.dptb);
        dpk = (EditText) findViewById(R.id.dpk);
        dpktb = (EditText) findViewById(R.id.dpktb);
        jum = (EditText) findViewById(R.id.jum_dpt);

        p_dpt = (EditText) findViewById(R.id.p_dpt);
        p_dptb = (EditText) findViewById(R.id.p_dptb);
        p_dpk = (EditText) findViewById(R.id.p_dpk);
        p_dpktb = (EditText) findViewById(R.id.p_dpktb);
        p_jum = (EditText) findViewById(R.id.jum_p_dpt);

        btn_simpan = findViewById(R.id.simpan_data_induk);
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
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        getData();

        //Auto jumlah
        dpt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dpt.getText().toString().equals("") || dptb.getText().toString().equals("")
                        || dpk.getText().toString().equals("") || dpktb.getText().toString().equals("")) {

                } else {
                    jum_tot_dpt = Integer.parseInt(dpt.getText().toString()) + Integer.parseInt(dptb.getText().toString())
                            + Integer.parseInt(dpk.getText().toString()) + Integer.parseInt(dpktb.getText().toString());
                    jum.setText(jum_tot_dpt+"");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dptb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dpt.getText().toString().equals("") || dptb.getText().toString().equals("")
                        || dpk.getText().toString().equals("") || dpktb.getText().toString().equals("")) {

                } else {
                    jum_tot_dpt = Integer.parseInt(dpt.getText().toString()) + Integer.parseInt(dptb.getText().toString())
                            + Integer.parseInt(dpk.getText().toString()) + Integer.parseInt(dpktb.getText().toString());
                    jum.setText(jum_tot_dpt+"");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dpk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dpt.getText().toString().equals("") || dptb.getText().toString().equals("")
                        || dpk.getText().toString().equals("") || dpktb.getText().toString().equals("")) {

                } else {
                    jum_tot_dpt = Integer.parseInt(dpt.getText().toString()) + Integer.parseInt(dptb.getText().toString())
                            + Integer.parseInt(dpk.getText().toString()) + Integer.parseInt(dpktb.getText().toString());
                    jum.setText(jum_tot_dpt+"");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Auto jumlah
        p_dpt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (p_dpt.getText().toString().equals("") || p_dptb.getText().toString().equals("")
                        || p_dpk.getText().toString().equals("") || p_dpktb.getText().toString().equals("")) {

                } else {
                    jum_tot_pengguna = Integer.parseInt(p_dpt.getText().toString()) + Integer.parseInt(p_dptb.getText().toString())
                            + Integer.parseInt(p_dpk.getText().toString()) + Integer.parseInt(p_dpktb.getText().toString());
                    p_jum.setText(jum_tot_pengguna+"");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        p_dptb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (p_dpt.getText().toString().equals("") || p_dptb.getText().toString().equals("")
                        || p_dpk.getText().toString().equals("") || p_dpktb.getText().toString().equals("")) {

                } else {
                    jum_tot_pengguna = Integer.parseInt(p_dpt.getText().toString()) + Integer.parseInt(p_dptb.getText().toString())
                            + Integer.parseInt(p_dpk.getText().toString()) + Integer.parseInt(p_dpktb.getText().toString());
                    p_jum.setText(jum_tot_pengguna+"");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        p_dpk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (p_dpt.getText().toString().equals("") || p_dptb.getText().toString().equals("")
                        || p_dpk.getText().toString().equals("") || p_dpktb.getText().toString().equals("")) {

                } else {
                    jum_tot_pengguna = Integer.parseInt(p_dpt.getText().toString()) + Integer.parseInt(p_dptb.getText().toString())
                            + Integer.parseInt(p_dpk.getText().toString()) + Integer.parseInt(p_dpktb.getText().toString());
                    p_jum.setText(jum_tot_pengguna+"");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getData() {
        loaderUi2.show();
        callDataInduk = api.getDataInduk(session.getTpsActive());
        callDataInduk.enqueue(new Callback<BaseResponse<DataInduk>>() {
            @Override
            public void onResponse(Call<BaseResponse<DataInduk>> call, Response<BaseResponse<DataInduk>> response) {
                if (response.isSuccessful()) {
                    dpt.setText(response.body().getData().get(0).getDpt().toString());
                    dptb.setText(response.body().getData().get(0).getDPTb().toString());
                    dpk.setText(response.body().getData().get(0).getDpk().toString());
                    dpktb.setText(response.body().getData().get(0).getDPKTb().toString());
                    jum.setText(response.body().getData().get(0).getJumDp().toString());

                    p_dpt.setText(response.body().getData().get(0).getPenggunaDpt().toString());
                    p_dptb.setText(response.body().getData().get(0).getPENGGUNADPTb().toString());
                    p_dpk.setText(response.body().getData().get(0).getPenggunaDpk().toString());
                    p_dpktb.setText(response.body().getData().get(0).getPENGGUNADPKTb().toString());
                    p_jum.setText(response.body().getData().get(0).getJumPengguna().toString());
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

    public void insertData() {
        loaderUi.show();
        tmp_dpt = !TextUtils.isEmpty(dpt.getText().toString()) ? dpt.getText().toString() : "0";
        tmp_dptb = !TextUtils.isEmpty(dptb.getText().toString()) ? dptb.getText().toString() : "0";
        tmp_dpk = !TextUtils.isEmpty(dpk.getText().toString()) ? dpk.getText().toString() : "0";
        tmp_dpktb = !TextUtils.isEmpty(dpktb.getText().toString()) ? dpktb.getText().toString() : "0";
        tmp_jum_dp = !TextUtils.isEmpty(jum.getText().toString()) ? jum.getText().toString() : "0";

        tmp_p_dpt = !TextUtils.isEmpty(p_dpt.getText().toString()) ? p_dpt.getText().toString() : "0";
        tmp_p_dptb = !TextUtils.isEmpty(p_dptb.getText().toString()) ? p_dptb.getText().toString() : "0";
        tmp_p_dpk = !TextUtils.isEmpty(p_dpk.getText().toString()) ? p_dpk.getText().toString() : "0";
        tmp_p_dpktb = !TextUtils.isEmpty(p_dpktb.getText().toString()) ? p_dpktb.getText().toString() : "0";
        tmp_jum_p_dp = !TextUtils.isEmpty(p_jum.getText().toString()) ? p_jum.getText().toString() : "0";

        callSimpanData = api.storeDataInduk(session.getTpsActive(), tmp_dpt, tmp_dptb, tmp_dpk,
                tmp_dpktb, tmp_jum_dp, tmp_p_dpt, tmp_p_dptb, tmp_p_dpk,tmp_p_dpktb, tmp_jum_p_dp);
        callSimpanData.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, retrofit2.Response<BaseResponse> response) {
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