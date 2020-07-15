package com.giussepr.gorillabook.ui.feed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giussepr.gorillabook.databinding.FeedFragmentItemBinding
import com.giussepr.gorillabook.entity.feed.Feed

class FeedAdapter : ListAdapter<Feed, FeedAdapter.FeedViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeedViewHolder constructor(private val binding: FeedFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feed: Feed) {
            binding.feed = feed

            if (feed.imageUrl.isEmpty()) {
                binding.imageViewExpand.visibility = View.GONE
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FeedViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FeedFragmentItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )

                return FeedViewHolder(binding)
            }
        }
    }

}