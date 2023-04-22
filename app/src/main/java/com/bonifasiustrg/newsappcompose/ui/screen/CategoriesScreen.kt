package com.bonifasiustrg.newsappcompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bonifasiustrg.newsappcompose.models.getAllArticleCategory
import com.bonifasiustrg.newsappcompose.network.NewsManager

@Composable
fun CategoriesScreen(navController: NavController,
                     onFetchCategory: (String) -> Unit,
                     newsManager: NewsManager
) {
//    Text(text = "Categories Screen")

    val tabsItems = getAllArticleCategory()

    Column() {
        LazyRow() {
            items(tabsItems.size) {
                val category = tabsItems[it]
                
                CategoryTab(category = category.categoryName,
                        onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }
    }
}

@Composable
fun CategoryTab(
    category: String, isSelected: Boolean = false,
    onFetchCategory: (String) -> Unit
) {
   val bgColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer
    
    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 16.dp)
            .clickable {
                onFetchCategory(category)
            },
        shape = MaterialTheme.shapes.small,
        color = bgColor
    ) {
        Text(text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun CategoryPreview() {

}