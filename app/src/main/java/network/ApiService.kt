package network

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import models.subreddit.SubredditChild
import models.subreddit.SubredditType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class ApiService {

    companion object {
        private const val REDDIT_URL = "https://www.reddit.com"
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
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(observeOn))
            .build()

        subredditApiService = retrofit.create(SubredditEndpointInterface::class.java)
    }

    fun getImageUrls(): Observable<Set<String>> {
        val urlStream = getListing()
            .map { subredditType ->
                getSketchDailyPermalinks(subredditType.subreddit.children)
            }
            .flatMapIterable { permalinks ->
                permalinks.map { permalink ->
                    getImageUrlsInPost(permalink)
                }
            }
            .flatMap { it }

        return urlStream
    }

    private fun getListing(): Observable<SubredditType> {
        return subredditApiService.getSubreddit(SKETCH_DAILY)
    }

    private fun getSketchDailyPermalinks(subredditChildren: List<SubredditChild>): List<String> {
        return subredditChildren
            .filter { it.subredditChildData.author == SKETCH_DAILY_BOT }
            .map {
                val permalink = it.subredditChildData.permalink

                // remove starting and ending slashes
                permalink.subSequence(1, permalink.lastIndex).toString()
            }
    }

    private fun getImageUrlsInPost(permalink: String): Observable<Set<String>> {
        val urls = subredditApiService
            .getPost(permalink)
            .map { postTypes ->

                val images = mutableSetOf<String>()

                postTypes
                    .forEach { postType ->
                        postType.post.children
                            .filter { it.comment.depth == 0 } // only direct replies
                            .forEach { commentType ->
                                val body = commentType.comment.body


                                val matches = IMAGE_URL_REGEX.findAll(body)
                                    .map {
                                        val match = it.value

                                        val url =
                                            match.subSequence(1, match.lastIndex).toString()

                                        url
                                    }.toSet()


                                if (matches.isNotEmpty()) {
                                    images.addAll(matches)
                                }
                            }

                    }

                images.toSet()
            }

        return urls
    }
}