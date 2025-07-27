package com.infinity.snapnews.ui.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.infinity.snapnews.navigation.Screen
import com.infinity.snapnews.state.NewsUiState
import com.infinity.snapnews.ui.components.Heading
import com.infinity.snapnews.ui.components.NewsCard
import com.infinity.snapnews.ui.components.SearchBar
import com.infinity.snapnews.ui.components.Tabs
import com.infinity.snapnews.ui.theme.Primary
import com.infinity.snapnews.ui.theme.background

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val scrollState = rememberScrollState()
//    val viewModel: HomeViewModel = hiltViewModel()
    val newsState by viewModel.newsState.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    var query by remember { mutableStateOf("") }

   Surface(modifier = Modifier
       .fillMaxSize(),
   color = background) {

       Column(modifier = Modifier.fillMaxSize()) {

           Spacer(modifier = Modifier.height(10.dp))
           // Heading
           Heading(value = "Snap News")
           Spacer(modifier = Modifier.height(10.dp))

           // Search Bar
           SearchBar(
               value = query,
               query = query,
               onQueryChanged = {
                   query = it
                   if (it.isNotEmpty()) viewModel.searchNews(it)
                   else viewModel.onCategorySelected(selectedCategory) // fallback
               },
               modifier = Modifier
                   .padding(horizontal = 16.dp)
                   .fillMaxWidth()
           )

           Spacer(modifier = Modifier.height(8.dp))

           // Category Tabs
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .horizontalScroll(scrollState)
                   .padding(horizontal = 12.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
           ) {
               listOf(
                   "Top Stories",
                   "Technology",
                   "Sports",
                   "Business",
                   "Health",
                   "Entertainment",
                   "Science"
               ).forEach { category ->
                   Tabs(
                       value = category,
                       isSelected = selectedCategory == category,
                       onClick = { viewModel.onCategorySelected(category) }
                   )
               }
           }

           Spacer(modifier = Modifier.height(12.dp))

           // News List
           when (newsState) {
               is NewsUiState.Loading -> {
                   Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                       CircularProgressIndicator(color = Primary)
                   }
               }

               is NewsUiState.Error -> {
                   val message = (newsState as NewsUiState.Error).message
                   Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                       Text(text = message, color = MaterialTheme.colors.error)
                   }
               }

               is NewsUiState.Success -> {
                   val articles = (newsState as NewsUiState.Success).articles

                   LazyColumn(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(bottom = 12.dp),
                       contentPadding = PaddingValues(horizontal = 8.dp)
                   ) {
                       items(articles) { article ->
                           NewsCard(
                               heading = article.title ?: "Title not found",
                               sourceName = article.source?.name ?: "Unknown Source",
                               date = article.publishedAt ?: "",
                               desciption = article.description ?: "No description available",
                               icon = article.urlToImage ?: "",
                               onClick = {
                                   // Navigate to DetailScreen, pass data via arguments or savedStateHandle
//                                   navController.currentBackStackEntry?.savedStateHandle?.set(
//                                       "article",
//                                       article
//                                   )
//                                   navController.navigate("details")
                                   viewModel.selectArticle(article)
                                   navController.navigate(Screen.Details.route)
                               }
                           )
                       }
                   }
               }
           }
       }
   }
}
