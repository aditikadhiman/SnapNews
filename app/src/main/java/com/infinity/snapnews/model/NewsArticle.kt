package com.infinity.snapnews.model

import java.util.*

data class NewsArticle(
//    val id: String = UUID.randomUUID().toString(),
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String?
)
