package com.giussepr.gorillabook.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giussepr.gorillabook.dto.feed.FeedResDTO
import com.giussepr.gorillabook.entity.feed.Feed
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val repository: IContractFeed.Repository) :
    ViewModel(), IContractFeed.ViewModel {

    private var feedHasLoaded = false
    private val listFeed: MutableList<Feed> = arrayListOf()

    private val _feed = MutableLiveData<List<Feed>>()
    val feed: LiveData<List<Feed>>
        get() = _feed

    private val _errorGettingData = MutableLiveData<Boolean>()
    val errorGettingData: LiveData<Boolean>
        get() = _errorGettingData


    override fun getFeed() {
        if (!feedHasLoaded) {
            viewModelScope.launch {
                val response = repository.getFeed()

                if (response.isSuccessful) {
                    response.body()?.let {
                        _feed.value = FeedResDTO.toListFeedEntity(it)
                        listFeed.addAll(FeedResDTO.toListFeedEntity(it))
                        feedHasLoaded = true
                    }
                } else {
                    _errorGettingData.value = true
                }
            }
        }

    }

    override fun addFeed(feed: Feed) {
        listFeed.add(0, feed)
        _feed.value = listFeed
    }
}