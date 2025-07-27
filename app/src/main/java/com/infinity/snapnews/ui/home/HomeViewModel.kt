package com.infinity.snapnews.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinity.snapnews.model.NewsArticle
import com.infinity.snapnews.repository.NewsRepository
import com.infinity.snapnews.state.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel()
{

    // All predefined categories
    private val categories = listOf(
        "Top Stories", "Technology", "Sports", "Business", "Health", "Entertainment", "Science"
    )

    private val _selectedCategory = MutableStateFlow("Top Stories")
    val selectedCategory: StateFlow<String> = _selectedCategory

    private val _newsState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val newsState: StateFlow<NewsUiState> = _newsState

    init {
        fetchNewsForCategory("Top Stories")
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
        fetchNewsForCategory(category)
    }

    private fun fetchNewsForCategory(category: String) {
        _newsState.value = NewsUiState.Loading

        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(category = resolveCategoryParam(category))

                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    _newsState.value = NewsUiState.Success(articles)
                } else {
                    _newsState.value = NewsUiState.Error("Error: ${response.message()}")
                }

            } catch (e: Exception) {
                _newsState.value = NewsUiState.Error("Exception: ${e.message}")
            }
        }
    }

    fun searchNews(query: String) {
        _newsState.value = NewsUiState.Loading

        viewModelScope.launch {
            try {
                val response = repository.searchNews(query)

                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    _newsState.value = NewsUiState.Success(articles)
                } else {
                    _newsState.value = NewsUiState.Error("Error: ${response.message()}")
                }

            } catch (e: Exception) {
                _newsState.value = NewsUiState.Error("Exception: ${e.message}")
            }
        }
    }

    // NewsAPI requires "general" instead of "Top Stories"
    private fun resolveCategoryParam(category: String): String {
        return when (category) {
            "Top Stories" -> "general"
            else -> category.lowercase()
        }
    }

    private val _selectedArticle = MutableStateFlow<NewsArticle?>(null)
    val selectedArticle: StateFlow<NewsArticle?> = _selectedArticle

    fun selectArticle(article: NewsArticle) {
        _selectedArticle.value = article
    }
}
