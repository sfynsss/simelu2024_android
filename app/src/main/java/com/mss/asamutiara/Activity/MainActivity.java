package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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

public class MainActivity extends AppCompatActivity {

    Session session;
    Api api;
    Call<BaseResponse<CekSession>> cekSession;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpNavigation();

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        cekSessionLogin();
    }

    public void setUpNavigation(){
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setItemIconTintList(null);
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

    public void cekSessionLogin(){
        cekSession = api.cekSession();
        cekSession.enqueue(new Callback<BaseResponse<CekSession>>() {
            @Override
            public void onResponse(Call<BaseResponse<CekSession>> call, Response<BaseResponse<CekSession>> response) {
                if (response.isSuccessful()) {
                    System.out.println("cek_session");
                    session.setUserStatus(true, response.body().getData().get(0).getId().toString(),
                            response.body().getData().get(0).getNik(),
                            response.body().getData().get(0).getNama(),
                            response.body().getData().get(0).getApiToken(),
                            response.body().getData().get(0).getTarget().toString(),
                            response.body().getData().get(0).getHierarkiId()+"",
                            response.body().getData().get(0).getNamaHierarki(),
                            response.body().getData().get(0).getNamaCalon(),
                            response.body().getData().get(0).getDesa(),
                            response.body().getData().get(0).getKecamatan(),
                            response.body().getData().get(0).getKabupaten(),
                            response.body().getData().get(0).getProvinsiId());
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(MainActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                    session.setUserStatus(false, "","", "", "", "",
                            "", "", "", "", "", "", "");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CekSession>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}