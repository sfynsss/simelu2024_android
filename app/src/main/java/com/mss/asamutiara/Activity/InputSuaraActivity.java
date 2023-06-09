package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.CekSession;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputSuaraActivity extends AppCompatActivity {

    Session session;
    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;
    Api api;
    Call<BaseResponse> callInputSuara;
    Call<BaseResponse<CekSession>> cekSession;

    EditText nama, nik, no_telp;
    AppCompatButton btn_simpan;

    String tmp_relawan_id = "", tmp_tps_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_suara);

        session = new Session(this);
        loaderUi = new LoaderUi(this, this);
        loaderUi2 = new LoaderUi2(this, this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        nama = findViewById(R.id.nama);
        nik = findViewById(R.id.nik);
        no_telp = findViewById(R.id.no_telp);
        btn_simpan = findViewById(R.id.btn_simpan);

        loaderUi2.show();
        getUser();

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaderUi.show();
                insertSuara();
            }
        });
    }

    public void getUser() {
        cekSession = api.cekSession();
        cekSession.enqueue(new Callback<BaseResponse<CekSession>>() {
            @Override
            public void onResponse(Call<BaseResponse<CekSession>> call, Response<BaseResponse<CekSession>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    tmp_relawan_id = !TextUtils.isEmpty(response.body().getData().get(0).getId().toString()) ?
                            response.body().getData().get(0).getId().toString() : "";
                    tmp_tps_id = !TextUtils.isEmpty(response.body().getData().get(0).getTpsId()) ?
                            response.body().getData().get(0).getTpsId() : "";
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputSuaraActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CekSession>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(InputSuaraActivity.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertSuara() {
        String tmp_nama = nama.getText().toString();
        String tmp_nik = nik.getText().toString();
        String tmp_no_telp = no_telp.getText().toString();
        callInputSuara = api.createSuara(tmp_nik+"", tmp_nama+"", tmp_tps_id+"",
                tmp_relawan_id+"", tmp_no_telp+"");
        callInputSuara.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    loaderUi.dismiss();
                    Toast.makeText(InputSuaraActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    setResult(1);
                    finish();
                } else {
                    loaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputSuaraActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                loaderUi.dismiss();
                Toast.makeText(InputSuaraActivity.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}