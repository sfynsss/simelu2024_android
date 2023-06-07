package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DapilKabupaten {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kabupaten_id")
    @Expose
    private Integer kabupatenId;
    @SerializedName("kategori_id")
    @Expose
    private Integer kategoriId;
    @SerializedName("dapil_kabupaten")
    @Expose
    private String dapilKabupaten;
    @SerializedName("jumlah_kursi")
    @Expose
    private Integer jumlahKursi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKabupatenId() {
        return kabupatenId;
    }

    public void setKabupatenId(Integer kabupatenId) {
        this.kabupatenId = kabupatenId;
    }

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getDapilKabupaten() {
        return dapilKabupaten;
    }

    public void setDapilKabupaten(String dapilKabupaten) {
        this.dapilKabupaten = dapilKabupaten;
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

}
