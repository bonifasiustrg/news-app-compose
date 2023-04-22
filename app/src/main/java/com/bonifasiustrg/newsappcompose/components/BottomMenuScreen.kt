package com.bonifasiustrg.newsappcompose.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bonifasiustrg.newsappcompose.BottomMenu

@Composable
fun BottomMenuScreen(navController: NavController) {
    val menuItems = listOf(
        BottomMenu.TopNews,
        BottomMenu.Categories,
        BottomMenu.Sources,
        BottomMenu.Bookmark
    )

    NavigationBar(contentColor = Color.Black /*item color*/,
        containerColor = MaterialTheme.colorScheme.primary /*Bot nav container color*/,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        val navBackStateException by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStateException?.destination?.route //check current route

        menuItems.forEach {
            val selected = currentRoute == it.route
            NavigationBarItem(selected = selected,
                label = { Text(text = it.title)},
//                label = { Text(text = it.title, color = if (selected) Color.Black else Color.Gray)},
                alwaysShowLabel = selected,
                icon = { Icon(imageVector = it.icon, contentDescription = "", modifier = Modifier.size(30.dp)
//                    if (selected) 40.dp else 20.dp)
                )},/*
                icon = { Icon(imageVector = it.icon, contentDescription = "",
                    tint = if (selected) Color.Black else Color.Gray
                )},*/
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    unselectedIconColor = Color.White
                )
            )
        }
    }
}