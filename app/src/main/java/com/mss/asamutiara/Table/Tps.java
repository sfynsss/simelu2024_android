package com.mss.asamutiara.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tps {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nomer_tps")
    @Expose
    private Integer nomerTps;
    @SerializedName("desa_id")
    @Expose
    private Long desaId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("desa")
    @Expose
    private String desa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNomerTps() {
        return nomerTps;
    }

    public void setNomerTps(Integer nomerTps) {
        this.nomerTps = nomerTps;
    }

    public Long getDesaId() {
        return desaId;
    }

    public void setDesaId(Long desaId) {
        this.desaId = desaId;
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

}
