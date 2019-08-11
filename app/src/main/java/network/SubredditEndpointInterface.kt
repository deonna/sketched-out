package network

import io.reactivex.Observable
import models.post.PostType
import models.subreddit.SubredditType
import retrofit2.http.GET
import retrofit2.http.Path

interface SubredditEndpointInterface {

    @GET("/r/{subreddit}")
    fun getSubreddit(@Path("subreddit") subreddit: String): Observable<SubredditType>

    @GET("/{permalink}")
    fun getPost(@Path("permalink") permalink: String): Observable<PostType>
}