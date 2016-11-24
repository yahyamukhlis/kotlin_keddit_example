package com.reanstudio.kotlin.keddit

import android.app.Application
import com.reanstudio.kotlin.keddit.di.NewsModule
import com.reanstudio.kotlin.keddit.di.news.DaggerNewsComponent
import com.reanstudio.kotlin.keddit.di.news.NewsComponent

/**
 * Created by yahyamukhlis on 11/24/16.
 */
class KedditApp : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()

        newsComponent = DaggerNewsComponent.builder()
                .newsModule(NewsModule())
                .build()
    }
}