package models.post

import com.fasterxml.jackson.annotation.JsonProperty

data class CommentType(
    @JsonProperty("data") val comment: Comment,
    @JsonProperty("kind") val kind: String
)