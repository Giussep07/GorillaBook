package com.giussepr.gorillabook.app

import com.facebook.stetho.Stetho
import com.giussepr.gorillabook.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class GorillaBookApp : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this).build()
    }

    override fun onCreate() {
        super<DaggerApplication>.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}