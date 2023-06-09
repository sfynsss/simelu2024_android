package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DapilProvinsi {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("provinsi_id")
    @Expose
    private Integer provinsiId;
    @SerializedName("kategori_id")
    @Expose
    private Integer kategoriId;
    @SerializedName("dapil_provinsi")
    @Expose
    private String dapilProvinsi;
    @SerializedName("jumlah_kursi")
    @Expose
    private Integer jumlahKursi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("provinsi")
    @Expose
    private Provinsi provinsi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getDapilProvinsi() {
        return dapilProvinsi;
    }

    public void setDapilProvinsi(String dapilProvinsi) {
        this.dapilProvinsi = dapilProvinsi;
    }

    public Integer getJumlahKursi() {
        return jumlahKursi;
    }

    public void setJumlahKursi(Integer jumlahKursi) {
        this.jumlahKursi = jumlahKursi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }
}
