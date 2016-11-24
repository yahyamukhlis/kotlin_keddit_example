package com.reanstudio.kotlin.keddit.di.news

import com.reanstudio.kotlin.keddit.NewsFragment
import com.reanstudio.kotlin.keddit.di.AppModule
import com.reanstudio.kotlin.keddit.di.NetworkModule
import com.reanstudio.kotlin.keddit.di.NewsModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by yahyamukhlis on 11/24/16.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class
))
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)
}