package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.Caleg;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputC1Caleg extends AppCompatActivity {

    Context context;
    Activity activity;
    Session session;
    Api api;
    RequestOptions option;

    AdapterCaleg adapterCaleg;
    Call<BaseResponse<Caleg>> callGetCaleg;
    Call<BaseResponse> callInsertSuaraCaleg;

    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;

    TextView nama_partai;
    EditText suara_partai;
    ImageView gambar_partai;
    RecyclerView daftar_caleg;
    CardView kontainer;
    AppCompatButton btn_simpan;
    RelativeLayout refresh;

    String tmp_kategori, partai_id;
    private ArrayList<String> id = new ArrayList<String>();
    private ArrayList<String> nomor_urut = new ArrayList<String>();
    private ArrayList<String> nama_caleg = new ArrayList<String>();
    private ArrayList<String> suara_caleg = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_c1_caleg);

        context = this;
        activity = this;
        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        option = new RequestOptions().placeholder(R.drawable.ic_hourglass_empty_24).error(R.drawable.ic_highlight_off_24);

        loaderUi = new LoaderUi(context, activity);
        loaderUi2 = new LoaderUi2(context, activity);

        nama_partai = findViewById(R.id.nama_partai);
        suara_partai = findViewById(R.id.suara_sah_parpol);
        gambar_partai = findViewById(R.id.gambar_partai);
        daftar_caleg = findViewById(R.id.daftar_caleg);
        kontainer = findViewById(R.id.kontainer);
        btn_simpan = findViewById(R.id.btn_simpan);
        refresh = findViewById(R.id.refresh);

        nama_partai.setText(getIntent().getStringExtra("partai"));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.signature(
                new ObjectKey(String.valueOf(System.currentTimeMillis())));
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load("http://" + session.getBaseUrl() + "/storage/upload/partai/logo/"+ getIntent().getStringExtra("id_partai")
                        +"/" + getIntent().getStringExtra("gambar_partai") + "").into(gambar_partai);
        suara_partai.setText(getIntent().getStringExtra("suara_partai"));

        tmp_kategori = getIntent().getStringExtra("kategori");
        partai_id = getIntent().getStringExtra("id_partai");
        getCaleg();

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCaleg();
            }
        });
    }

    public void getCaleg() {
        loaderUi2.show();
        String wilayah = "";
        if (tmp_kategori.equals("1")) {
            wilayah = session.getProvinsiId();
        } else if (tmp_kategori.equals("2")) {
            wilayah = session.getKabupatenActive();
        } else if (tmp_kategori.equals("3")) {
            wilayah = session.getKecamatanActive();
        }

        Log.d("TAG", "getCaleg: "+tmp_kategori+" | "+ wilayah+" | "+ session.getTpsActive()+" | "+ partai_id+"");
        callGetCaleg = api.getCaleg(tmp_kategori+"", wilayah+"", session.getTpsActive()+"", partai_id+"");
        callGetCaleg.enqueue(new Callback<BaseResponse<Caleg>>() {
            @Override
            public void onResponse(Call<BaseResponse<Caleg>> call, Response<BaseResponse<Caleg>> response) {
                if (response.isSuccessful()) {
                    id.clear();
                    nomor_urut.clear();
                    nama_caleg.clear();
                    suara_caleg.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getIdCalon().toString());
                        nomor_urut.add(response.body().getData().get(i).getNomerUrut().toString());
                        nama_caleg.add(response.body().getData().get(i).getNamaCalon());
                        suara_caleg.add(response.body().getData().get(i).getJumlahSuara().toString());
                    }

                    adapterCaleg = new AdapterCaleg(context, activity, id, nomor_urut, nama_caleg, suara_caleg, new MyListener() {
                        @Override
                        public void addValue(String s, int position) {
                            suara_caleg.set(position, s);
                        }
                    });
                    adapterCaleg.notifyDataSetChanged();
                    daftar_caleg.setLayoutManager(new GridLayoutManager(context, 1));
                    daftar_caleg.setAdapter(adapterCaleg);
                    kontainer.setVisibility(View.VISIBLE);
                    loaderUi2.dismiss();
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Caleg>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertData() {
        loaderUi.show();
        String det_id_calon = id.size() > 0 ? TextUtils.join(";", id) : "";
        String det_suara_calon = suara_caleg.size() > 0 ? TextUtils.join(";", suara_caleg) : "";
        String det_suara_partai = !TextUtils.isEmpty(suara_partai.getText().toString()) ? suara_partai.getText().toString() : "0";
        Log.d("TAG", "Det Suara Calon: "+det_suara_calon);

        String wilayah = "";
        if (tmp_kategori.equals("1")) {
            wilayah = session.getProvinsiId();
        } else if (tmp_kategori.equals("2")) {
            wilayah = session.getKabupatenActive();
        } else if (tmp_kategori.equals("3")) {
            wilayah = session.getKecamatanActive();
        }

        Log.d("TAG", "insertData: "+tmp_kategori+" | "+ session.getTpsActive()+" | "+
                partai_id+" | "+ det_suara_partai+" | "+ det_id_calon+" | "+det_suara_calon);
        callInsertSuaraCaleg = api.insertSuaraCaleg(tmp_kategori+"", session.getTpsActive()+"",
                partai_id+"", det_suara_partai+"", det_id_calon+"", det_suara_calon+"",
                wilayah+"", session.getDesaActive()+"");
        callInsertSuaraCaleg.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    loaderUi.dismiss();
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    setResult(1);
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