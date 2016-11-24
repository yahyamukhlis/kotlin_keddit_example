package com.reanstudio.kotlin.keddit.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.reanstudio.kotlin.keddit.R
import com.reanstudio.kotlin.keddit.commons.RedditNewsItem
import com.reanstudio.kotlin.keddit.commons.adapter.ViewType
import com.reanstudio.kotlin.keddit.commons.adapter.ViewTypeDelegateAdapter
import com.reanstudio.kotlin.keddit.commons.extensions.getFriendlyTime
import com.reanstudio.kotlin.keddit.commons.extensions.inflate
import com.reanstudio.kotlin.keddit.commons.extensions.loadImage

/**
 * Created by yahyamukhlis on 11/22/16.
 */
class NewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_news)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            val ivThumnail = itemView.findViewById(R.id.iv_thumbnail) as ImageView
            val tvDescription = itemView.findViewById(R.id.tv_description) as TextView
            val tvAuthor = itemView.findViewById(R.id.tv_author) as TextView
            val tvComments = itemView.findViewById(R.id.tv_comments) as TextView
            val tvTime = itemView.findViewById(R.id.tv_time) as TextView

            ivThumnail.loadImage(item.thumbnail)
            tvDescription.text = item.title
            tvAuthor.text = item.author
            tvComments.text = "${item.numComments} comments"
            tvTime.text = item.created.getFriendlyTime()
        }
    }
}
