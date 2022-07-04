package com.gopay.network

import com.gopay.network.response.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleApi {

    @GET("people")
    suspend fun getPeopleList(
        @Query("page")page:Int,
        @Query("name")name:String
    ) : PeopleResponse
}