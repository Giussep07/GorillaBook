package com.giussepr.gorillabook.di

import android.app.Application
import com.giussepr.gorillabook.app.GorillaBookApp
import com.giussepr.gorillabook.di.modules.FeedModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        FragmentBindingModule::class,
        ActivityBindingModule::class,
        FeedModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<GorillaBookApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(application: Application): Builder

        fun build(): ApplicationComponent
    }
}