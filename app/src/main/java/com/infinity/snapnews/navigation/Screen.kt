package com.infinity.snapnews.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{articleUrl}") {
//        fun createRoute(articleUrl: String) = "details/$articleUrl"
    }
}
