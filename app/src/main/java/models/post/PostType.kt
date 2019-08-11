package models.post

import com.fasterxml.jackson.annotation.JsonProperty

data class PostType(
    @JsonProperty("data") val post: Post = Post.EMPTY,
    @JsonProperty("kind") val kind: String = ""
)

