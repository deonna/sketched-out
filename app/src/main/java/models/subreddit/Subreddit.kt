package models.subreddit

import com.fasterxml.jackson.annotation.JsonProperty

data class Subreddit(
    @JsonProperty("after") val after: String = "",
    @JsonProperty("before") val before: Any? = null,
    @JsonProperty("children") val children: List<SubredditChild> = listOf(),
    @JsonProperty("dist") val dist: Int = 0,
    @JsonProperty("modhash") val modhash: String = ""
) {
    companion object {
        val EMPTY = Subreddit()
    }
}