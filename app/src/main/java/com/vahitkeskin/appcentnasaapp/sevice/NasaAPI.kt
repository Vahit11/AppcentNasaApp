package com.vahitkeskin.appcentnasaapp.sevice

import com.vahitkeskin.appcentnasaapp.model.Model
import io.reactivex.Single
import retrofit2.http.GET

interface NasaAPI {

    //API (User Interface) Created for the information of the remaining project except BASE_URL

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=nxnqHS25AWIY6jj0CFqUMMeomxBwJh3JmnfbHMQk&page=1")
    fun getCuriosityData(): Single<Model>

    @GET("mars-photos/api/v1/rovers/opportunity/photos?sol=1000&api_key=nxnqHS25AWIY6jj0CFqUMMeomxBwJh3JmnfbHMQk&page=1")
    fun getOpportunityData(): Single<Model>

    @GET("mars-photos/api/v1/rovers/spirit/photos?sol=1000&api_key=nxnqHS25AWIY6jj0CFqUMMeomxBwJh3JmnfbHMQk&page=1")
    fun getSpiritData(): Single<Model>

}