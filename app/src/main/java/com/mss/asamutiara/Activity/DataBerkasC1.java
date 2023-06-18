package com.mss.asamutiara.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.mss.asamutiara.Api.Api;
import com.mss.asamutiara.Api.RetrofitClient;
import com.mss.asamutiara.R;
import com.mss.asamutiara.Session.Session;

public class DataBerkasC1 extends AppCompatActivity {

    Context context;
    Activity activity;
    Session session;
    Api api;
    RequestOptions option;

    LoaderUi loaderUi;
    LoaderUi2 loaderUi2;

    AppCompatImageView filter;
    TextView title_tps_active;
    FilterWilayah filterWilayah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_berkas_c1);

        context = this;
        activity = this;
        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        option = new RequestOptions().placeholder(R.drawable.ic_hourglass_empty_24).error(R.drawable.ic_highlight_off_24);

        loaderUi = new LoaderUi(context, activity);
        loaderUi2 = new LoaderUi2(context, activity);

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
                    }
                });
                filterWilayah.show();
            }
        });

        if (!session.getNamaTpsActive().equals("")) {
            title_tps_active.setText(session.getNamaTpsActive());
        }
    }
}