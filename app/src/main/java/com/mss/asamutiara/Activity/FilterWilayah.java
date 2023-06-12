package com.mss.asamutiara.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;

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
import com.mss.asamutiara.Table.Kabupaten;
import com.mss.asamutiara.Table.Kecamatan;
import com.mss.asamutiara.Table.Tps;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterWilayah {

    private Context context;
    private Activity activity;
    static Dialog dialog;
    LoaderUi2 loaderUi2;

    Session session;
    Api api;
    ImageView close;
    AppCompatButton terapkan, filter_provinsi;
    AppCompatSpinner filter_kabupaten, filter_kecamatan, filter_desa, filter_tps;

    Call<BaseResponse<Kabupaten>> callKabupaten;
    Call<BaseResponse<Kecamatan>> callKecamatan;
    Call<BaseResponse<Desa>> callDesa;
    Call<BaseResponse<Tps>> callTps;
    Call<BaseResponse<CekSession>> cekSession;

    ArrayList<String> list_kabupaten_id = new ArrayList<>();
    ArrayList<String> list_kabupaten = new ArrayList<>();

    ArrayList<String> list_kecamatan_id = new ArrayList<>();
    ArrayList<String> list_kecamatan = new ArrayList<>();

    ArrayList<String> list_desa_id = new ArrayList<>();
    ArrayList<String> list_desa = new ArrayList<>();

    ArrayList<String> list_tps_id = new ArrayList<>();
    ArrayList<String> list_tps = new ArrayList<>();

    String tmp_hierarki_id = "";
    String tmp_relawan_id = "";
    String tmp_dapil_provinsi_id = "";
    String tmp_provinsi_id = "";
    String tmp_kabupaten_id = "";
    String tmp_dapil_kabupaten_id = "";
    String tmp_kecamatan_id = "";
    String tmp_desa_id = "";
    String tmp_tps_id = "";

    public FilterWilayah(Context context, Activity activity) {
        session = new Session(context);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        loaderUi2 = new LoaderUi2(context, activity);

        dialog = new Dialog(context);
        dialog.setTitle("Gambar Barang");
        this.context = context;
        this.activity = activity;

        View v = activity.getLayoutInflater().inflate(R.layout.filter_wilayah, null);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.setContentView(v);
        close = v.findViewById(R.id.close);
        terapkan = v.findViewById(R.id.terapkan);
        filter_provinsi = v.findViewById(R.id.filter_provinsi);
        filter_kabupaten = v.findViewById(R.id.filter_kabupaten);
        filter_kecamatan = v.findViewById(R.id.filter_kecamatan);
        filter_desa = v.findViewById(R.id.filter_desa);
        filter_tps = v.findViewById(R.id.filter_tps);

        filter_provinsi.setText(session.getProvinsi());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        terapkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setWilayah(tmp_provinsi_id+"", tmp_kabupaten_id+"",
                        list_kecamatan_id.get(filter_kecamatan.getSelectedItemPosition())+"",
                        list_desa_id.get(filter_desa.getSelectedItemPosition())+"",
                        list_tps_id.get(filter_tps.getSelectedItemPosition())+"");
                dialog.dismiss();
            }
        });

        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        option();
    }

    public void show() {
        if (!activity.isFinishing() && dialog != null) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (!activity.isFinishing() && dialog != null) {
            dialog.dismiss();
        }
    }

    public void option() {
        if (session.getHierarkiId().equals("1")) {
            getKabupaten(session.getProvinsiId());
            filter_kabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getKecamatan(list_kabupaten_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filter_kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getDesa(list_kecamatan_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filter_desa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getTps(list_desa_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else if (session.getHierarkiId().equals("2")) {
            getKabupatenByUser();
            filter_kabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getKecamatan(list_kabupaten_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filter_kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getDesa(list_kecamatan_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filter_desa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getTps(list_desa_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else if (session.getHierarkiId().equals("3")) {
            getKabupatenByUser();
            filter_kabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getKecamatan(list_kabupaten_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filter_kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getDesa(list_kecamatan_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filter_desa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getTps(list_desa_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else if (session.getHierarkiId().equals("4")) {
            getKabupatenByUser();
            getKecamatanByUser();

            filter_kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getDesa(list_kecamatan_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            filter_desa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getTps(list_desa_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else if (session.getHierarkiId().equals("5")) {
            getKabupatenByUser();
            getKecamatanByUser();
            getDesaByUser();

            filter_desa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getTps(list_desa_id.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else {
            getKabupatenByUser();
            getKecamatanByUser();
            getDesaByUser();
            getTpsByUser();
        }
    }

    public void getKabupaten(String provinsi_id){
    loaderUi2.show();
    callKabupaten = api.getKabupaten(provinsi_id);
    callKabupaten.enqueue(new Callback<BaseResponse<Kabupaten>>() {
        @Override
        public void onResponse(Call<BaseResponse<Kabupaten>> call, Response<BaseResponse<Kabupaten>> response) {
            if (response.isSuccessful()) {
                loaderUi2.dismiss();
                list_kabupaten_id.clear();
                list_kabupaten.clear();

                for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                    list_kabupaten_id.add(response.body().getData().get(i).getId().toString());
                    list_kabupaten.add(response.body().getData().get(i).getKabupaten());
                }

                //Ini buat ngisi Spinner
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_kabupaten);
                arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                filter_kabupaten.setAdapter(arrayAdapter);
            } else {
                loaderUi2.dismiss();
                ApiError apiError = ErrorUtils.parseError(response);
                Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<BaseResponse<Kabupaten>> call, Throwable t) {
            loaderUi2.dismiss();
            Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}

    public void getKecamatan(String kabupaten_id){
        loaderUi2.show();
        callKecamatan = api.getKecamatan(kabupaten_id);
        callKecamatan.enqueue(new Callback<BaseResponse<Kecamatan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Kecamatan>> call, Response<BaseResponse<Kecamatan>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    list_kecamatan_id.clear();
                    list_kecamatan.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_kecamatan_id.add(response.body().getData().get(i).getId().toString());
                        list_kecamatan.add(response.body().getData().get(i).getKecamatan());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_kecamatan);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    filter_kecamatan.setAdapter(arrayAdapter);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Kecamatan>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDesa(String kecamatan_id){
        loaderUi2.show();
        callDesa = api.getDesa(kecamatan_id);
        callDesa.enqueue(new Callback<BaseResponse<Desa>>() {
            @Override
            public void onResponse(Call<BaseResponse<Desa>> call, Response<BaseResponse<Desa>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    list_desa_id.clear();
                    list_desa.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_desa_id.add(response.body().getData().get(i).getId().toString());
                        list_desa.add(response.body().getData().get(i).getDesa());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_desa);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    filter_desa.setAdapter(arrayAdapter);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Desa>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTps(String desa_id){
        loaderUi2.show();
        callTps = api.getTps(desa_id);
        callTps.enqueue(new Callback<BaseResponse<Tps>>() {
            @Override
            public void onResponse(Call<BaseResponse<Tps>> call, Response<BaseResponse<Tps>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    list_tps_id.clear();
                    list_tps.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_tps_id.add(response.body().getData().get(i).getId().toString());
                        list_tps.add(response.body().getData().get(i).getDesa()+" "+response.body().getData().get(i).getNomerTps());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_tps);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    filter_tps.setAdapter(arrayAdapter);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Tps>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    public void getKabupatenByUser(){
        loaderUi2.show();
        callKabupaten = api.getKabupatenByUser();
        callKabupaten.enqueue(new Callback<BaseResponse<Kabupaten>>() {
            @Override
            public void onResponse(Call<BaseResponse<Kabupaten>> call, Response<BaseResponse<Kabupaten>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    list_kabupaten_id.clear();
                    list_kabupaten.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_kabupaten_id.add(response.body().getData().get(i).getId().toString());
                        list_kabupaten.add(response.body().getData().get(i).getKabupaten());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_kabupaten);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    filter_kabupaten.setAdapter(arrayAdapter);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Kabupaten>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getKecamatanByUser(){
        loaderUi2.show();
        callKecamatan = api.getKecamatanByUser();
        callKecamatan.enqueue(new Callback<BaseResponse<Kecamatan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Kecamatan>> call, Response<BaseResponse<Kecamatan>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    list_kecamatan_id.clear();
                    list_kecamatan.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_kecamatan_id.add(response.body().getData().get(i).getId().toString());
                        list_kecamatan.add(response.body().getData().get(i).getKecamatan());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_kecamatan);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    filter_kecamatan.setAdapter(arrayAdapter);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Kecamatan>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDesaByUser(){
        loaderUi2.show();
        callDesa = api.getDesaByUser();
        callDesa.enqueue(new Callback<BaseResponse<Desa>>() {
            @Override
            public void onResponse(Call<BaseResponse<Desa>> call, Response<BaseResponse<Desa>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    list_desa_id.clear();
                    list_desa.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_desa_id.add(response.body().getData().get(i).getId().toString());
                        list_desa.add(response.body().getData().get(i).getDesa());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_desa);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    filter_desa.setAdapter(arrayAdapter);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Desa>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTpsByUser(){
        loaderUi2.show();
        callTps = api.getTpsByUser();
        callTps.enqueue(new Callback<BaseResponse<Tps>>() {
            @Override
            public void onResponse(Call<BaseResponse<Tps>> call, Response<BaseResponse<Tps>> response) {
                if (response.isSuccessful()) {
                    loaderUi2.dismiss();
                    list_tps_id.clear();
                    list_tps.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_tps_id.add(response.body().getData().get(i).getId().toString());
                        list_tps.add(response.body().getData().get(i).getDesa()+" "+response.body().getData().get(i).getNomerTps());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.spinner_layout, list_tps);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
                    filter_tps.setAdapter(arrayAdapter);
                } else {
                    loaderUi2.dismiss();
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(context, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Tps>> call, Throwable t) {
                loaderUi2.dismiss();
                Toast.makeText(context, "Error on Failur, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
