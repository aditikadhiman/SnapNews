package com.infinity.snapnews.state

import com.infinity.snapnews.model.NewsArticle


sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val articles: List<NewsArticle>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}
