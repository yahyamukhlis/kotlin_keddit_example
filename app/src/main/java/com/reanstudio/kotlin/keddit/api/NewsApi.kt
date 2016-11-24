package com.reanstudio.kotlin.keddit.api

import retrofit2.Call

/**
 * Created by yahyamukhlis on 11/23/16.
 */
interface NewsApi {

    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}