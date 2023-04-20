package com.bonifasiustrg.newsappcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Source
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenu(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object TopNew: BottomMenu("top_news", Icons.Outlined.Home, "Top News")
    object Categories: BottomMenu("categories", Icons.Outlined.Category, "Category")
    object Sources: BottomMenu("sources", Icons.Outlined.Source, "Sources")
    object Bookmark: BottomMenu("bookmark", Icons.Outlined.Bookmarks, "Saved News")
}
