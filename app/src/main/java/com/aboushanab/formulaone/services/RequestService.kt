package com.aboushanab.formulaone.services

import com.aboushanab.formulaone.responses.DriversResponse
import com.aboushanab.formulaone.responses.GoogleResponse
import com.aboushanab.formulaone.responses.SeasonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestService {
    @Headers("Content-Type:application/json")
    @GET("seasons.json?limit=100")
    fun getSeasons(): Call<SeasonResponse>

    @Headers("Content-Type:application/json")
    @GET("{year}/drivers.json?limit=100")
    fun getDrivers(@Path("year") year: String): Call<DriversResponse>

    @GET("customsearch/v1")
    fun getImage( @Query("q") search_par: String,
        @Query("key") api_key: String ="AIzaSyADx9HTfg1vEtKt2KllxBhwpjB5qUvO52k" ,
                 @Query("cx") engine_key: String="000213537299717655806:fsqehiydnxg"
                ): Call<GoogleResponse>
}