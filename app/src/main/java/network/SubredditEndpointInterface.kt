package network

import io.reactivex.Observable
import models.post.PostType
import models.subreddit.SubredditType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SubredditEndpointInterface {

    @GET("/r/{subreddit}.json")
    fun getSubreddit(@Path("subreddit") subreddit: String, @Query("after") after: String? = null):
        Observable<SubredditType>

    @GET("/{permalink}.json")
    fun getPost(
        @Path("permalink") permalink: String, @Query("after") after: String? = null
    ): Observable<List<PostType>>
}