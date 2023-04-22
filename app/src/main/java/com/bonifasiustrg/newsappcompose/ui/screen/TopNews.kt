package com.bonifasiustrg.newsappcompose.ui.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bonifasiustrg.newsappcompose.MockData
import com.bonifasiustrg.newsappcompose.MockData.getTimeAgo
import com.bonifasiustrg.newsappcompose.R
import com.bonifasiustrg.newsappcompose.models.TopNewsArticle
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopNews(navController: NavController, articles: List<TopNewsArticle>) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        /*Button(onClick = {
            navController.navigate("Detail")
        }) {
            Toast.makeText(LocalContext.current, "Navigate to Detail Screen", Toast.LENGTH_SHORT).show()
            Text(text = "Go to Detail Screen")


        }*/
        LazyColumn() {
//            items(MockData.topNewsList) {newsData ->
            items(articles.size) {index ->
                Log.e("article", "in topnews, lazycolumn")
                TopNewsItem(/*newsData = newsData*/ /*onNewsClick = {
                    navController.navigate("Detail/${articles[idx].}")
                },*/ article = articles[index],   onNewsClick = {
                    navController.navigate("Detail/$index")
                })
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
//fun TopNewsItem(newsData: NewsData, onNewsClick: () -> Unit) {   //WITH MOCK DATA
fun TopNewsItem(article: TopNewsArticle, onNewsClick: () -> Unit) {  // WITH API RESPONSE DATA
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            Log.e("article", "Item clicked, go to detail screen")
            onNewsClick()
        }
    ) {
//        Image(painter = painterResource(id = newsData.image), contentDescription ="",contentScale = ContentScale.FillBounds)
        CoilImage(
            imageModel = { article.urlToImage },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop, alignment = Alignment.Center
            ),
            failure = { ImageBitmap.imageResource(R.drawable.news_img1) },
            previewPlaceholder = R.drawable.news_img1,
            modifier = Modifier.fillMaxWidth()
        )

        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),verticalArrangement = Arrangement.SpaceBetween) {
            Text(
//                text = newsData.publishedAt,
//                text = MockData.stringToDate(newsData.publishedAt).getTimeAgo(),
                text = MockData.stringToDate(/*newsData*/article.publishedAt!!).getTimeAgo(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = /*newsData*/article.title!!,color = Color.White,fontWeight = FontWeight.SemiBold)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    /*TopNewsItem(
        TopNewsArticle(
            author = "Bonifasius",
            title = "Pembubaran DPR",
            description = "bkakbakfakfbfb sdkfsdjflkl sldkfjsd f",
            publishedAt = "14 Apr 2023"
        ),
    )*/
}