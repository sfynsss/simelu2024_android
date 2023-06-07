package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.UserResponse;
import com.mss.asamutiara.Session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    ImageView show_password;
    TextView lupa_password;
    EditText username, password;
    ProgressBar progress;
    Context context;
    Boolean showPasswordClicked = false;

    Api api;
    Session session;
    Call<UserResponse> login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;

        session = new Session(this);
        api = RetrofitClient.createService(Api.class);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        progress = findViewById(R.id.progress);
        show_password = findViewById(R.id.show_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                login = api.login(username.getText().toString(), password.getText().toString());
                login.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            session.setUserStatus(true, response.body().getUser().getId().toString(), response.body().getUser().getNik(), response.body().getUser().getNama(),
                                    response.body().getUser().getApiToken(), response.body().getUser().getTarget().toString(), response.body().getUser().getHierarkiId()+"",
                                    response.body().getUser().getNamaHierarki(), response.body().getUser().getNamaCalon(), response.body().getUser().getDesa(),
                                    response.body().getUser().getKecamatan(), response.body().getUser().getKabupaten(), response.body().getUser().getProvinsiId());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, "Selamat datang "+response.body().getUser().getNama(), Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                        } else {
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(LoginActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                    }
                });
            }
        });

        show_password.setBackgroundResource(R.drawable.ic_eye_open);
        show_password.setOnClickListener(mToggleShowPasswordButton);

        lupa_password = findViewById(R.id.lupa_password);
        lupa_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Untuk merubah password\nsilahkan menghubungi Admin!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    View.OnClickListener mToggleShowPasswordButton = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            // change your button background

            if(showPasswordClicked){
                v.setBackgroundResource(R.drawable.ic_eye_closed);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                v.setBackgroundResource(R.drawable.ic_eye_open);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            showPasswordClicked = !showPasswordClicked; // reverse
        }

    };
}