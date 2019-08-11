package models.post

data class Post(
    val after: Any,
    val before: Any,
    val children: List<CommentType>,
    val dist: Any,
    val modhash: String
)