package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kecamatan {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kabupaten_id")
    @Expose
    private Integer kabupatenId;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("dapil_pusat")
    @Expose
    private Integer dapilPusat;
    @SerializedName("dapil_provinsi")
    @Expose
    private Integer dapilProvinsi;
    @SerializedName("dapil_kabupaten")
    @Expose
    private Integer dapilKabupaten;
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

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public Integer getDapilPusat() {
        return dapilPusat;
    }

    public void setDapilPusat(Integer dapilPusat) {
        this.dapilPusat = dapilPusat;
    }

    public Integer getDapilProvinsi() {
        return dapilProvinsi;
    }

    public void setDapilProvinsi(Integer dapilProvinsi) {
        this.dapilProvinsi = dapilProvinsi;
    }

    public Integer getDapilKabupaten() {
        return dapilKabupaten;
    }

    public void setDapilKabupaten(Integer dapilKabupaten) {
        this.dapilKabupaten = dapilKabupaten;
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
