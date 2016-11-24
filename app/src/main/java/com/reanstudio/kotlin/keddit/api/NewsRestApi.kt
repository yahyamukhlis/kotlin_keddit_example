package com.reanstudio.kotlin.keddit.api

import retrofit2.Call
import javax.inject.Inject

/**
 * Created by yahyamukhlis on 11/22/16.
 */
class NewsRestApi @Inject constructor(private val redditApi: RedditApi) : NewsApi {

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}