package by.sergey.newsapi.models

import java.util.*
import kotlin.collections.ArrayList

data class Article(
    val source: Source? = null,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: Date? = null,
    val content: String? = null
)

data class Root(
    val status: String? = null,
    val totalResults: Int = 0,
    val articles: ArrayList<Article>? = null
)

data class Source(
    val id: Any? = null,
    val name: String? = null
)

