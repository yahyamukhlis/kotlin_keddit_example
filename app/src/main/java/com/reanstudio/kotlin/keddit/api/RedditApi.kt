package com.reanstudio.kotlin.keddit.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by yahyamukhlis on 11/22/16.
 */
interface RedditApi {

    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String): Call<RedditNewsResponse>
}