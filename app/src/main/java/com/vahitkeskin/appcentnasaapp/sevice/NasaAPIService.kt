package com.vahitkeskin.appcentnasaapp.sevice

import com.vahitkeskin.appcentnasaapp.model.Model
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NasaAPIService {

    //Created for downloading and using data from the
    //internet with Retrofit and RXJAVA using BASE_URL
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NasaAPI::class.java)

    //Curiosity data belonging to
    fun getCuriosityService(): Single<Model> {
        return retrofit.getCuriosityData()
    }

    //Opportunity data belonging to
    fun getOpportunityService(): Single<Model> {
        return retrofit.getOpportunityData()
    }

    //Spirit data belonging to
    fun getSpiritService(): Single<Model> {
        return retrofit.getSpiritData()
    }

}