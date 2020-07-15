package com.giussepr.gorillabook.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.giussepr.gorillabook.ui.feed.FeedViewModel
import com.giussepr.gorillabook.utility.viewModel.ViewModelFactory
import com.giussepr.gorillabook.utility.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    internal abstract fun bindFeedViewModel(viewModel: FeedViewModel): ViewModel
}