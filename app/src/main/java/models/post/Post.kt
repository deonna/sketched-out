package models.post

import com.fasterxml.jackson.annotation.JsonProperty

data class Post(
    @JsonProperty("after") val after: Any? = null,
    @JsonProperty("before") val before: Any? = null,
    @JsonProperty("children") val children: List<CommentType> = listOf(),
    @JsonProperty("dist") val dist: Any? = null,
    @JsonProperty("modhash") val modhash: String = ""
) {
    companion object {
        val EMPTY = Post()
    }
}