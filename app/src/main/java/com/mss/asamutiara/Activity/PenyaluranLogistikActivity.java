package com.mss.asamutiara.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.Helpers.ApiError;
import com.mss.asamutiara.Helpers.ErrorUtils;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Session.Session;
import com.mss.asamutiara.Table.Relawan;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenyaluranLogistikActivity extends AppCompatActivity {

    Activity activity;
    Context context;
    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;
    Session session;
    Api api;
    Call<BaseResponse<Relawan>> callGetDataRelawan;
    Call<BaseResponse> callInsertDetailLogistik;

    TextView penyalur;
    AppCompatSpinner penerima;
    ImageView bukti_penerimaan;
    AppCompatButton btn_simpan;

    ArrayList<String> list_id = new ArrayList<>();
    ArrayList<String> list_relawan = new ArrayList<>();
    String base64Photo = "", id_master = "", id_penyalur = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyaluran_logistik);

        penyalur = findViewById(R.id.penyalur);
        penerima = findViewById(R.id.penerima);
        bukti_penerimaan = findViewById(R.id.bukti_penerimaan);
        btn_simpan = findViewById(R.id.btn_simpan);

        activity = this;
        context = this;
        loaderUi = new LoaderUi(context, activity);
        loaderUi2 = new LoaderUi2(context, activity);
        session = new Session(context);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        penyalur.setText(getIntent().getStringExtra("penyalur"));
        id_penyalur = getIntent().getStringExtra("id_penyalur");
        id_master = getIntent().getStringExtra("id_master_logistik");

        bukti_penerimaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent();
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 102);
                    } else {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDetailLogistik();
            }
        });

        getDataRelawan();
    }

    public void getDataRelawan() {
        loaderUi2.show();
        callGetDataRelawan = api.getRelawanPenerima();
        callGetDataRelawan.enqueue(new Callback<BaseResponse<Relawan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Relawan>> call, Response<BaseResponse<Relawan>> response) {
                if (response.isSuccessful()) {
                    list_id.clear();
                    list_relawan.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        list_id.add(response.body().getData().get(i).getId().toString());
                        list_relawan.add(response.body().getData().get(i).getNama());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_relawan);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    penerima.setAdapter(arrayAdapter);
                    loaderUi2.dismiss();
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Relawan>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void insertDetailLogistik() {
        loaderUi.show();
//        Log.d("TAG", "insertDetailLogistik: "+base64Photo);
        if (base64Photo == "") {
            loaderUi.dismiss();
            Toast.makeText(context, "Silahkan masukkan gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else {
//            Log.d("TAG", "insertDetailLogistik: "+id_master+""+ id_penyalur+""+
//                    list_id.get(penerima.getSelectedItemPosition())+"");
            callInsertDetailLogistik = api.insertDetailLogistik(id_master+"", id_penyalur+"",
                    list_id.get(penerima.getSelectedItemPosition())+"", base64Photo+"");
            callInsertDetailLogistik.enqueue(new Callback<BaseResponse>() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            if (resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                base64Photo = imageToString(photo);
                bukti_penerimaan.setImageBitmap(photo);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0) {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 102);
        } else {
            Toast.makeText(context, "Butuh akses kamera.", Toast.LENGTH_SHORT);
        }
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}