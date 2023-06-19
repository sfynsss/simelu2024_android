package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterLogistik {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("penyalur")
    @Expose
    private Integer penyalur;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("penerima")
    @Expose
    private Integer penerima;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("nama_penyalur")
    @Expose
    private String namaPenyalur;
    @SerializedName("nama_penerima")
    @Expose
    private String namaPenerima;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getPenyalur() {
        return penyalur;
    }

    public void setPenyalur(Integer penyalur) {
        this.penyalur = penyalur;
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

    public Integer getPenerima() {
        return penerima;
    }

    public void setPenerima(Integer penerima) {
        this.penerima = penerima;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public String getNamaPenyalur() {
        return namaPenyalur;
    }

    public void setNamaPenyalur(String namaPenyalur) {
        this.namaPenyalur = namaPenyalur;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }


}
