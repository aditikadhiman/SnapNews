package com.infinity.snapnews.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.infinity.snapnews.ui.detail.DetailScreen
import com.infinity.snapnews.ui.home.HomeScreen
import com.infinity.snapnews.ui.home.HomeViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val homeViewModel: HomeViewModel= hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController,homeViewModel)
        }

        composable(route = Screen.Details.route) { backStackEntry ->
//            val articleUrl = backStackEntry.arguments?.getString("articleUrl") ?: ""
            DetailScreen(viewModel = homeViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

