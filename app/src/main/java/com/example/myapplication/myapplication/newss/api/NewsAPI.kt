package com.example.myapplication.myapplication.newss.api

import com.example.myapplication.myapplication.newss.BuildConfig.API_KEY
import com.example.myapplication.myapplication.newss.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNewsPerCategory(
        @Query("country")
        countryCode:String="in",
        @Query("page")
        pageNumber:Int =1,
        @Query("apikey")
        apikey:String=API_KEY
    ): Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery:String,
        @Query("page")
        pageNumber:Int=1,
        @Query("apikey")
        apikey:String= API_KEY
    ):Response<NewsResponse>


}