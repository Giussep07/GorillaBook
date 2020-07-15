package com.giussepr.gorillabook.di.modules

import com.giussepr.gorillabook.repository.feed.FeedRepository
import com.giussepr.gorillabook.ui.feed.IContractFeed
import com.giussepr.gorillabook.webService.GorillaBookApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FeedModule {

    @Provides
    @Singleton
    fun provideFeedRepository(
        gorillaBookApi: GorillaBookApi
    ): IContractFeed.Repository {
        return FeedRepository(gorillaBookApi)
    }
}