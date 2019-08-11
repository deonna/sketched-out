package network

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import models.subreddit.SubredditChild
import models.subreddit.SubredditType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object {
        private const val REDDIT_URL = "http://www.reddit.com"
        private const val SKETCH_DAILY = "SketchDaily"
        private const val SKETCH_DAILY_BOT = "sketchdailybot"

        private val IMAGE_URL_REGEX = "\\(http.*[png|jpg|gif|jpeg|tiff|svg|webp].*\\)".toRegex()
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
    }

    fun getImageUrlsInPost(permalink: String): Observable<Set<String>> {
        val urls = subredditApiService
            .getPost(permalink)
            .map { postType ->
                postType.post.children
                    .fold(mutableSetOf<String>(), { acc, commentType ->
                        val body = commentType.comment.body

                        acc.addAll(IMAGE_URL_REGEX.findAll(body).map { it.value })

                        acc
                    })
                    .toSet()
            }

        return urls
    }
}