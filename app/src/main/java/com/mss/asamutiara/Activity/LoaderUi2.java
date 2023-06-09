package com.mss.asamutiara.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;

import com.mss.asamutiara.R;

public class LoaderUi2 {

    static Context context;
    static Activity activity;
    static Dialog dialog;

    public LoaderUi2(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        dialog = new Dialog(context);
        dialog.setTitle("Gambar Barang");
        View v = activity.getLayoutInflater().inflate(R.layout.loader_ui2, null);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.setContentView(v);

        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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
}
