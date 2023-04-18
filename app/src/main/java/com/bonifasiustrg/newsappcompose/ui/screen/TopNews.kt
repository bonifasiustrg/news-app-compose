package com.bonifasiustrg.newsappcompose.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bonifasiustrg.NewsData
import com.bonifasiustrg.newsappcompose.MockData
import com.bonifasiustrg.newsappcompose.MockData.getTimeAgo
import com.bonifasiustrg.newsappcompose.R

@Composable
fun TopNews(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        /*Button(onClick = {
            navController.navigate("Detail")
        }) {
            Toast.makeText(LocalContext.current, "Navigate to Detail Screen", Toast.LENGTH_SHORT).show()
            Text(text = "Go to Detail Screen")


        }*/
        LazyColumn() {
            items(MockData.topNewsList) {newsData ->
                TopNewsItem(newsData = newsData, onNewsClick = {
                    navController.navigate("Detail/${newsData.id}")
                })
            }
        }

    }
}

@Composable
fun TopNewsItem(newsData: NewsData, onNewsClick: () -> Unit) {
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }
    ) {
        Image(painter = painterResource(id = newsData.image), contentDescription ="",contentScale = ContentScale.FillBounds)
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(
//                text = newsData.publishedAt,
                text = MockData.stringToDate(newsData.publishedAt).getTimeAgo(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = newsData.title,color = Color.White,fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
//    TopNews(rememberNavController())
//    TopNewsItem(
//        NewsData(1,
//            R.drawable.news_img1,
//            "Bonifasius",
//            "Pembubaran DPR",
//            "bkakbakfakfbfb sdkfsdjflkl sldkfjsd f",
//            "14 Apr 2023"
//        )
//
//    )
}