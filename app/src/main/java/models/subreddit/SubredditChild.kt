package models.subreddit

import com.fasterxml.jackson.annotation.JsonProperty

data class SubredditChild(
    @JsonProperty("data") val subredditChildData: SubredditChildData = SubredditChildData.EMPTY,
    @JsonProperty("kind") val kind: String = ""
)