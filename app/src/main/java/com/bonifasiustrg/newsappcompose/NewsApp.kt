package com.bonifasiustrg.newsappcompose

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bonifasiustrg.newsappcompose.ui.screen.DetailScreen
import com.bonifasiustrg.newsappcompose.ui.screen.TopNews

@Composable
fun NewsApp() {
    Navigation()
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()

    NavHost(navController = navController, startDestination = "TopNews") {
        composable("TopNews") {
            TopNews(navController = navController)

            // Because we dont need to go to Detail again when back stack, just pop the activity,
            // byt in detail screen we still need top news stack, so we not call this function
//            navController.popBackStack()

        }

        composable("Detail/{newsId}", arguments = listOf(navArgument("newsId"){ type = NavType.IntType})
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData =MockData.getNewsById(id)
            DetailScreen(newsData, scrollState, navController = navController)
        }
    }
}