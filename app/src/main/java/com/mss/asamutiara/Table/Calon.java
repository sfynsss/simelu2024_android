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
    private Integer partaiId;
    @SerializedName("nomer_urut")
    @Expose
    private Integer nomerUrut;
    @SerializedName("nama_calon")
    @Expose
    private String namaCalon;
    @SerializedName("dapil_pusat_id")
    @Expose
    private Integer dapilPusatId;
    @SerializedName("dapil_provinsi_id")
    @Expose
    private Object dapilProvinsiId;
    @SerializedName("dapil_kabupaten_id")
    @Expose
    private Object dapilKabupatenId;
    @SerializedName("jumlah_suara")
    @Expose
    private Object jumlahSuara;
    @SerializedName("sts_jadi")
    @Expose
    private Integer stsJadi;
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

    public Integer getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Integer kategoriId) {
        this.kategoriId = kategoriId;
    }

    public Integer getPartaiId() {
        return partaiId;
    }

    public void setPartaiId(Integer partaiId) {
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

    public Integer getDapilPusatId() {
        return dapilPusatId;
    }

    public void setDapilPusatId(Integer dapilPusatId) {
        this.dapilPusatId = dapilPusatId;
    }

    public Object getDapilProvinsiId() {
        return dapilProvinsiId;
    }

    public void setDapilProvinsiId(Object dapilProvinsiId) {
        this.dapilProvinsiId = dapilProvinsiId;
    }

    public Object getDapilKabupatenId() {
        return dapilKabupatenId;
    }

    public void setDapilKabupatenId(Object dapilKabupatenId) {
        this.dapilKabupatenId = dapilKabupatenId;
    }

    public Object getJumlahSuara() {
        return jumlahSuara;
    }

    public void setJumlahSuara(Object jumlahSuara) {
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

}
