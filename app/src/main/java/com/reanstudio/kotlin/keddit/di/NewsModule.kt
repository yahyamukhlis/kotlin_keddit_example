package com.reanstudio.kotlin.keddit.di

import com.reanstudio.kotlin.keddit.api.NewsApi
import com.reanstudio.kotlin.keddit.api.NewsRestApi
import com.reanstudio.kotlin.keddit.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by yahyamukhlis on 11/24/16.
 */
@Module
class NewsModule {

    @Provides
    @Singleton
    fun providerNewsApi(redditApi: RedditApi): NewsApi {
        return NewsRestApi(redditApi)
    }

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }
}