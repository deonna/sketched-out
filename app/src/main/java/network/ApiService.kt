package network

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import models.subreddit.SubredditType
import models.subreddit.SubredditChild
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object {
        private const val REDDIT_URL = "http://www.reddit.com"
        private const val SKETCH_DAILY = "SketchDaily"
        private const val SKETCH_DAILY_BOT = "sketchdailybot"
    }

    private val observeOn = Schedulers.io()

    private val retrofit: Retrofit

    private val subredditApiService: SubredditEndpointInterface

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(REDDIT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(observeOn))
            .build()

        subredditApiService = retrofit.create(SubredditEndpointInterface::class.java)
    }

    fun getListing(): Observable<SubredditType> {
        return subredditApiService.getSubreddit(SKETCH_DAILY)
    }

    fun getSketchDailyPermalinks(subredditChildren: List<SubredditChild>): List<String> {
        return subredditChildren
            .filter { it.subredditChildData.author == SKETCH_DAILY_BOT }
            .map { it.subredditChildData.permalink }
            .map { permalink ->
                permalink.removePrefix("/")
            }
    }
}