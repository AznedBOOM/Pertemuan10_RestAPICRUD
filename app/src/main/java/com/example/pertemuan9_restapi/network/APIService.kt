package com.example.pertemuan9_restapi.network


import com.example.pertemuan9_restapi.model.request.Mahasiswa
import com.example.pertemuan9_restapi.model.response.ResponseDeleteData
import com.example.pertemuan9_restapi.model.response.ResponsePostData
import com.example.pertemuan9_restapi.model.response.ResponseUpdateData
import com.example.pertemuan9_restapi.model.response.ResponseDataMahasiswa
import com.example.pertemuan9_restapi.model.response.ResponseDetailMahasiswa
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET("datamahasiswa/")
    fun getDataMahasiswa() : Call<ResponseDataMahasiswa>

    @GET("datamahasiswa/{nim}")
    fun getDetailMahasiswa(@Path("nim") nim : String ) : Call<ResponseDetailMahasiswa>

    @POST("datamahasiswa/")
    fun addDetailMahasiswa(@Body data : Mahasiswa) : Call<ResponsePostData>

    @POST("datamahasiswa/{nim}")
    fun updateDetailMahasiswa(@Path("nim") nim : String, @Body data: Mahasiswa) : Call<ResponseUpdateData>

    @DELETE("datamahasiswa/{nim}")
    fun deleteDetailMahasiswa(@Path("nim") nim : String) : Call<ResponseDeleteData>
}