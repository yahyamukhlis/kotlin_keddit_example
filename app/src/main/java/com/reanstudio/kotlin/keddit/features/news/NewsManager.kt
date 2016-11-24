package com.reanstudio.kotlin.keddit.features.news

import com.reanstudio.kotlin.keddit.api.NewsApi
import com.reanstudio.kotlin.keddit.commons.RedditNews
import com.reanstudio.kotlin.keddit.commons.RedditNewsItem
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yahyamukhlis on 11/22/16.
 */
@Singleton
class NewsManager @Inject constructor(private val api: NewsApi) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {

        return Observable.create {
            subcriber ->

            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments, item.created,
                            item.thumbnail, item.url)
                }
                val redditNews = RedditNews(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        news
                )
                subcriber.onNext(redditNews)
                subcriber.onCompleted()
            } else {
                subcriber.onError(Throwable(response.message()))
            }
        }
    }
}
