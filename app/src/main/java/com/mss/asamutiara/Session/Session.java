package com.mss.asamutiara.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Context context;

    public Session(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences("Absensi", context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public void setUserStatus(Boolean loggedIn, String id_user, String nik, String nama, String token, String target, String hierarki_id,
                              String nama_hierarki, String nama_calon, String desa, String kecamatan, String kabupaten, String provinsi_id,
                              String provinsi) {
        editor.putBoolean("loggedIn", loggedIn);
        editor.putString("id_user", id_user);
        editor.putString("nik", nik);
        editor.putString("nama", nama);
        editor.putString("token", token);
        editor.putString("target", target);
        editor.putString("hierarki_id", hierarki_id);
        editor.putString("nama_hierarki", nama_hierarki);
        editor.putString("nama_calon", nama_calon);
        editor.putString("desa", desa);
        editor.putString("kecamatan", kecamatan);
        editor.putString("kabupaten", kabupaten);
        editor.putString("provinsi_id", provinsi_id);
        editor.putString("provinsi", provinsi);
        editor.commit();
    }

    public String getBaseUrl() {
        return preferences.getString("baseUrl", "192.168.1.9:8000");
    }

    public boolean getUserLoggedIn() {
        return preferences.getBoolean("loggedIn", false);
    }

    public String getToken() {
        return preferences.getString("token", "");
    }

    public String getNip() {
        return preferences.getString("nip", "");
    }

    public String getNama() {
        return preferences.getString("nama", "");
    }

    public String getNamaHierarki() {
        return preferences.getString("nama_hierarki", "");
    }

    public String getHierarkiId() {
        return preferences.getString("hierarki_id", "");
    }

    public String getNamaCalon() {
        return preferences.getString("nama_calon", "");
    }

    public String getDesa() {
        return preferences.getString("desa", "");
    }

    public String getKecamatan() {
        return preferences.getString("kecamatan", "");
    }

    public String getKabupaten() {
        return preferences.getString("kabupaten", "");
    }

    public String getTarget() {
        return preferences.getString("target", "");
    }

    public String getProvinsiId() {
        return preferences.getString("provinsi_id", "");
    }

    public String getProvinsi() {
        return preferences.getString("provinsi", "");
    }

    public void setWilayah(String provinsi, String kabupaten, String kecamatan, String desa, String tps) {
        editor.putString("filter_provinsi", provinsi);
        editor.putString("filter_kabupaten", kabupaten);
        editor.putString("filter_kecamatan", kecamatan);
        editor.putString("filter_desa", desa);
        editor.putString("filter_tps", tps);
        editor.commit();
    }

    public String getProvinsiActive() {
        return preferences.getString("filter_provinsi", "");
    }

    public String getKabupatenActive() {
        return preferences.getString("filter_kabupaten", "");
    }

    public String getKecamatanActive() {
        return preferences.getString("filter_kecamatan", "");
    }

    public String getDesaActive() {
        return preferences.getString("filter_desa", "");
    }

    public String getTpsActive() {
        return preferences.getString("filter_tps", "");
    }
}

