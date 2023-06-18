package com.mss.asamutiara.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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
import android.view.View;
import android.widget.ImageView;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputBerkasC1 extends AppCompatActivity {

    Activity activity;
    Context context;
    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;
    Session session;
    Api api;
    Call<BaseResponse> callInsertGambarC1;

    String base64Photo = "", id_tps = "";
    ImageView gambar_c1;
    AppCompatButton btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_berkas_c1);

        gambar_c1 = findViewById(R.id.gambar_c1);
        btn_simpan = findViewById(R.id.btn_simpan);

        activity = this;
        context = this;
        loaderUi = new LoaderUi(context, activity);
        loaderUi2 = new LoaderUi2(context, activity);
        session = new Session(context);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        gambar_c1.setOnClickListener(new View.OnClickListener() {
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
                insertBerkasC1();
            }
        });
    }

    public void insertBerkasC1() {
        loaderUi.show();
//        Log.d("TAG", "insertDetailLogistik: "+base64Photo);
        if (base64Photo == "") {
            loaderUi.dismiss();
            Toast.makeText(context, "Silahkan masukkan gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else {
//            Log.d("TAG", "insertDetailLogistik: "+id_master+""+ id_penyalur+""+
//                    list_id.get(penerima.getSelectedItemPosition())+"");
            callInsertGambarC1 = api.insertBerkasC1(id_tps+"",  base64Photo+"");
            callInsertGambarC1.enqueue(new Callback<BaseResponse>() {
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
                gambar_c1.setImageBitmap(photo);
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