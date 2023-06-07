package com.mss.asamutiara.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;

import com.mss.asamutiara.R;

public class FilterWilayah {

    static Context context;
    static Activity activity;
    static Dialog dialog;

    ImageView close;
    AppCompatButton terapkan;

    public FilterWilayah(Context context, Activity activity) {
        dialog = new Dialog(context);
        dialog.setTitle("Gambar Barang");
        View v = activity.getLayoutInflater().inflate(R.layout.filter_wilayah, null);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.setContentView(v);
        close = v.findViewById(R.id.close);
        terapkan = v.findViewById(R.id.terapkan);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        terapkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void show() {
        dialog.show();
    }
    public void dismiss() {
        dialog.dismiss();
    }

}
