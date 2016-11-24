package com.reanstudio.kotlin.keddit.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by yahyamukhlis on 11/24/16.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}