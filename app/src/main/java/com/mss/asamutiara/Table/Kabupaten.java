package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kabupaten {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("provinsi_id")
    @Expose
    private Integer provinsiId;
    @SerializedName("kabupaten")
    @Expose
    private String kabupaten;
    @SerializedName("dapil_pusat")
    @Expose
    private Integer dapilPusat;
    @SerializedName("dapil_provinsi")
    @Expose
    private Integer dapilProvinsi;
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

    public Integer getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
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
