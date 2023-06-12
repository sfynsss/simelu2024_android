package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Caleg {

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
    private Integer dapilProvinsiId;
    @SerializedName("dapil_kabupaten_id")
    @Expose
    private Integer dapilKabupatenId;
    @SerializedName("jumlah_suara")
    @Expose
    private Integer jumlahSuara;
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
    private Integer gambarCalon;
    @SerializedName("id_calon")
    @Expose
    private Integer idCalon;
    @SerializedName("provinsi_id")
    @Expose
    private Integer provinsiId;
    @SerializedName("kabupaten_id")
    @Expose
    private Integer kabupatenId;
    @SerializedName("desa_id")
    @Expose
    private Long desaId;
    @SerializedName("tps_id")
    @Expose
    private Integer tpsId;

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

    public Integer getDapilProvinsiId() {
        return dapilProvinsiId;
    }

    public void setDapilProvinsiId(Integer dapilProvinsiId) {
        this.dapilProvinsiId = dapilProvinsiId;
    }

    public Integer getDapilKabupatenId() {
        return dapilKabupatenId;
    }

    public void setDapilKabupatenId(Integer dapilKabupatenId) {
        this.dapilKabupatenId = dapilKabupatenId;
    }

    public Integer getJumlahSuara() {
        return jumlahSuara;
    }

    public void setJumlahSuara(Integer jumlahSuara) {
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

    public Integer getGambarCalon() {
        return gambarCalon;
    }

    public void setGambarCalon(Integer gambarCalon) {
        this.gambarCalon = gambarCalon;
    }

    public Integer getIdCalon() {
        return idCalon;
    }

    public void setIdCalon(Integer idCalon) {
        this.idCalon = idCalon;
    }

    public Integer getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public Integer getKabupatenId() {
        return kabupatenId;
    }

    public void setKabupatenId(Integer kabupatenId) {
        this.kabupatenId = kabupatenId;
    }

    public Long getDesaId() {
        return desaId;
    }

    public void setDesaId(Long desaId) {
        this.desaId = desaId;
    }

    public Integer getTpsId() {
        return tpsId;
    }

    public void setTpsId(Integer tpsId) {
        this.tpsId = tpsId;
    }

}
