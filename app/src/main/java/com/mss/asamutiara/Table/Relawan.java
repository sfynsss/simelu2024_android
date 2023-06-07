package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relawan {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("dapil_provinsi_id")
    @Expose
    private String dapilProvinsiId;
    @SerializedName("kabupaten_id")
    @Expose
    private Integer kabupatenId;
    @SerializedName("dapil_kabupaten_id")
    @Expose
    private String dapilKabupatenId;
    @SerializedName("kecamatan_id")
    @Expose
    private String kecamatanId;
    @SerializedName("desa_id")
    @Expose
    private String desaId;
    @SerializedName("tps_id")
    @Expose
    private String tpsId;
    @SerializedName("calon_id")
    @Expose
    private String calonId;
    @SerializedName("target")
    @Expose
    private Integer target;
    @SerializedName("hierarki_id")
    @Expose
    private Integer hierarkiId;
    @SerializedName("relawan_id")
    @Expose
    private Integer relawanId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("desa")
    @Expose
    private String desa;
    @SerializedName("nama_calon")
    @Expose
    private String namaCalon;
    @SerializedName("nama_hierarki")
    @Expose
    private String namaHierarki;
    @SerializedName("tingkat")
    @Expose
    private String tingkat;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("kabupaten")
    @Expose
    private String kabupaten;
    @SerializedName("provinsi_id")
    @Expose
    private Integer provinsiId;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("dapil_provinsi")
    @Expose
    private String dapilProvinsi;
    @SerializedName("dapil_kabupaten")
    @Expose
    private String dapilKabupaten;
    @SerializedName("nama_relawan")
    @Expose
    private String namaRelawan;
    @SerializedName("tps")
    @Expose
    private String tps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getDapilProvinsiId() {
        return dapilProvinsiId;
    }

    public void setDapilProvinsiId(String dapilProvinsiId) {
        this.dapilProvinsiId = dapilProvinsiId;
    }

    public Integer getKabupatenId() {
        return kabupatenId;
    }

    public void setKabupatenId(Integer kabupatenId) {
        this.kabupatenId = kabupatenId;
    }

    public String getDapilKabupatenId() {
        return dapilKabupatenId;
    }

    public void setDapilKabupatenId(String dapilKabupatenId) {
        this.dapilKabupatenId = dapilKabupatenId;
    }

    public String getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(String kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getDesaId() {
        return desaId;
    }

    public void setDesaId(String desaId) {
        this.desaId = desaId;
    }

    public String getTpsId() {
        return tpsId;
    }

    public void setTpsId(String tpsId) {
        this.tpsId = tpsId;
    }

    public String getCalonId() {
        return calonId;
    }

    public void setCalonId(String calonId) {
        this.calonId = calonId;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getHierarkiId() {
        return hierarkiId;
    }

    public void setHierarkiId(Integer hierarkiId) {
        this.hierarkiId = hierarkiId;
    }

    public Integer getRelawanId() {
        return relawanId;
    }

    public void setRelawanId(Integer relawanId) {
        this.relawanId = relawanId;
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

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getNamaCalon() {
        return namaCalon;
    }

    public void setNamaCalon(String namaCalon) {
        this.namaCalon = namaCalon;
    }

    public String getNamaHierarki() {
        return namaHierarki;
    }

    public void setNamaHierarki(String namaHierarki) {
        this.namaHierarki = namaHierarki;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public Integer getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getDapilProvinsi() {
        return dapilProvinsi;
    }

    public void setDapilProvinsi(String dapilProvinsi) {
        this.dapilProvinsi = dapilProvinsi;
    }

    public String getDapilKabupaten() {
        return dapilKabupaten;
    }

    public void setDapilKabupaten(String dapilKabupaten) {
        this.dapilKabupaten = dapilKabupaten;
    }

    public String getNamaRelawan() {
        return namaRelawan;
    }

    public void setNamaRelawan(String namaRelawan) {
        this.namaRelawan = namaRelawan;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }
}
