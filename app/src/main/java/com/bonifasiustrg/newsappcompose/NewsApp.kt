package com.bonifasiustrg.newsappcompose

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bonifasiustrg.newsappcompose.components.BottomMenuScreen
import com.bonifasiustrg.newsappcompose.models.TopNewsArticle
import com.bonifasiustrg.newsappcompose.network.NewsManager
import com.bonifasiustrg.newsappcompose.ui.screen.BookmarkScreen
import com.bonifasiustrg.newsappcompose.ui.screen.CategoriesScreen
import com.bonifasiustrg.newsappcompose.ui.screen.DetailScreen
import com.bonifasiustrg.newsappcompose.ui.screen.SourcesScreen
import com.bonifasiustrg.newsappcompose.ui.screen.TopNews

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsApp() {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()

    MainScreen(navController = navController, scrollState = scrollState)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        "Detail/{newsId}" -> false // on this screen bottom bar should be hidden
//        "RouteOfScreenB" -> false // here too
        else -> true // in all other cases show bottom bar
    }
    Scaffold(bottomBar = { if (showBottomBar) BottomMenuScreen(navController = navController)
    }) {
        Text(text = "${navController.currentDestination}", modifier = Modifier.padding(it))
        Navigation(navController, scrollState, paddingValues = it)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController,
               scrollState: ScrollState,
               newsManager: NewsManager = NewsManager(),
               paddingValues: PaddingValues
) {
//    val navController = rememberNavController()
//    val scrollState = rememberScrollState()

    val articles = newsManager.newsResponse.value.articles
    Log.e("article", "in Navigation $articles")

    articles?.let{
        NavHost(
            navController = navController,
            startDestination = /*"TopNews"*/ BottomMenu.TopNews.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            bottomNavigation(navController, articles)

            /*composable("TopNews") {
                TopNews(navController = navController, articles)

                // Because we dont need to go to Detail again when back stack, just pop the activity,
                // byt in detail screen we still need top news stack, so we not call this function
//            navController.popBackStack()

            }*/

            composable(
                "Detail/{index}",
                arguments = listOf(navArgument(/*"newsId"*/"index") { type = NavType.IntType })
            ) { navBackStackEntry ->
                val /*id*/index = navBackStackEntry.arguments?.getInt("index ")
//                val newsData = MockData.getNewsById(id)
                index?.let {
                    val article = articles[index]
                    Log.e("article", "in navigation, index check detail")
                    DetailScreen(article, scrollState, navController = navController)
                }

            }
        }
    }
}

// BUILD NAVIGATION FOR BOTTOM BAR
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.bottomNavigation(navController: NavController, articles: List<TopNewsArticle>) {
    composable(BottomMenu.TopNews.route){
        TopNews(navController = navController, articles)
    }
    composable(BottomMenu.Categories.route){
        CategoriesScreen(navController = navController)
    }

    composable(BottomMenu.Sources.route){
        SourcesScreen(navController = navController)
    }

    composable(BottomMenu.Bookmark.route){
        BookmarkScreen(navController = navController)
    }
}
