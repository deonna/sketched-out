package models.subreddit

data class Subreddit(
    val after: String,
    val before: Any,
    val children: List<SubredditChild>,
    val dist: Int,
    val modhash: String
)