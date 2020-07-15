package com.giussepr.gorillabook.di

import com.giussepr.gorillabook.ui.feed.FeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindFeedFragment(): FeedFragment
}