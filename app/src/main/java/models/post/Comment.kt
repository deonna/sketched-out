package models.post

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Comment(
    val all_awardings: List<Any> = listOf(),
    val approved_at_utc: Any? = null,
    val approved_by: Any? = null,
    val archived: Boolean = false,
    val author: String = "",
    val author_flair_background_color: String = "",
    val author_flair_css_class: String = "",
    val author_flair_richtext: List<Any> = listOf(),
    val author_flair_template_id: Any? = null,
    val author_flair_text: String = "",
    val author_flair_text_color: String = "",
    val author_flair_type: String = "",
    val author_fullname: String = "",
    val author_patreon_flair: Boolean = false,
    val banned_at_utc: Any? = null,
    val banned_by: Any? = null,
    val body: String = "",
    val body_html: String = "",
    val can_gild: Boolean = false,
    val can_mod_post: Boolean = false,
    val collapsed: Boolean = false,
    val collapsed_reason: Any? = null,
    val controversiality: Int = 0,
    val created: Int = 0,
    val created_utc: Int = 0,
    val depth: Int = 0,
    val distinguished: Any? = null,
    val downs: Int = 0,
    val edited: String = "", // could be boolean or long
    val gilded: Int = 0,
    val gildings: Gildings = Gildings.EMPTY,
    val id: String = "",
    val is_submitter: Boolean = false,
    val likes: Any? = null,
    val link_id: String = "",
    val locked: Boolean = false,
    val mod_note: Any? = null,
    val mod_reason_by: Any? = null,
    val mod_reason_title: Any? = null,
    val mod_reports: List<Any> = listOf(),
    val name: String = "",
    val no_follow: Boolean = false,
    val num_reports: Any? = null,
    val parent_id: String = "",
    val permalink: String = "",
    val removal_reason: Any? = null,
    val replies: Any? = null,
    val report_reasons: Any? = null,
    val saved: Boolean = false,
    val score: Int = 0,
    val score_hidden: Boolean = false,
    val send_replies: Boolean = false,
    val stickied: Boolean = false,
    val subreddit: String = "",
    val subreddit_id: String = "",
    val subreddit_name_prefixed: String = "",
    val subreddit_type: String = "",
    val total_awards_received: Int = 0,
    val ups: Int = 0,
    val user_reports: List<Any> = listOf()
) {
    companion object {
        val EMPTY = Comment()
    }
}