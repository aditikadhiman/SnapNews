package com.infinity.snapnews.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinity.snapnews.model.NewsArticle
import com.infinity.snapnews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
//
//    private val _selectedArticle = MutableStateFlow<NewsArticle?>(null)
//    val selectedArticle: StateFlow<NewsArticle?> = _selectedArticle
//
//    fun loadSelectedArticle(articleId: String) {
//        viewModelScope.launch {
//            val article = repository.getArticleById(articleId)
//            _selectedArticle.value = article
//        }
//    }
//
//    fun setSelectedArticle(article: NewsArticle) {
//        _selectedArticle.value = article
//    }
}
