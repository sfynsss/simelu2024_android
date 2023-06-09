package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataInduk {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("tps_id")
    @Expose
    private Integer tpsId;
    @SerializedName("DPT")
    @Expose
    private Integer dpt;
    @SerializedName("DPTb")
    @Expose
    private Integer dPTb;
    @SerializedName("DPK")
    @Expose
    private Integer dpk;
    @SerializedName("DPKTb")
    @Expose
    private Integer dPKTb;
    @SerializedName("JUM_DP")
    @Expose
    private Integer jumDp;
    @SerializedName("PENGGUNA_DPT")
    @Expose
    private Integer penggunaDpt;
    @SerializedName("PENGGUNA_DPTb")
    @Expose
    private Integer pENGGUNADPTb;
    @SerializedName("PENGGUNA_DPK")
    @Expose
    private Integer penggunaDpk;
    @SerializedName("PENGGUNA_DPKTb")
    @Expose
    private Integer pENGGUNADPKTb;
    @SerializedName("JUM_PENGGUNA")
    @Expose
    private Integer jumPengguna;
    @SerializedName("jml_surat_pres")
    @Expose
    private Integer jmlSuratPres;
    @SerializedName("jml_surat_kembali_pres")
    @Expose
    private Integer jmlSuratKembaliPres;
    @SerializedName("jml_surat_tidak_digunakan_pres")
    @Expose
    private Integer jmlSuratTidakDigunakanPres;
    @SerializedName("jml_surat_digunakan_pres")
    @Expose
    private Integer jmlSuratDigunakanPres;
    @SerializedName("jml_surat_sah_pres")
    @Expose
    private Integer jmlSuratSahPres;
    @SerializedName("jml_surat_tidak_sah_pres")
    @Expose
    private Integer jmlSuratTidakSahPres;
    @SerializedName("jml_surat_sah_dan_tidak_sah_pres")
    @Expose
    private Integer jmlSuratSahDanTidakSahPres;
    @SerializedName("jml_surat_dpr")
    @Expose
    private Integer jmlSuratDpr;
    @SerializedName("jml_surat_kembali_dpr")
    @Expose
    private Integer jmlSuratKembaliDpr;
    @SerializedName("jml_surat_tidak_digunakan_dpr")
    @Expose
    private Integer jmlSuratTidakDigunakanDpr;
    @SerializedName("jml_surat_digunakan_dpr")
    @Expose
    private Integer jmlSuratDigunakanDpr;
    @SerializedName("jml_surat_sah_dpr")
    @Expose
    private Integer jmlSuratSahDpr;
    @SerializedName("jml_surat_tidak_sah_dpr")
    @Expose
    private Integer jmlSuratTidakSahDpr;
    @SerializedName("jml_surat_sah_dan_tidak_sah_dpr")
    @Expose
    private Integer jmlSuratSahDanTidakSahDpr;
    @SerializedName("jml_surat_dpr_prov")
    @Expose
    private Integer jmlSuratDprProv;
    @SerializedName("jml_surat_kembali_dpr_prov")
    @Expose
    private Integer jmlSuratKembaliDprProv;
    @SerializedName("jml_surat_tidak_digunakan_dpr_prov")
    @Expose
    private Integer jmlSuratTidakDigunakanDprProv;
    @SerializedName("jml_surat_digunakan_dpr_prov")
    @Expose
    private Integer jmlSuratDigunakanDprProv;
    @SerializedName("jml_surat_sah_dpr_prov")
    @Expose
    private Integer jmlSuratSahDprProv;
    @SerializedName("jml_surat_tidak_sah_dpr_prov")
    @Expose
    private Integer jmlSuratTidakSahDprProv;
    @SerializedName("jml_surat_sah_dan_tidak_sah_dpr_prov")
    @Expose
    private Integer jmlSuratSahDanTidakSahDprProv;
    @SerializedName("jml_surat_dpr_kab")
    @Expose
    private Integer jmlSuratDprKab;
    @SerializedName("jml_surat_kembali_dpr_kab")
    @Expose
    private Integer jmlSuratKembaliDprKab;
    @SerializedName("jml_surat_tidak_digunakan_dpr_kab")
    @Expose
    private Integer jmlSuratTidakDigunakanDprKab;
    @SerializedName("jml_surat_digunakan_dpr_kab")
    @Expose
    private Integer jmlSuratDigunakanDprKab;
    @SerializedName("jml_surat_sah_dpr_kab")
    @Expose
    private Integer jmlSuratSahDprKab;
    @SerializedName("jml_surat_tidak_sah_dpr_kab")
    @Expose
    private Integer jmlSuratTidakSahDprKab;
    @SerializedName("jml_surat_sah_dan_tidak_sah_dpr_kab")
    @Expose
    private Integer jmlSuratSahDanTidakSahDprKab;
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

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Integer getTpsId() {
        return tpsId;
    }

    public void setTpsId(Integer tpsId) {
        this.tpsId = tpsId;
    }

    public Integer getDpt() {
        return dpt;
    }

    public void setDpt(Integer dpt) {
        this.dpt = dpt;
    }

    public Integer getDPTb() {
        return dPTb;
    }

    public void setDPTb(Integer dPTb) {
        this.dPTb = dPTb;
    }

    public Integer getDpk() {
        return dpk;
    }

    public void setDpk(Integer dpk) {
        this.dpk = dpk;
    }

    public Integer getDPKTb() {
        return dPKTb;
    }

    public void setDPKTb(Integer dPKTb) {
        this.dPKTb = dPKTb;
    }

    public Integer getJumDp() {
        return jumDp;
    }

    public void setJumDp(Integer jumDp) {
        this.jumDp = jumDp;
    }

    public Integer getPenggunaDpt() {
        return penggunaDpt;
    }

    public void setPenggunaDpt(Integer penggunaDpt) {
        this.penggunaDpt = penggunaDpt;
    }

    public Integer getPENGGUNADPTb() {
        return pENGGUNADPTb;
    }

    public void setPENGGUNADPTb(Integer pENGGUNADPTb) {
        this.pENGGUNADPTb = pENGGUNADPTb;
    }

    public Integer getPenggunaDpk() {
        return penggunaDpk;
    }

    public void setPenggunaDpk(Integer penggunaDpk) {
        this.penggunaDpk = penggunaDpk;
    }

    public Integer getPENGGUNADPKTb() {
        return pENGGUNADPKTb;
    }

    public void setPENGGUNADPKTb(Integer pENGGUNADPKTb) {
        this.pENGGUNADPKTb = pENGGUNADPKTb;
    }

    public Integer getJumPengguna() {
        return jumPengguna;
    }

    public void setJumPengguna(Integer jumPengguna) {
        this.jumPengguna = jumPengguna;
    }

    public Integer getJmlSuratPres() {
        return jmlSuratPres;
    }

    public void setJmlSuratPres(Integer jmlSuratPres) {
        this.jmlSuratPres = jmlSuratPres;
    }

    public Integer getJmlSuratKembaliPres() {
        return jmlSuratKembaliPres;
    }

    public void setJmlSuratKembaliPres(Integer jmlSuratKembaliPres) {
        this.jmlSuratKembaliPres = jmlSuratKembaliPres;
    }

    public Integer getJmlSuratTidakDigunakanPres() {
        return jmlSuratTidakDigunakanPres;
    }

    public void setJmlSuratTidakDigunakanPres(Integer jmlSuratTidakDigunakanPres) {
        this.jmlSuratTidakDigunakanPres = jmlSuratTidakDigunakanPres;
    }

    public Integer getJmlSuratDigunakanPres() {
        return jmlSuratDigunakanPres;
    }

    public void setJmlSuratDigunakanPres(Integer jmlSuratDigunakanPres) {
        this.jmlSuratDigunakanPres = jmlSuratDigunakanPres;
    }

    public Integer getJmlSuratSahPres() {
        return jmlSuratSahPres;
    }

    public void setJmlSuratSahPres(Integer jmlSuratSahPres) {
        this.jmlSuratSahPres = jmlSuratSahPres;
    }

    public Integer getJmlSuratTidakSahPres() {
        return jmlSuratTidakSahPres;
    }

    public void setJmlSuratTidakSahPres(Integer jmlSuratTidakSahPres) {
        this.jmlSuratTidakSahPres = jmlSuratTidakSahPres;
    }

    public Integer getJmlSuratSahDanTidakSahPres() {
        return jmlSuratSahDanTidakSahPres;
    }

    public void setJmlSuratSahDanTidakSahPres(Integer jmlSuratSahDanTidakSahPres) {
        this.jmlSuratSahDanTidakSahPres = jmlSuratSahDanTidakSahPres;
    }

    public Integer getJmlSuratDpr() {
        return jmlSuratDpr;
    }

    public void setJmlSuratDpr(Integer jmlSuratDpr) {
        this.jmlSuratDpr = jmlSuratDpr;
    }

    public Integer getJmlSuratKembaliDpr() {
        return jmlSuratKembaliDpr;
    }

    public void setJmlSuratKembaliDpr(Integer jmlSuratKembaliDpr) {
        this.jmlSuratKembaliDpr = jmlSuratKembaliDpr;
    }

    public Integer getJmlSuratTidakDigunakanDpr() {
        return jmlSuratTidakDigunakanDpr;
    }

    public void setJmlSuratTidakDigunakanDpr(Integer jmlSuratTidakDigunakanDpr) {
        this.jmlSuratTidakDigunakanDpr = jmlSuratTidakDigunakanDpr;
    }

    public Integer getJmlSuratDigunakanDpr() {
        return jmlSuratDigunakanDpr;
    }

    public void setJmlSuratDigunakanDpr(Integer jmlSuratDigunakanDpr) {
        this.jmlSuratDigunakanDpr = jmlSuratDigunakanDpr;
    }

    public Integer getJmlSuratSahDpr() {
        return jmlSuratSahDpr;
    }

    public void setJmlSuratSahDpr(Integer jmlSuratSahDpr) {
        this.jmlSuratSahDpr = jmlSuratSahDpr;
    }

    public Integer getJmlSuratTidakSahDpr() {
        return jmlSuratTidakSahDpr;
    }

    public void setJmlSuratTidakSahDpr(Integer jmlSuratTidakSahDpr) {
        this.jmlSuratTidakSahDpr = jmlSuratTidakSahDpr;
    }

    public Integer getJmlSuratSahDanTidakSahDpr() {
        return jmlSuratSahDanTidakSahDpr;
    }

    public void setJmlSuratSahDanTidakSahDpr(Integer jmlSuratSahDanTidakSahDpr) {
        this.jmlSuratSahDanTidakSahDpr = jmlSuratSahDanTidakSahDpr;
    }

    public Integer getJmlSuratDprProv() {
        return jmlSuratDprProv;
    }

    public void setJmlSuratDprProv(Integer jmlSuratDprProv) {
        this.jmlSuratDprProv = jmlSuratDprProv;
    }

    public Integer getJmlSuratKembaliDprProv() {
        return jmlSuratKembaliDprProv;
    }

    public void setJmlSuratKembaliDprProv(Integer jmlSuratKembaliDprProv) {
        this.jmlSuratKembaliDprProv = jmlSuratKembaliDprProv;
    }

    public Integer getJmlSuratTidakDigunakanDprProv() {
        return jmlSuratTidakDigunakanDprProv;
    }

    public void setJmlSuratTidakDigunakanDprProv(Integer jmlSuratTidakDigunakanDprProv) {
        this.jmlSuratTidakDigunakanDprProv = jmlSuratTidakDigunakanDprProv;
    }

    public Integer getJmlSuratDigunakanDprProv() {
        return jmlSuratDigunakanDprProv;
    }

    public void setJmlSuratDigunakanDprProv(Integer jmlSuratDigunakanDprProv) {
        this.jmlSuratDigunakanDprProv = jmlSuratDigunakanDprProv;
    }

    public Integer getJmlSuratSahDprProv() {
        return jmlSuratSahDprProv;
    }

    public void setJmlSuratSahDprProv(Integer jmlSuratSahDprProv) {
        this.jmlSuratSahDprProv = jmlSuratSahDprProv;
    }

    public Integer getJmlSuratTidakSahDprProv() {
        return jmlSuratTidakSahDprProv;
    }

    public void setJmlSuratTidakSahDprProv(Integer jmlSuratTidakSahDprProv) {
        this.jmlSuratTidakSahDprProv = jmlSuratTidakSahDprProv;
    }

    public Integer getJmlSuratSahDanTidakSahDprProv() {
        return jmlSuratSahDanTidakSahDprProv;
    }

    public void setJmlSuratSahDanTidakSahDprProv(Integer jmlSuratSahDanTidakSahDprProv) {
        this.jmlSuratSahDanTidakSahDprProv = jmlSuratSahDanTidakSahDprProv;
    }

    public Integer getJmlSuratDprKab() {
        return jmlSuratDprKab;
    }

    public void setJmlSuratDprKab(Integer jmlSuratDprKab) {
        this.jmlSuratDprKab = jmlSuratDprKab;
    }

    public Integer getJmlSuratKembaliDprKab() {
        return jmlSuratKembaliDprKab;
    }

    public void setJmlSuratKembaliDprKab(Integer jmlSuratKembaliDprKab) {
        this.jmlSuratKembaliDprKab = jmlSuratKembaliDprKab;
    }

    public Integer getJmlSuratTidakDigunakanDprKab() {
        return jmlSuratTidakDigunakanDprKab;
    }

    public void setJmlSuratTidakDigunakanDprKab(Integer jmlSuratTidakDigunakanDprKab) {
        this.jmlSuratTidakDigunakanDprKab = jmlSuratTidakDigunakanDprKab;
    }

    public Integer getJmlSuratDigunakanDprKab() {
        return jmlSuratDigunakanDprKab;
    }

    public void setJmlSuratDigunakanDprKab(Integer jmlSuratDigunakanDprKab) {
        this.jmlSuratDigunakanDprKab = jmlSuratDigunakanDprKab;
    }

    public Integer getJmlSuratSahDprKab() {
        return jmlSuratSahDprKab;
    }

    public void setJmlSuratSahDprKab(Integer jmlSuratSahDprKab) {
        this.jmlSuratSahDprKab = jmlSuratSahDprKab;
    }

    public Integer getJmlSuratTidakSahDprKab() {
        return jmlSuratTidakSahDprKab;
    }

    public void setJmlSuratTidakSahDprKab(Integer jmlSuratTidakSahDprKab) {
        this.jmlSuratTidakSahDprKab = jmlSuratTidakSahDprKab;
    }

    public Integer getJmlSuratSahDanTidakSahDprKab() {
        return jmlSuratSahDanTidakSahDprKab;
    }

    public void setJmlSuratSahDanTidakSahDprKab(Integer jmlSuratSahDanTidakSahDprKab) {
        this.jmlSuratSahDanTidakSahDprKab = jmlSuratSahDanTidakSahDprKab;
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
