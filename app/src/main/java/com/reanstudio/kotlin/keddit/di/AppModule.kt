package com.reanstudio.kotlin.keddit.di

import android.content.Context
import com.reanstudio.kotlin.keddit.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yahyamukhlis on 11/24/16.
 */
@Module
class AppModule(val app: KedditApp) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideApplication(): KedditApp {
        return app
    }
}