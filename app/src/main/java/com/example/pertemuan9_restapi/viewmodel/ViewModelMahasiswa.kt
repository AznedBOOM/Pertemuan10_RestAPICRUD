package com.example.pertemuan9_restapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pertemuan9_restapi.model.request.DataMahasiswa
import com.example.pertemuan9_restapi.model.request.Mahasiswa
import com.example.pertemuan9_restapi.model.response.ResponseDataMahasiswa
import com.example.pertemuan9_restapi.model.response.ResponseDeleteData
import com.example.pertemuan9_restapi.model.response.ResponseDetailMahasiswa
import com.example.pertemuan9_restapi.model.response.ResponsePostData
import com.example.pertemuan9_restapi.model.response.ResponseUpdateData
import com.example.pertemuan9_restapi.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMahasiswa : ViewModel() {
    private val getDataMahasiswa = MutableLiveData<List<DataMahasiswa>?>()
    private val detailMahasiswa = MutableLiveData<ResponseDetailMahasiswa?>()
    private val insertMahasiswa = MutableLiveData<ResponsePostData?>()
    private val updateMahasiswa = MutableLiveData<ResponseUpdateData?>()

    fun getDataMahasiswa() : MutableLiveData<List<DataMahasiswa>?>{
        return getDataMahasiswa
    }

    fun getDetailDataMahasiswa() : MutableLiveData<ResponseDetailMahasiswa?>{
        return detailMahasiswa
    }

    fun insertMahasiswa() : MutableLiveData<ResponsePostData?>{
        return insertMahasiswa
    }

    fun updateMahasiswa() : MutableLiveData<ResponseUpdateData?>{
        return updateMahasiswa
    }

    fun showDataMahasiswa(){
        APIClient.instance.getDataMahasiswa().enqueue(object : Callback<ResponseDataMahasiswa>{
            override fun onResponse(
                call: Call<ResponseDataMahasiswa>,
                response: Response<ResponseDataMahasiswa>
            ) {
                if (response.isSuccessful){
                    getDataMahasiswa.postValue(response.body()?.data)
                } else {
                    getDataMahasiswa.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDataMahasiswa>, t: Throwable) {
                getDataMahasiswa.postValue(null)
            }

        })
    }

    fun getDetailData(nim : String){
        APIClient.instance.getDetailMahasiswa(nim).enqueue(object  : Callback<ResponseDetailMahasiswa>{
            override fun onResponse(
                call: Call<ResponseDetailMahasiswa>,
                response: Response<ResponseDetailMahasiswa>
            ) {
                if(response.isSuccessful){
                    detailMahasiswa.postValue(response.body())
                }
                else{
                    detailMahasiswa.postValue(null )
                }
            }

            override fun onFailure(call: Call<ResponseDetailMahasiswa>, t: Throwable) {
                detailMahasiswa.postValue(null )
            }

        })
    }

    fun addMahasiswa(nim : String, nama : String, telepon : String){
        APIClient.instance.addDetailMahasiswa(Mahasiswa(nim, nama, telepon))
            .enqueue(object : Callback<ResponsePostData>{
            override fun onResponse(
                call: Call<ResponsePostData>,
                response: Response<ResponsePostData>
            ) {
                if (response.isSuccessful){
                    insertMahasiswa.postValue(response.body())
                } else{
                    insertMahasiswa.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponsePostData>, t: Throwable) {
                insertMahasiswa.postValue(null)
            }

        })
    }

    fun updateMahasiswa(nim : String, nama: String, telepon: String){
        APIClient.instance.updateDetailMahasiswa(nim, Mahasiswa(nim, nama, telepon))
            .enqueue(object : Callback<ResponseUpdateData>{
                override fun onResponse(
                    call: Call<ResponseUpdateData>,
                    response: Response<ResponseUpdateData>
                ) {
                    if (response.isSuccessful){
                        updateMahasiswa.postValue((response.body()))
                    } else{
                        updateMahasiswa.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseUpdateData>, t: Throwable) {
                    updateMahasiswa.postValue(null)
                }

            })
    }

}