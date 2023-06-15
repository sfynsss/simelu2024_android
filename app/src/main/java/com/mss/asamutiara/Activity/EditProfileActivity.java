package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    EditText password_pengguna;
    Button btn_browse, btn_simpan;

    LoaderUi loaderUi;
    Session session;
    Api api;

    String kataSandi;

    Call<BaseResponse> ubahPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        password_pengguna = findViewById(R.id.password_pengguna);
        btn_simpan = findViewById(R.id.btn_simpan);

        loaderUi = new LoaderUi(this, this);
        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaderUi.show();
                kataSandi = password_pengguna.getText().toString();
                ubahPassword = api.ubahPassword(kataSandi);
                ubahPassword.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            loaderUi.dismiss();
                            Toast.makeText(EditProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                        } else {
                            loaderUi.dismiss();
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(EditProfileActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        loaderUi.dismiss();
                        Toast.makeText(EditProfileActivity.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}