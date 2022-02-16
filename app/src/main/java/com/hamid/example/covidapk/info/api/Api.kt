package com.hamid.example.covidapk.info.api

import com.hamid.example.covidapk.info.model.IndonesiaResponse
import com.hamid.example.covidapk.info.model.ProvinceResponce
import retrofit2.Call
import  retrofit2.http.GET

interface Api {
    @GET("https://learn-retrofit-data.netlify.app/casenumber.json")
    fun getIndonesia(): Call<ArrayList<IndonesiaResponse>>

    @GET("https://learn-retrofit-data.netlify.app/casenumberprovince.json")
    fun getProvince(): Call<ArrayList<ProvinceResponce>>
}