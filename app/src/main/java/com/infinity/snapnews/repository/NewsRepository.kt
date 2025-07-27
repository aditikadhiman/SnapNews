package com.infinity.snapnews.repository


import com.infinity.snapnews.model.NewsArticle
import com.infinity.snapnews.model.NewsResponse
import com.infinity.snapnews.network.NewsApiService
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: NewsApiService
) {

    suspend fun getTopHeadlines(category: String, country: String = "us"): Response<NewsResponse> {
        return apiService.getTopHeadlines(category = category, country = country)
    }

    suspend fun searchNews(query: String): Response<NewsResponse> {
        return apiService.searchNews(query = query)
    }


}
