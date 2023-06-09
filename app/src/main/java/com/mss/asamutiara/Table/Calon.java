package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Calon {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kategori_id")
    @Expose
    private Integer kategoriId;
    @SerializedName("partai_id")
    @Expose
    private String partaiId;
    @SerializedName("nomer_urut")
    @Expose
    private Integer nomerUrut;
    @SerializedName("nama_calon")
    @Expose
    private String namaCalon;
    @SerializedName("dapil_pusat_id")
    @Expose
    private String dapilPusatId;
    @SerializedName("dapil_provinsi_id")
    @Expose
    private String dapilProvinsiId;
    @SerializedName("dapil_kabupaten_id")
    @Expose
    private String dapilKabupatenId;
    @SerializedName("jumlah_suara")
    @Expose
    private String jumlahSuara;
    @SerializedName("sts_jadi")
    @Expose
    private Integer stsJadi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gambar_calon")
    @Expose
    private String gambarCalon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getPartaiId() {
        return partaiId;
    }

    public void setPartaiId(String partaiId) {
        this.partaiId = partaiId;
    }

    public Integer getNomerUrut() {
        return nomerUrut;
    }

    public void setNomerUrut(Integer nomerUrut) {
        this.nomerUrut = nomerUrut;
    }

    public String getNamaCalon() {
        return namaCalon;
    }

    public void setNamaCalon(String namaCalon) {
        this.namaCalon = namaCalon;
    }

    public String getDapilPusatId() {
        return dapilPusatId;
    }

    public void setDapilPusatId(String dapilPusatId) {
        this.dapilPusatId = dapilPusatId;
    }

    public String getDapilProvinsiId() {
        return dapilProvinsiId;
    }

    public void setDapilProvinsiId(String dapilProvinsiId) {
        this.dapilProvinsiId = dapilProvinsiId;
    }

    public String getDapilKabupatenId() {
        return dapilKabupatenId;
    }

    public void setDapilKabupatenId(String dapilKabupatenId) {
        this.dapilKabupatenId = dapilKabupatenId;
    }

    public String getJumlahSuara() {
        return jumlahSuara;
    }

    public void setJumlahSuara(String jumlahSuara) {
        this.jumlahSuara = jumlahSuara;
    }

    public Integer getStsJadi() {
        return stsJadi;
    }

    public void setStsJadi(Integer stsJadi) {
        this.stsJadi = stsJadi;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGambarCalon() {
        return gambarCalon;
    }

    public void setGambarCalon(String gambarCalon) {
        this.gambarCalon = gambarCalon;
    }

}
