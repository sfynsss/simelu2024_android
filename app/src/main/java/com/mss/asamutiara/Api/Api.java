package com.mss.asamutiara.Api;

import com.mss.asamutiara.Response.BaseResponse;
import com.mss.asamutiara.Response.UserResponse;
import com.mss.asamutiara.Table.CekSession;
import com.mss.asamutiara.Table.DapilKabupaten;
import com.mss.asamutiara.Table.DataInduk;
import com.mss.asamutiara.Table.Desa;
import com.mss.asamutiara.Table.Hierarki;
import com.mss.asamutiara.Table.Kabupaten;
import com.mss.asamutiara.Table.Kecamatan;
import com.mss.asamutiara.Table.Relawan;
import com.mss.asamutiara.Table.Suara;
import com.mss.asamutiara.Table.Tps;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("hierarki/getOption")
    Call<BaseResponse<Hierarki>> getHierarki(
            @Field("hierarki_id") String hierarki_id
    );

    @FormUrlEncoded
    @POST("kabupaten/getOption")
    Call<BaseResponse<Kabupaten>> getKabupaten(
            @Field("provinsi_id") String provinsi_id
    );

    @FormUrlEncoded
    @POST("dapilKabupaten/getOption")
    Call<BaseResponse<DapilKabupaten>> getDapilKabupaten(
            @Field("kabupaten_id") String kabupaten_id
    );

    @FormUrlEncoded
    @POST("kecamatan/getOption")
    Call<BaseResponse<Kecamatan>> getKecamatan(
            @Field("kabupaten_id") String kabupaten_id
    );

    @FormUrlEncoded
    @POST("desa/getOption")
    Call<BaseResponse<Desa>> getDesa(
            @Field("kecamatan_id") String kecamatan_id
    );

    @FormUrlEncoded
    @POST("tps/getOption")
    Call<BaseResponse<Tps>> getTps(
            @Field("desa_id") String desa_id
    );

    //    GET WILAYAH BY USER
    @GET("kabupaten/getOptionByUser")
    Call<BaseResponse<Kabupaten>> getKabupatenByUser(
    );

    @GET("kecamatan/getOptionByUser")
    Call<BaseResponse<Kecamatan>> getKecamatanByUser();

    @GET("desa/getOptionByUser")
    Call<BaseResponse<Desa>> getDesaByUser();

    @GET("tps/getOptionByUser")
    Call<BaseResponse<Tps>> getTpsByUser();
//    END OF GET WILAYAH BY USER

    @GET("cekSession")
    Call<BaseResponse<CekSession>> cekSession();

    @GET("getRelawan")
    Call<BaseResponse<Relawan>> getRelawan();

    @GET("getSuara")
    Call<BaseResponse<Suara>> getSuara();

    @FormUrlEncoded
    @POST("createRelawan")
    Call<BaseResponse> createRelawan(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("nik") String nik,
            @Field("dapil_provinsi") String dapil_provinsi,
            @Field("kabupaten") String kabupaten,
            @Field("dapil_kabupaten") String dapil_kabupaten,
            @Field("kecamatan") String kecamatan,
            @Field("desa") String desa,
            @Field("tps") String tps,
            @Field("calon") String calon,
            @Field("no_telp") String no_telp,
            @Field("target") String target,
            @Field("hierarki") String hierarki,
            @Field("relawan_id") String relawan_id
    );

    @FormUrlEncoded
    @POST("createSuara")
    Call<BaseResponse> createSuara(
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("tps_id") String tps_id,
            @Field("relawan_id") String relawan_id,
            @Field("no_telp") String no_telp
    );

    @FormUrlEncoded
    @POST("getDataInduk")
    Call<BaseResponse<DataInduk>> getDataInduk(
            @Field("tps_id") String tps_id
    );

    @FormUrlEncoded
    @POST("storeDataInduk")
    Call<BaseResponse> storeDataInduk(
            @Field("tps_id") String tps_id,

            @Field("dpt") String dpt,
            @Field("dptb") String dptb,
            @Field("dpk") String dpk,
            @Field("dpktb") String dpktb,
            @Field("jum_dp") String jum_dp,

            @Field("p_dpt") String p_dpt,
            @Field("p_dptb") String p_dptb,
            @Field("p_dpk") String p_dpk,
            @Field("p_dpktb") String p_dpktb,
            @Field("jum_p_dp") String jum_p_dp
    );
}
