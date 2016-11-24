package com.reanstudio.kotlin.keddit.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.reanstudio.kotlin.keddit.R
import com.reanstudio.kotlin.keddit.commons.adapter.ViewType
import com.reanstudio.kotlin.keddit.commons.adapter.ViewTypeDelegateAdapter
import com.reanstudio.kotlin.keddit.commons.extensions.inflate

/**
 * Created by yahyamukhlis on 11/22/16.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) { }

    class TurnsViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(parent.inflate(R.layout.item_loading))
}
