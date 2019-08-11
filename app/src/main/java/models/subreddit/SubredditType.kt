package models.subreddit

import com.fasterxml.jackson.annotation.JsonProperty

data class SubredditType(
    @JsonProperty("data") val subreddit: Subreddit = Subreddit.EMPTY,
    @JsonProperty("kind") val kind: String = ""
)