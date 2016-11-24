package com.reanstudio.kotlin.keddit

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reanstudio.kotlin.keddit.commons.InfiniteScrollListener
import com.reanstudio.kotlin.keddit.commons.RedditNews
import com.reanstudio.kotlin.keddit.commons.RxBaseFragment
import com.reanstudio.kotlin.keddit.commons.extensions.inflate
import com.reanstudio.kotlin.keddit.features.news.NewsManager
import com.reanstudio.kotlin.keddit.features.news.adapter.NewsAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by yahyamukhlis on 11/21/16.
 */
class NewsFragment: RxBaseFragment() {

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    private var redditNews: RedditNews? = null

    @Inject lateinit var newsManager: NewsManager

    private var rvNews: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        KedditApp.newsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_news)
        rvNews = view?.findViewById(R.id.rv_news) as RecyclerView
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvNews.apply {
            rvNews?.setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            rvNews?.layoutManager = linearLayout
            rvNews?.clearOnScrollListeners()
            rvNews?.addOnScrollListener(InfiniteScrollListener({requestNews()}, linearLayout))
        }

        initAdapter()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (rvNews?.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    private fun initAdapter() {
        if (rvNews?.adapter == null) {
            rvNews?.adapter = NewsAdapter()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (rvNews?.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.size > 0) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    retrievedNews ->
                    redditNews = retrievedNews
                    (rvNews?.adapter as NewsAdapter).addNews(retrievedNews.news)
                }, {
                    e -> Snackbar.make(rvNews!!, e.message ?: "", Snackbar.LENGTH_LONG).show()
                })

        subscriptions.add(subscription)
    }
}