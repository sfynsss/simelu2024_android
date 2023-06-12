package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partai {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_partai")
    @Expose
    private String namaPartai;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("nomer_urut")
    @Expose
    private Integer nomerUrut;
    @SerializedName("jumlah_suara")
    @Expose
    private Integer jumlahSuara;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaPartai() {
        return namaPartai;
    }

    public void setNamaPartai(String namaPartai) {
        this.namaPartai = namaPartai;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNomerUrut() {
        return nomerUrut;
    }

    public void setNomerUrut(Integer nomerUrut) {
        this.nomerUrut = nomerUrut;
    }

    public Integer getJumlahSuara() {
        return jumlahSuara;
    }

    public void setJumlahSuara(Integer jumlahSuara) {
        this.jumlahSuara = jumlahSuara;
    }

}
