package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.CekSession;
import com.mss.asamutiara.Table.DapilKabupaten;
import com.mss.asamutiara.Table.Desa;
import com.mss.asamutiara.Table.Hierarki;
import com.mss.asamutiara.Table.Kabupaten;
import com.mss.asamutiara.Table.Kecamatan;
import com.mss.asamutiara.Table.Tps;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputDataRelawan extends AppCompatActivity {

    Session session;
    LoaderUi LoaderUi;
    Api api;
    Call<BaseResponse<Hierarki>> callHierarki;
    Call<BaseResponse<Kabupaten>> callKabupaten;
    Call<BaseResponse<DapilKabupaten>> callDapilKabupaten;
    Call<BaseResponse<Kecamatan>> callKecamatan;
    Call<BaseResponse<Desa>> callDesa;
    Call<BaseResponse<Tps>> callTps;
    Call<BaseResponse> callCreateRelawan;
    Call<BaseResponse<CekSession>> cekSession;

    Spinner hierarki, kabupaten, dapil_kabupaten, kecamatan, desa, tps;
    LinearLayoutCompat d_dapil_kabupaten, d_kabupaten, d_kecamatan, d_desa, d_tps;
    EditText nama_relawan, email_relawan, password_relawan, nik_relawan, no_telp_relawan,
    target_relawan;
    AppCompatButton btn_simpan;

    ArrayList<String> list_hierarki_id = new ArrayList<>();
    ArrayList<String> list_hierarki = new ArrayList<>();

    ArrayList<String> list_kabupaten_id = new ArrayList<>();
    ArrayList<String> list_kabupaten = new ArrayList<>();

    ArrayList<String> list_dapil_kabupaten_id = new ArrayList<>();
    ArrayList<String> list_dapil_kabupaten = new ArrayList<>();

    ArrayList<String> list_kecamatan_id = new ArrayList<>();
    ArrayList<String> list_kecamatan = new ArrayList<>();

    ArrayList<String> list_desa_id = new ArrayList<>();
    ArrayList<String> list_desa = new ArrayList<>();

    ArrayList<String> list_tps_id = new ArrayList<>();
    ArrayList<String> list_tps = new ArrayList<>();

    String tmp_relawan_id = "";
    String tmp_dapil_provinsi_id = "";
    String tmp_provinsi_id = "";
    String tmp_kabupaten_id = "";
    String tmp_dapil_kabupaten_id = "";
    String tmp_kecamatan_id = "";
    String tmp_desa_id = "";
    String tmp_tps_id = "";
    String tmp_calon_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_relawan);

        session = new Session(this);
        LoaderUi = new LoaderUi(this, this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        nama_relawan = findViewById(R.id.nama_relawan);
        email_relawan = findViewById(R.id.email_relawan);
        password_relawan = findViewById(R.id.password_relawan);
        nik_relawan = findViewById(R.id.nik_relawan);
        no_telp_relawan = findViewById(R.id.no_telp_relawan);
        target_relawan = findViewById(R.id.target_relawan);
        btn_simpan = findViewById(R.id.btn_simpan);

        hierarki = findViewById(R.id.hierarki);
        kabupaten = findViewById(R.id.kabupaten);
        dapil_kabupaten = findViewById(R.id.dapil_kabupaten);
        kecamatan = findViewById(R.id.kecamatan);
        desa = findViewById(R.id.desa);
        tps = findViewById(R.id.tps);
        d_dapil_kabupaten = findViewById(R.id.d_dapil_kabupaten);
        d_kabupaten = findViewById(R.id.d_kabupaten);
        d_kecamatan = findViewById(R.id.d_kecamatan);
        d_desa = findViewById(R.id.d_desa);
        d_tps = findViewById(R.id.d_tps);

        getUser();
        getHierarki(session.getHierarkiId());

        hierarki.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cekHierarki(list_hierarki_id.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaderUi.show();
                insertRelawan();
            }
        });
    }

    public void getUser() {
        LoaderUi.show();
        cekSession = api.cekSession();
        cekSession.enqueue(new Callback<BaseResponse<CekSession>>() {
            @Override
            public void onResponse(Call<BaseResponse<CekSession>> call, Response<BaseResponse<CekSession>> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    tmp_relawan_id = !TextUtils.isEmpty(response.body().getData().get(0).getId().toString()) ?
                            response.body().getData().get(0).getId().toString() : "";
                    tmp_dapil_provinsi_id = !TextUtils.isEmpty(response.body().getData().get(0).getDapilProvinsiId()) ?
                            response.body().getData().get(0).getDapilProvinsiId() : "";
                    tmp_provinsi_id = !TextUtils.isEmpty(response.body().getData().get(0).getProvinsiId()) ?
                            response.body().getData().get(0).getProvinsiId() : "";
                    tmp_kabupaten_id = !TextUtils.isEmpty(response.body().getData().get(0).getKabupatenId()) ?
                            response.body().getData().get(0).getKabupatenId() : "";
                    tmp_dapil_kabupaten_id = !TextUtils.isEmpty(response.body().getData().get(0).getDapilKabupatenId()) ?
                            response.body().getData().get(0).getDapilKabupatenId() : "";
                    tmp_kecamatan_id = !TextUtils.isEmpty(response.body().getData().get(0).getKecamatanId()) ?
                            response.body().getData().get(0).getKecamatanId() : "";
                    tmp_desa_id = !TextUtils.isEmpty(response.body().getData().get(0).getDesaId()) ?
                            response.body().getData().get(0).getDesaId() : "";
                    tmp_tps_id = !TextUtils.isEmpty(response.body().getData().get(0).getTpsId()) ?
                            response.body().getData().get(0).getTpsId() : "";
                    tmp_calon_id = !TextUtils.isEmpty(response.body().getData().get(0).getCalonId()) ?
                            response.body().getData().get(0).getCalonId() : "";
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CekSession>> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertRelawan(){
        String tmp_nama_relawan = nama_relawan.getText().toString();
        String tmp_email_relawan = email_relawan.getText().toString();
        String tmp_password_relawan = password_relawan.getText().toString();
        String tmp_nik_relawan = nik_relawan.getText().toString();
        String tmp_no_telp_relawan = no_telp_relawan.getText().toString();
        String tmp_target_relawan = target_relawan.getText().toString();

        String tmp_hierarki = list_hierarki_id.get(hierarki.getSelectedItemPosition());
        if (tmp_hierarki.equals("2")) {
            tmp_kabupaten_id = list_kabupaten_id.get(kabupaten.getSelectedItemPosition());
        } else if (tmp_hierarki.equals("3")) {
            tmp_dapil_kabupaten_id = list_dapil_kabupaten_id.get(dapil_kabupaten.getSelectedItemPosition());
        } else if (tmp_hierarki.equals("4")) {
            tmp_kecamatan_id = list_kecamatan_id.get(kecamatan.getSelectedItemPosition());
        } else if (tmp_hierarki.equals("5")) {
            tmp_desa_id = list_desa_id.get(desa.getSelectedItemPosition());
        } else if (tmp_hierarki.equals("6")) {
            tmp_tps_id = list_tps_id.get(tps.getSelectedItemPosition());
        }

        callCreateRelawan = api.createRelawan(tmp_nama_relawan+"", tmp_email_relawan+"",
                tmp_password_relawan+"", tmp_nik_relawan+"", tmp_dapil_provinsi_id,
                tmp_kabupaten_id+"", tmp_dapil_kabupaten_id+"", tmp_kecamatan_id+"",
                tmp_desa_id+"", tmp_tps_id+"", tmp_calon_id+"", tmp_no_telp_relawan+"",
                tmp_target_relawan+"", list_hierarki_id.get(hierarki.getSelectedItemPosition())+"",
                tmp_relawan_id+"");
        callCreateRelawan.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    Toast.makeText(InputDataRelawan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    setResult(1);
                    finish();
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getHierarki(String hierarki_id) {
        LoaderUi.show();
        callHierarki = api.getHierarki(hierarki_id);
        callHierarki.enqueue(new Callback<BaseResponse<Hierarki>>() {
            @Override
            public void onResponse(Call<BaseResponse<Hierarki>> call, Response<BaseResponse<Hierarki>> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    list_hierarki_id.clear();
                    list_hierarki.clear();

                    for (int i = 0; i < 1; i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_hierarki_id.add(response.body().getData().get(i).getId().toString());
                        list_hierarki.add(response.body().getData().get(i).getNamaHierarki());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InputDataRelawan.this, R.layout.spinner_layout, list_hierarki);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    hierarki.setAdapter(arrayAdapter);
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Hierarki>> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cekHierarki(String hierarki) {
        if (hierarki.equals("2")) {
            getKabupaten(session.getProvinsiId());
        } else if (hierarki.equals("3")) {
            getDapilKabupaten(tmp_kabupaten_id);
        } else if (hierarki.equals("4")) {
            getKecamatan(tmp_kabupaten_id);
        } else if (hierarki.equals("5")) {
            getDesa(tmp_kecamatan_id);
        } else if (hierarki.equals("6")) {
            getTps(tmp_desa_id);
        }
    }

    public void getKabupaten(String provinsi_id){
        LoaderUi.show();
        d_kabupaten.setVisibility(View.VISIBLE);
        callKabupaten = api.getKabupaten(provinsi_id);
        callKabupaten.enqueue(new Callback<BaseResponse<Kabupaten>>() {
            @Override
            public void onResponse(Call<BaseResponse<Kabupaten>> call, Response<BaseResponse<Kabupaten>> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    list_kabupaten_id.clear();
                    list_kabupaten.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_kabupaten_id.add(response.body().getData().get(i).getId().toString());
                        list_kabupaten.add(response.body().getData().get(i).getKabupaten());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InputDataRelawan.this, R.layout.spinner_layout, list_kabupaten);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    kabupaten.setAdapter(arrayAdapter);
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Kabupaten>> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDapilKabupaten(String kabupaten_id){
        LoaderUi.show();
        d_dapil_kabupaten.setVisibility(View.VISIBLE);
        callDapilKabupaten = api.getDapilKabupaten(kabupaten_id);
        callDapilKabupaten.enqueue(new Callback<BaseResponse<DapilKabupaten>>() {
            @Override
            public void onResponse(Call<BaseResponse<DapilKabupaten>> call, Response<BaseResponse<DapilKabupaten>> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    list_dapil_kabupaten_id.clear();
                    list_dapil_kabupaten.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_dapil_kabupaten_id.add(response.body().getData().get(i).getId().toString());
                        list_dapil_kabupaten.add(response.body().getData().get(i).getDapilKabupaten());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InputDataRelawan.this, R.layout.spinner_layout, list_dapil_kabupaten);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    dapil_kabupaten.setAdapter(arrayAdapter);
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<DapilKabupaten>> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getKecamatan(String kabupaten_id){
        LoaderUi.show();
        d_kecamatan.setVisibility(View.VISIBLE);
        callKecamatan = api.getKecamatan(kabupaten_id);
        callKecamatan.enqueue(new Callback<BaseResponse<Kecamatan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Kecamatan>> call, Response<BaseResponse<Kecamatan>> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    list_kecamatan_id.clear();
                    list_kecamatan.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_kecamatan_id.add(response.body().getData().get(i).getId().toString());
                        list_kecamatan.add(response.body().getData().get(i).getKecamatan());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InputDataRelawan.this, R.layout.spinner_layout, list_kecamatan);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    kecamatan.setAdapter(arrayAdapter);
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Kecamatan>> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDesa(String kecamatan_id){
        LoaderUi.show();
        d_desa.setVisibility(View.VISIBLE);
        callDesa = api.getDesa(kecamatan_id);
        callDesa.enqueue(new Callback<BaseResponse<Desa>>() {
            @Override
            public void onResponse(Call<BaseResponse<Desa>> call, Response<BaseResponse<Desa>> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    list_desa_id.clear();
                    list_desa.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_desa_id.add(response.body().getData().get(i).getId().toString());
                        list_desa.add(response.body().getData().get(i).getDesa());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InputDataRelawan.this, R.layout.spinner_layout, list_desa);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    desa.setAdapter(arrayAdapter);
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Desa>> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTps(String desa_id){
        LoaderUi.show();
        d_tps.setVisibility(View.VISIBLE);
        callTps = api.getTps(desa_id);
        callTps.enqueue(new Callback<BaseResponse<Tps>>() {
            @Override
            public void onResponse(Call<BaseResponse<Tps>> call, Response<BaseResponse<Tps>> response) {
                if (response.isSuccessful()) {
                    LoaderUi.dismiss();
                    list_tps_id.clear();
                    list_tps.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_tps_id.add(response.body().getData().get(i).getId().toString());
                        list_tps.add(response.body().getData().get(i).getDesa()+" "+response.body().getData().get(i).getNomerTps());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(InputDataRelawan.this, R.layout.spinner_layout, list_tps);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    tps.setAdapter(arrayAdapter);
                } else {
                    LoaderUi.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(InputDataRelawan.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Tps>> call, Throwable t) {
                LoaderUi.dismiss();
                Toast.makeText(InputDataRelawan.this, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}